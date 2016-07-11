package io.astro.lib;

import java.util.Map;

/**
 * @author skeswa
 */
public class Update {
    private final Object listenerContext;
    private final UpdateListener listener;
    private final Map<Field, Object> fieldValueMap;

    Update(
        final Object listenerContext,
        final UpdateListener listener,
        final Map<Field, Object> fieldValueMap
    ) {
        this.listenerContext = listenerContext;
        this.listener = listener;
        this.fieldValueMap = fieldValueMap;
    }

    Object getListenerContext() {
        return listenerContext;
    }

    UpdateListener getListener() {
        return listener;
    }

    Map<Field, Object> getFieldValueMap() {
        return fieldValueMap;
    }

    public static <T> UpdateBuilder set(Field<T> field, T value) {
        return new UpdateBuilder().set(field, value);
    }

    public static <T> UpdateBuilder listen(T context, final UpdateListener<T> listener) {
        return new UpdateBuilder().listen(context, listener);
    }

    public static void execute(final Component component) {
        new UpdateBuilder().execute(component);
    }
}
