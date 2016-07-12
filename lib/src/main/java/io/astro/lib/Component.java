package io.astro.lib;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author skeswa
 */
public abstract class Component implements Renderable {
    static final Element[] EMPTY_CHILDREN = new Element[0];
    static final FieldValueSet EMPTY_FIELD_STATE = new FieldValueSet(new HashMap<Field, Object>());
    static final AttributeValueSet EMPTY_ATTRIBUTE_STATE = new AttributeValueSet(new HashMap<Attribute, Object>());

    private ElementComposite composite;
    private int compositeReductionDepth = -1;

    private Element[] children = EMPTY_CHILDREN;
    private FieldValueSet fieldState = EMPTY_FIELD_STATE;
    private AttributeValueSet attributeState = EMPTY_ATTRIBUTE_STATE;

    @Override
    public void onMount() {}

    @Override
    public void onUnmount() {}

    @Override
    public void setCompositeReductionDepth(final int compositeReductionDepth) {
        this.compositeReductionDepth = compositeReductionDepth;
    }

    @Override
    public void setComposite(final ElementComposite composite) {
        this.composite = composite;
    }

    @SuppressWarnings("unused")
    protected void onChildrenWillChange(final Element[] nextChildren) {}

    @Override
    public void setChildren(final Element[] nextChildren) {
        onChildrenWillChange(nextChildren);
        children = nextChildren;
    }

    @SuppressWarnings("unused")
    protected void onAttributesWillChange(final AttributeValueSet nextAttributeState) {}

    @Override
    public void setAttributes(AttributeValueSet nextAttributeState) {
        onAttributesWillChange(nextAttributeState);
        attributeState = nextAttributeState;
    }

    @SuppressWarnings("unused")
    protected void onFieldsWillChange(final FieldValueSet nextFieldState) {}

    void setFields(final FieldValueSet nextFieldState) {
        onFieldsWillChange(nextFieldState);
        fieldState = nextFieldState;
    }

    @Override
    public boolean shouldUpdate(final AttributeValueSet nextAttributeState) {
        return true;
    }

    boolean shouldUpdate(final FieldValueSet nextFieldState) {
        return fieldState == null && nextFieldState != null ||
            fieldState != null && nextFieldState == null ||
            (fieldState != null && !fieldState.equals(nextFieldState));
    }

    // TODO(skeswa): make updates happen in a parallel queue thread.
    @Override
    public void enqueueUpdate(final Update update) {
        if (composite == null) {
            throw new IllegalArgumentException("Only mounted components may be updated.");
        }

        // Localize update state.
        final Object listenerContext = update == null ? null : update.getListenerContext();
        final UpdateListener listener = update == null ? null : update.getListener();
        final Map<Field, Object> fieldValueMap = update == null ? null : update.getFieldValueMap();

        // Create flag that determines whether this component should be re-rendered.
        boolean willUpdate = false;

        // If this renderable is a Component, then perform field state transfer logic. Only update
        // in the event that Component#shouldUpdate evaluates true, or this is a stateless update.
        if (fieldValueMap != null) {
            final FieldValueSet oldFieldState = fieldState;
            final FieldValueSet nextFieldState = oldFieldState == null ? new FieldValueSet
                (fieldValueMap) : oldFieldState.extend(fieldValueMap);
            willUpdate = shouldUpdate(nextFieldState);
            setFields(nextFieldState);
        }

        // Make the composite re-render this renderable.
        if (willUpdate) {
            if (this != composite.getRenderableAtDepth(compositeReductionDepth)) {
                // TODO(skeswa): better exception.
                throw new RuntimeException("Composite reduction depth was incorrect.");
            }

            composite.update(compositeReductionDepth);
        }

        // Invoke the listener when appropriate.
        if (listener != null) {
            listener.onUpdate(listenerContext);
        }
    }

    @SuppressWarnings("unused")
    protected Element[] getChildren() {
        return children;
    }

    @SuppressWarnings("unused")
    protected <T> T valueOf(final Field<T> field) {
        return fieldState.valueOf(field);
    }

    @SuppressWarnings("unused")
    protected <T> T valueOf(final Attribute<T> attribute) {
        return attributeState.valueOf(attribute);
    }

    @SuppressWarnings("unused")
    protected final ElementBuilder $(final ElementBuilder elementBuilder) {
        return elementBuilder;
    }

    @SuppressWarnings("unused")
    protected final ElementChildArgumentList $(final ElementBuilder...elementBuilders) {
        if (elementBuilders == null || elementBuilders.length == 0) {
            return null;
        }

        return new ElementChildArgumentList(elementBuilders);
    }

    @SuppressWarnings("unused")
    protected final ElementChildArgumentList $(final Collection<ElementBuilder> elementBuilders) {
        if (elementBuilders == null || elementBuilders.size() == 0) {
            return null;
        }

        return new ElementChildArgumentList(elementBuilders);
    }

    @SuppressWarnings("unused")
    protected final ElementBuilder $(final Class<? extends Attributable> componentType) {
        // The component type is required.
        if (componentType == null) {
            // TODO(skeswa): standardize parameter null check exception messages.
            throw new IllegalArgumentException("Null is not a valid renderable type.");
        }

        return new ElementBuilder(componentType);
    }

    @Override
    public abstract Element render();
}
