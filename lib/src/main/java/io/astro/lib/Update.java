package io.astro.lib;

/**
 * @author skeswa
 */
public class Update {
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
