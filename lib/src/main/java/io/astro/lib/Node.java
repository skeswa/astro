package io.astro.lib;

/**
 * @author skeswa
 */
public class Node {
    private final int renderableInstanceId;
    private final Element element;
    private final Node[] children;

    public Node(int renderableInstanceId, Element element, Node[] children) {
        this.renderableInstanceId = renderableInstanceId;
        this.element = element;
        this.children = children;
    }

    public int getRenderableInstanceId() {
        return renderableInstanceId;
    }

    public Element getElement() {
        return element;
    }

    public Node[] getChildren() {
        return children;
    }
}
