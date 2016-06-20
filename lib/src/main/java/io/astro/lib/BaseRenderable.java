package io.astro.lib;

import java.util.Arrays;

/**
 * @author skeswa
 */
public abstract class BaseRenderable implements Renderable {
    private Element[] children;
    private AttributeValueSet attributeState;

    @Override
    public void onMount() {}

    @Override
    public void setChildren(final Element[] children) {
        if (!Arrays.equals(this.children, children)) {
            this.children = children;
            // TODO(skeswa): re-render.
        }
    }

    @Override
    public void setAttributes(final AttributeValueSet nextAttributeState) {
        // Make sure that this renderable is cleared for an update.
        if (shouldUpdate(nextAttributeState)) {
            attributeState = nextAttributeState;
            // TODO(skeswa): re-render.
        }
    }

    @Override
    public boolean shouldUpdate(final AttributeValueSet nextAttributeState) {
        return attributeState == null && nextAttributeState != null ||
            attributeState != null && nextAttributeState == null ||
            (attributeState != null && !attributeState.equals(nextAttributeState));
    }

    @Override
    public void onUnmount() {}

    @Override
    public abstract Element render();

    protected <T> T valueOf(final Attribute<T> attribute) {
        if (attribute == null) {
            throw new IllegalArgumentException("Null is not a valid attribute.");
        }

        if (attributeState == null || !attributeState.has(attribute)) {
            return attribute.getDefaultValue();
        }

        return attributeState.valueOf(attribute);
    }

    protected Element[] getChildren() {
        if (children == null) return new Element[0];

        return children.clone();
    }
}
