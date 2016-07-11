package io.astro.lib;

import java.util.Arrays;

/**
 * @author skeswa
 */
public abstract class BaseRenderable implements Renderable {
    private Element[] children;
    private AttributeValueSet attributeState;
    private StyleAttributeValueSet styleAttributeState;

    @Override
    public void onMount() {}

    @Override
    public void setChildren(final Element[] children) {
        if (!Arrays.equals(this.children, children)) {
            this.children = children;
        }
    }

    @Override
    public void setAttributes(final AttributeValueSet nextAttributeState) {
        // Make sure that this renderable is cleared for an reduce.
        if (shouldUpdate(nextAttributeState)) {
            attributeState = nextAttributeState;
        }
    }

    @Override
    public void setStyleAttributes(final StyleAttributeValueSet nextStyleAttributeState) {
        // Make sure that this renderable is cleared for an reduce.
        if (shouldUpdate(nextStyleAttributeState)) {
            styleAttributeState = nextStyleAttributeState;
        }
    }

    @Override
    public boolean shouldUpdate(final AttributeValueSet nextAttributeState) {
        return !ObjectUtil.equals(attributeState, nextAttributeState);
    }

    @Override
    public boolean shouldUpdate(final StyleAttributeValueSet nextStyleAttributeValueSet) {
        return !ObjectUtil.equals(styleAttributeState, nextStyleAttributeValueSet);
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

    protected <T> T valueOf(final StyleAttribute<T> styleAttribute) {
        if (styleAttribute == null) {
            throw new IllegalArgumentException("Null is not a valid style attribute.");
        }

        if (styleAttributeState == null || !styleAttributeState.has(styleAttribute)) {
            return styleAttribute.getDefaultValue();
        }

        return styleAttributeState.valueOf(styleAttribute);
    }

    protected Element[] getChildren() {
        if (children == null) return new Element[0];

        return children.clone();
    }
}
