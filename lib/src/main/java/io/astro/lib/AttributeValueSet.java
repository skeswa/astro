package io.astro.lib;

import java.util.Arrays;
import java.util.Map;

/**
 * @author skeswa
 */
public class AttributeValueSet {
    private final Map<Attribute, Object> attributeValueMap;

    AttributeValueSet(final Map<Attribute, Object> attributeValueMap) {
        this.attributeValueMap = attributeValueMap;
    }

    public boolean has(final Attribute<?> attribute) {
        return attributeValueMap.containsKey(attribute);
    }

    @SuppressWarnings("unchecked")
    public <T> T valueOf(final Attribute<T> attribute) {
        return (T) attributeValueMap.get(attribute);
    }

    Map<Attribute, Object> getAttributeValueMap() {
        return attributeValueMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof AttributeValueSet)) return false;

        final AttributeValueSet other = (AttributeValueSet) o;

        if (attributeValueMap == null && other.attributeValueMap != null) return false;
        if (attributeValueMap != null && other.attributeValueMap == null) return false;
        if (attributeValueMap != null && !attributeValueMap.equals(other.attributeValueMap)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = 31 * hashCode + (attributeValueMap == null ? 0 : attributeValueMap.hashCode());
        return hashCode;
    }
}
