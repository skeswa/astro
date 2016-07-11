package io.astro.lib;

import java.util.HashMap;
import java.util.Map;

/**
 * @author skeswa
 */
public abstract class NativeComponent implements Viewable {
    private static final StyleAttributeValueSet EMPTY_STYLE_ATTRIBUTE_STATE = new StyleAttributeValueSet(new HashMap<StyleAttribute, Object>());

    private AttributeValueSet attributeState = Component.EMPTY_ATTRIBUTE_STATE;
    private StyleAttributeValueSet styleAttributeState = EMPTY_STYLE_ATTRIBUTE_STATE;

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
        // TODO(skeswa): turn this into changes to a CSSNode.
        if (styleAttributeState == null) {
            for (final Map.Entry<StyleAttribute, Object> entry : nextStyleAttributeState.getStyleAttributeValueMap().entrySet()) {
//                onStyleAttributeValueChanged(entry.getKey(), entry.getValue());
            }
        } else {
            final Map<StyleAttribute, Object> styleAttributeValues = styleAttributeState.getStyleAttributeValueMap();
            final Map<StyleAttribute, Object> nextStyleAttributeValues = nextStyleAttributeState.getStyleAttributeValueMap();

            for (final Map.Entry<StyleAttribute, Object> entry : nextStyleAttributeValues.entrySet()) {
                if (!styleAttributeValues.containsKey(entry.getKey()) || !ObjectUtil.equals(entry.getValue(), styleAttributeValues.get(entry.getKey()))) {
//                    onStyleAttributeValueChanged(entry.getKey(), entry.getValue());
                }
            }

            for (final StyleAttribute key : styleAttributeValues.keySet()) {
                if (!nextStyleAttributeValues.containsKey(key)) {
//                    onStyleAttributeValueChanged(key, null);
                }
            }
        }

        styleAttributeState = nextStyleAttributeState;
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
