package io.astro.lib;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skeswa
 */
public class UpdateBuilder {
    private UpdateListener listener;
    private List<FieldValueAssignment<?>> values;

    UpdateBuilder() {}

    public <T> UpdateBuilder set(Field<T> field, T value) {
        if (field == null) {
            throw new IllegalArgumentException("Null is not a valid field.");
        }

        if (values == null) {
            values = new ArrayList<>();
        }

        values.add(new FieldValueAssignment<T>(field, value));

        return this;
    }

    public UpdateBuilder listen(final UpdateListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Null is not a valid listener.");
        }

        this.listener = listener;

        return this;
    }

    public void execute(final Renderable renderable) {
        // TODO(skeswa): implement this (the update queue).
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
