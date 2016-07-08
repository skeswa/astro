package io.astro.lib;

import java.util.HashMap;
import java.util.Map;

/**
 * @author skeswa
 */
public class StyleAttributeValueSet {
    private final Map<StyleAttribute, Object> styleAttributeValueMap;

    StyleAttributeValueSet(final Map<StyleAttribute, Object> styleAttributeValueMap) {
        if (styleAttributeValueMap == null) {
            this.styleAttributeValueMap = new HashMap<>();
        } else {
            this.styleAttributeValueMap = styleAttributeValueMap;
        }
    }

    public boolean has(final StyleAttribute<?> styleAttribute) {
        return styleAttributeValueMap.containsKey(styleAttribute);
    }

    @SuppressWarnings("unchecked")
    public <T> T valueOf(final StyleAttribute<T> styleAttribute) {
        if (styleAttribute == null) {
            throw new IllegalArgumentException("Null is not a valid style attribute.");
        }

        final T value = (T) styleAttributeValueMap.get(styleAttribute);

        if (value == null) {
            return styleAttribute.getDefaultValue();
        }

        return value;
    }

    Map<StyleAttribute, Object> getStyleAttributeValueMap() {
        return styleAttributeValueMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof StyleAttributeValueSet)) return false;

        final StyleAttributeValueSet other = (StyleAttributeValueSet) o;

        return ObjectUtil.equals(styleAttributeValueMap, other.styleAttributeValueMap);
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hash(styleAttributeValueMap);
    }
}
