package io.astro.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author skeswa
 */
public class UpdateBuilder {
    private Object listenerContext;
    private UpdateListener listener;
    private Map<Field, Object> fieldValueMap;

    UpdateBuilder() {}

    public <T> UpdateBuilder set(final Field<T> field, final T value) {
        if (field == null) {
            throw new IllegalArgumentException("Null is not a valid field.");
        }

        if (fieldValueMap == null) {
            fieldValueMap = new HashMap<>();
        }

        fieldValueMap.put(field, value);

        return this;
    }

    public <T> UpdateBuilder listen(final T context, final UpdateListener<T> listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Null is not a valid listener.");
        }

        this.listener = listener;
        this.listenerContext = context;

        return this;
    }

    public void execute(final Renderable renderable) {
        // TODO(skeswa): implement this (the update queue).
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
