package io.astro.lib;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skeswa
 */
public class Element implements ElementChild {
    private Element parent;
    private List<Style> styles;
    private List<Element> children;
    private List<ElementAttributeArgument> attributes;
    private final Class<? extends Renderable> renderableType;

    Element(Class<? extends Renderable> renderableType) {
        this.renderableType = renderableType;
    }

    void declareAttribute(final ElementAttributeArgument attribute) {
        if (attribute == null){
            return;
        }

        if (attributes == null) {
            attributes = new ArrayList<>();
        }

        attributes.add(attribute);
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

    void declareStyle(final Style style) {
        if (style == null){
            return;
        }

        if (styles == null) {
            styles = new ArrayList<>();
        }

        styles.add(style);
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

    public List<Style> getStyles() {
        return styles;
    }

    @Override
    public void bind(final Element parent) {
        parent.declareChildElement(this);
        this.parent = parent;
    }
}
