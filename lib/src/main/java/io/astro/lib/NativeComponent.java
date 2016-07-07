package io.astro.lib;

import java.util.Map;

/**
 * @author skeswa
 */
public abstract class NativeComponent implements Viewable {
    private AttributeValueSet attributeState;
    private StyleAttributeValueSet styleAttributeState;

    @Override
    public void setAttributes(final AttributeValueSet nextAttributeState) {
        if (attributeState == null) {
            for (final Map.Entry<Attribute, Object> entry : nextAttributeState.getAttributeValueMap().entrySet()) {
                onAttributeValueChanged(entry.getKey(), entry.getValue());
            }
        } else {
            final Map<Attribute, Object> attributeValues = attributeState.getAttributeValueMap();
            final Map<Attribute, Object> nextAttributeValues = nextAttributeState.getAttributeValueMap();

            for (final Map.Entry<Attribute, Object> entry : nextAttributeValues.entrySet()) {
                if (!attributeValues.containsKey(entry.getKey()) || !ObjectUtil.equals(entry.getValue(), attributeValues.get(entry.getKey()))) {
                    onAttributeValueChanged(entry.getKey(), entry.getValue());
                }
            }

            for (final Attribute key : attributeValues.keySet()) {
                if (!nextAttributeValues.containsKey(key)) {
                    onAttributeValueChanged(key, null);
                }
            }
        }

        attributeState = nextAttributeState;
    }

    @Override
    public void setStyleAttributes(final StyleAttributeValueSet nextStyleAttributeState) {
        if (styleAttributeState == null) {
            for (final Map.Entry<StyleAttribute, Object> entry : nextStyleAttributeState.getStyleAttributeValueMap().entrySet()) {
                onStyleAttributeValueChanged(entry.getKey(), entry.getValue());
            }
        } else {
            final Map<StyleAttribute, Object> styleAttributeValues = styleAttributeState.getStyleAttributeValueMap();
            final Map<StyleAttribute, Object> nextStyleAttributeValues = nextStyleAttributeState.getStyleAttributeValueMap();

            for (final Map.Entry<StyleAttribute, Object> entry : nextStyleAttributeValues.entrySet()) {
                if (!styleAttributeValues.containsKey(entry.getKey()) || !ObjectUtil.equals(entry.getValue(), styleAttributeValues.get(entry.getKey()))) {
                    onStyleAttributeValueChanged(entry.getKey(), entry.getValue());
                }
            }

            for (final StyleAttribute key : styleAttributeValues.keySet()) {
                if (!nextStyleAttributeValues.containsKey(key)) {
                    onStyleAttributeValueChanged(key, null);
                }
            }
        }

        styleAttributeState = nextStyleAttributeState;
    }

    protected abstract void onAttributeValueChanged(final Attribute attribute, Object value);

    protected void onStyleAttributeValueChanged(final StyleAttribute styleAttribute, Object value) {}

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
}
