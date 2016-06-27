package io.astro.lib;

import java.util.Map;
import java.util.Objects;

/**
 * @author skeswa
 */
public class StyleAttributeValueSet {
    private final Map<StyleAttribute, Object> styleAttributeValueMap;

    StyleAttributeValueSet(final Map<StyleAttribute, Object> styleAttributeValueMap) {
        this.styleAttributeValueMap = styleAttributeValueMap;
    }

    public boolean has(final StyleAttribute<?> styleAttribute) {
        return styleAttributeValueMap.containsKey(styleAttribute);
    }

    @SuppressWarnings("unchecked")
    public <T> T valueOf(final StyleAttribute<T> styleAttribute) {
        return (T) styleAttributeValueMap.get(styleAttribute);
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

        return Util.equals(styleAttributeValueMap, other.styleAttributeValueMap);
    }

    @Override
    public int hashCode() {
        return Util.hash(styleAttributeValueMap);
    }
}
