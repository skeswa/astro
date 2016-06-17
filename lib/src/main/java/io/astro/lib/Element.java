package io.astro.lib;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skeswa
 */
public class Element implements ElementChild {
    private Element parent;
    private List<Element> children;
    private List<ElementAttributeArgument> attributes;
    private final Class<? extends Renderable> renderableType;

    Element(Class<? extends Renderable> renderableType) {
        this.renderableType = renderableType;
    }

    void declareAttributeValueAssignment(final ElementAttributeArgument assignment) {
        if (assignment == null){
            return;
        }

        if (attributes == null) {
            attributes = new ArrayList<>();
        }

        attributes.add(assignment);
    }

    void declareChildElement(final Element child) {
        if (child == null){
            return;
        }

        if (children == null) {
            children = new ArrayList<>();
        }

        children.add(child);
    }

    List<Element> getChildren() {
        return children;
    }

    List<ElementAttributeArgument> getAttributes() {
        return attributes;
    }

    Class<? extends Renderable> getRenderableType() {
        return renderableType;
    }

    @Override
    public void bind(final Element parent) {
        parent.declareChildElement(this);
        this.parent = parent;
    }
}
