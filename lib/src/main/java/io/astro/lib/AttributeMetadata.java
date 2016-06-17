package io.astro.lib;

import java.lang.reflect.Field;

/**
 * @author skeswa
 */
public class AttributeMetadata {
    private final String name;
    private final Class<?> type;
    private final Field field;

    public AttributeMetadata(String name, Class<?> type, Field field) {
        this.name = name;
        this.type = type;
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public Class<?> getType() {
        return type;
    }

    public Field getField() {
        return field;
    }
}
