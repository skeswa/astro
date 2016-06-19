package io.astro.lib;

import java.util.ArrayList;

/**
 * @author skeswa
 */
public class Update {
    public static <T> UpdateBuilder set(Field<T> field, T value) {
        return new UpdateBuilder().set(field, value);
    }

    public static UpdateBuilder listen(final UpdateListener listener) {
        return new UpdateBuilder().listen(listener);
    }

    public static void execute(final Renderable renderable) {
        new UpdateBuilder().execute(renderable);
    }
}
