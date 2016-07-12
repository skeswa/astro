package io.astro.lib;

import java.util.Map;

/**
 * @author skeswa
 */
public abstract class NativeComponent implements Viewable {
    private AttributeValueSet attributeState = Component.EMPTY_ATTRIBUTE_STATE;

    @Override
    public void setAttributes(final AttributeValueSet nextAttributeState) {
        if (attributeState == Component.EMPTY_ATTRIBUTE_STATE) {
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

    protected abstract void onAttributeValueChanged(final Attribute attribute, Object value);

    protected <T> T valueOf(final Attribute<T> attribute) {
        if (attribute == null) {
            throw new IllegalArgumentException("Null is not a valid attribute.");
        }

        if (attributeState == null || !attributeState.has(attribute)) {
            return attribute.getDefaultValue();
        }

        return attributeState.valueOf(attribute);
    }
}
