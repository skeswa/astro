package io.astro.lib;

/**
 * @author skeswa
 */
public class Field<T> {
    public static <T> Field<T> create(Class<T> type) {
        return new Field<T>(type);
    }

    public static <T> Field<T> create(Class<T> type, T defaultValue) {
        return new Field<T>(type, defaultValue);
    }

    private final Class<T> type;
    private final T defaultValue;

    private Field(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null.");
        }

        this.type = type;
        this.defaultValue = null;
    }

    private Field(Class<T> type, T defaultValue) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null.");
        }

        if (defaultValue == null) {
            throw new IllegalArgumentException("Default value cannot be null.");
        }

        this.type = type;
        this.defaultValue = defaultValue;
    }

    public Class<T> getType() {
        return type;
    }

    public T getDefaultValue() {
        return defaultValue;
    }
}
