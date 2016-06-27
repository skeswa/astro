package io.astro.lib;

import android.util.SparseArray;

/**
 * @author skeswa
 */
public class Root {
    private Node rootNode;
    private SparseArray<Renderable> renderableInstances;
    private SparseArray<Node> nodes;
    private int nextRenderableInstanceId = 1000;

    void update(Element rootElement) {

    }
}