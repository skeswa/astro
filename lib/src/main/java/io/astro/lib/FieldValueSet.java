package io.astro.lib;

import java.util.Map;

/**
 * @author skeswa
 */
public class FieldValueSet {
    private final Map<Field, Object> fieldValueMap;

    FieldValueSet(final Map<Field, Object> fieldValueMap) {
        this.fieldValueMap = fieldValueMap;
    }

    public boolean has(final Field<?> field) {
        return fieldValueMap.containsKey(field);
    }

    @SuppressWarnings("unchecked")
    public <T> T valueOf(final Field<T> field) {
        return (T) fieldValueMap.get(field);
    }

    Map<Field, Object> getFieldValueMap() {
        return fieldValueMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof FieldValueSet)) return false;

        final FieldValueSet other = (FieldValueSet) o;

        if (fieldValueMap == null && other.fieldValueMap != null) return false;
        if (fieldValueMap != null && other.fieldValueMap == null) return false;
        if (fieldValueMap != null && !fieldValueMap.equals(other.fieldValueMap)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = 31 * hashCode + (fieldValueMap == null ? 0 : fieldValueMap.hashCode());
        return hashCode;
    }
}
