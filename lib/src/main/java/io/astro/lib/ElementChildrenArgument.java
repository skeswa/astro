package io.astro.lib;

/**
 * @author skeswa
 */
public class ElementChildrenArgument implements ElementDeclarationArgument {
    private final ElementChild[] children;

    ElementChildrenArgument(ElementChild[] children) {
        this.children = children;
    }

    @Override
    public void bind(Element parent) {
        for (ElementChild child : children) {
            child.bind(parent);
        }
    }
}
