package io.astro.lib;

import java.util.HashMap;
import java.util.Map;

/**
 * @author skeswa
 */
public class UpdateBuilder {
    private Object listenerContext;
    private UpdateListener listener;
    private Map<Field, Object> fieldValueMap;

    UpdateBuilder() {
    }

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

    public UpdateBuilder listen(final Object context, final UpdateListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Null is not a valid listener.");
        }

        this.listener = listener;
        this.listenerContext = context;

        return this;
    }

    public Update create() {
        return new Update(listenerContext, listener, fieldValueMap);
    }

    @SuppressWarnings("unchecked")
    public void execute(final Renderable renderable) {
        if (renderable == null) {
            throw new IllegalArgumentException("Null is not a valid renderable.");
        }

        renderable.enqueueUpdate(new Update(listenerContext, listener, fieldValueMap));
    }
}
