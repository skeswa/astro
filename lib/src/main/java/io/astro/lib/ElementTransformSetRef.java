package io.astro.lib;

/**
 * @author skeswa
 */
public class ElementTransformSetRef implements ElementTransform {
    private final String newRef;
    private final Node node;

    public ElementTransformSetRef(
        final Node node,
        final String newRef
    ) {
        this.node = node;
        this.newRef = newRef;
    }

    @Override
    public void apply() {

    }
}
