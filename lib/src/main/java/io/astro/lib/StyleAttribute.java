package io.astro.lib;

/**
 * @author skeswa
 */
public class StyleAttribute<T> {
    public static <T> StyleAttribute<T> create(Class<T> type) {
        return new StyleAttribute<T>(type);
    }

    public static <T> StyleAttribute<T> create(Class<T> type, T defaultValue) {
        return new StyleAttribute<T>(type, defaultValue);
    }

    private final Class<T> type;
    private final T defaultValue;

    private StyleAttribute(Class<T> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null.");
        }

        this.type = type;
        this.defaultValue = null;
    }

    private StyleAttribute(Class<T> type, T defaultValue) {
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
