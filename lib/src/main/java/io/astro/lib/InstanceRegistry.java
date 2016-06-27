package io.astro.lib;

import android.util.SparseArray;

/**
 * @author skeswa
 */
public class InstanceRegistry {
    private static final SparseArray<Object> instances = new SparseArray<>();
    private static int nextRootInstanceId = 0;
    private static int nextRenderableInstanceId = 1;

    private int nextRootInstanceId() {
        final int id = nextRootInstanceId;
        nextRootInstanceId += 10;
        return id;
    }

    private int nextRenderableInstanceId() {
        final int id = nextRenderableInstanceId;

        // Ids that are also multiples of ten are reserved for roots.
        final int increment = (nextRenderableInstanceId % 10 == 0) ? 2 : 1;
        nextRenderableInstanceId += increment;

        return id;
    }

    int register(final Renderable instance) {
        final int id = nextRootInstanceId();
        instances.put(id, instance);
        return id;
    }

    int register(final Root instance) {
        final int id = nextRootInstanceId();
        instances.put(id, instance);
        return id;
    }

    Renderable getRenderable(final int id) {
        return (Renderable) instances.get(id);
    }

    Root getRoot(final int id) {
        return (Root) instances.get(id);
    }
}
