package io.astro.lib;

/**
 * @author skeswa
 */
public class Attribute<T> {
    public static <T> Attribute<T> create(Class<T> type) {
        return new Attribute<T>(type);
    }

    public static <T> Attribute<T> create(Class<T> type, T defaultValue) {
        return new Attribute<T>(type, defaultValue);
    }

    private final Class<T> type;
    private final T defaultValue;

    private Attribute(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null.");
        }

        this.type = type;
        this.defaultValue = null;
    }

    private Attribute(Class<T> type, T defaultValue) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null.");
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
