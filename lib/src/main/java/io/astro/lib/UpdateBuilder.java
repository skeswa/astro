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

    public <T> UpdateBuilder listen(final T context, final UpdateListener<T> listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Null is not a valid listener.");
        }

        this.listener = listener;
        this.listenerContext = context;

        return this;
    }

    // TODO(skeswa): make updates happen in parallel.
    @SuppressWarnings("unchecked")
    public void execute(final Renderable renderable) {
        if (renderable == null) {
            throw new IllegalArgumentException("Null is not a valid renderable.");
        }

        final ElementComposite composite = renderable.getComposite();
        if (composite == null) {
            throw new IllegalArgumentException("Only mounted renderables may be updated.");
        }

        boolean shouldUpdate = true;

        // If this renderable is a Component, then perform field state transfer logic. Only update
        // in the event that Component#shouldUpdate evaluates true, or this is a stateless update.
        if (renderable instanceof Component && fieldValueMap != null) {
            final Component component = ((Component) renderable);

            final FieldValueSet oldFieldState = component.getFields();
            final FieldValueSet nextFieldState = oldFieldState == null ? new FieldValueSet
                (fieldValueMap) : oldFieldState.extend(fieldValueMap);
            shouldUpdate = component.shouldUpdate(nextFieldState);
            component.setFields(nextFieldState);
        }

        // Make the composite re-render this renderable.
        if (shouldUpdate) {
            if (renderable != composite.getRenderableAtDepth(renderable
                .getCompositeReductionDepth())) {
                // TODO(skeswa): better exception.
                throw new RuntimeException("Composite reduction depth was invalid.");
            }

            composite.update(renderable.getCompositeReductionDepth());
        }

        // Invoke the listener when appropriate.
        if (listener != null) {
            listener.onUpdate(listenerContext);
        }
    }
}
