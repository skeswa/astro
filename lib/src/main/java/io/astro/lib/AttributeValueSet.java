package io.astro.lib;

import java.util.HashMap;
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

        return ObjectUtil.equals(attributeValueMap, other.attributeValueMap);
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hash(attributeValueMap);
    }
}
