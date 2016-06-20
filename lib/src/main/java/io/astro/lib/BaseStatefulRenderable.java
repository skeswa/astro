package io.astro.lib;

/**
 * @author skeswa
 */
public abstract class BaseStatefulRenderable extends BaseRenderable implements StatefulRenderable {
    private FieldValueSet fieldState;

    @Override
    public void setFields(final FieldValueSet nextFieldState) {
        // Make sure that this component is cleared for an update.
        if (shouldUpdate(nextFieldState)) {
            fieldState = nextFieldState;
            // TODO(skeswa): re-render.
        }
    }

    @Override
    public boolean shouldUpdate(final FieldValueSet nextFieldState) {
        return fieldState == null && nextFieldState != null ||
            fieldState != null && nextFieldState == null ||
            (fieldState != null && !fieldState.equals(nextFieldState));
    }

    @Override
    public abstract Element render();

    protected <T> T valueOf(final Field<T> field) {
        if (field == null) {
            throw new IllegalArgumentException("Null is not a valid field.");
        }

        if (fieldState == null || !fieldState.has(field)) {
            return field.getDefaultValue();
        }

        return fieldState.valueOf(field);
    }
}
