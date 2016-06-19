package io.astro.lib;

/**
 * @author skeswa
 */
public class FieldValueAssignment<T> {
    private final Field<T> field;
    private final T value;

    FieldValueAssignment(final Field<T> field, final T value) {
        this.field = field;
        this.value = value;
    }
}
