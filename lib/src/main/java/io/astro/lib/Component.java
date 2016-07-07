package io.astro.lib;

import java.util.Collection;

/**
 * @author skeswa
 */
public abstract class Component implements Renderable {
    private FieldValueSet fieldState;

    // TODO
    void setFields(final FieldValueSet nextFieldState) {
        // Make sure that this component is cleared for an update.
        if (shouldUpdate(nextFieldState)) {
            fieldState = nextFieldState;
            // TODO(skeswa): re-render.
        }
    }

    // TODO
    boolean shouldUpdate(final FieldValueSet nextFieldState) {
        return fieldState == null && nextFieldState != null ||
            fieldState != null && nextFieldState == null ||
            (fieldState != null && !fieldState.equals(nextFieldState));
    }

    protected <T> T valueOf(final Field<T> field) {
        if (field == null) {
            throw new IllegalArgumentException("Null is not a valid field.");
        }

        if (fieldState == null || !fieldState.has(field)) {
            return field.getDefaultValue();
        }

        return fieldState.valueOf(field);
    }

    protected final ElementBuilder $(final ElementBuilder elementBuilder) {
        return elementBuilder;
    }

    protected final ElementChildArgumentList $(final ElementBuilder...elementBuilders) {
        if (elementBuilders == null || elementBuilders.length == 0) {
            return null;
        }

        return new ElementChildArgumentList(elementBuilders);
    }

    protected final ElementChildArgumentList $(final Collection<ElementBuilder> elementBuilders) {
        if (elementBuilders == null || elementBuilders.size() == 0) {
            return null;
        }

        return new ElementChildArgumentList(elementBuilders);
    }

    protected final ElementBuilder $(final Class<? extends Renderable> renderableType) {
        // The component type is required.
        if (renderableType == null) {
            // TODO(skeswa): standardize parameter null check exception messages.
            throw new IllegalArgumentException("Null is not a valid renderable type.");
        }

        return new ElementBuilder(renderableType);
    }

    @Override
    public abstract Element render();
}
