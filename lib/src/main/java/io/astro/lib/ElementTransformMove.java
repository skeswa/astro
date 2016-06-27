package io.astro.lib;

/**
 * @author skeswa
 */
public class ElementTransformMove implements ElementTransform {
    private final Node parentNode;
    private final int fromIndex;
    private final int toIndex;

    public ElementTransformMove(
        final Node parentNode,
        final int fromIndex,
        final int toIndex
    ) {
        this.parentNode = parentNode;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }

    @Override
    public void apply() {

    }
}
