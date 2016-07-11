package io.astro.lib;

import java.util.HashMap;
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

    FieldValueSet extend(final Map<Field, Object> fieldStateChanges) {
        if (fieldStateChanges == null) {
            return this;
        }

        final Map<Field, Object> newFieldValueMap = new HashMap<>(fieldValueMap);
        newFieldValueMap.putAll(fieldStateChanges);

        return new FieldValueSet(newFieldValueMap);
    }

    @SuppressWarnings("unchecked")
    public <T> T valueOf(final Field<T> field) {
        if (field == null) {
            throw new IllegalArgumentException("Null is not a valid field.");
        }

        final T value = (T) fieldValueMap.get(field);

        if (value == null) {
            return field.getDefaultValue();
        }

        return value;
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

        return ObjectUtil.equals(fieldValueMap, other.fieldValueMap);
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hash(fieldValueMap);
    }
}
