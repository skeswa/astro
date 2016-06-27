package io.astro.lib;

/**
 * @author skeswa
 */
public class ElementTransformRemove implements ElementTransform {
    private final Node parentNode;
    private final int index;
    private final Node childNode;

    public ElementTransformRemove(final Node parentNode, final int index, final Node childNode) {
        this.parentNode = parentNode;
        this.index = index;
        this.childNode = childNode;
    }

    @Override
    public void apply() {

    }
}
