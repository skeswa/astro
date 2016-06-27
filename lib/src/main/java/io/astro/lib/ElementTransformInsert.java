package io.astro.lib;

/**
 * @author skeswa
 */
public class ElementTransformInsert implements ElementTransform {
    private final Node parentNode;
    private final int index;
    private final Element element;

    public ElementTransformInsert(final Node parentNode, final int index, final Element element) {
        this.parentNode = parentNode;
        this.index = index;
        this.element = element;
    }

    @Override
    public void apply() {

    }
}
