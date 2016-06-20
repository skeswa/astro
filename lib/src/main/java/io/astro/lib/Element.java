package io.astro.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author skeswa
 */
public class Element implements ElementChildArgument {
    private final Element[] children;
    private final Map<Attribute, Object> attributes;
    private final Class<? extends Renderable> renderableType;
    private final Map<StyleAttribute, Object> styleAttributes;

    public Element(final Element[] children, final Map<Attribute, Object> attributes, final Class<? extends Renderable> renderableType, final Map<StyleAttribute, Object> styleAttributes) {
        this.children = children;
        this.attributes = attributes;
        this.renderableType = renderableType;
        this.styleAttributes = styleAttributes;
    }

    @Override
    public Element getElement() {
        return this;
    }

    @Override
    public List<? extends Element> getElements() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Element)) return false;

        final Element other = (Element) o;

        if (children == null && other.children != null) return false;
        if (children != null && other.children == null) return false;
        if (children != null && !Arrays.equals(children, other.children)) return false;

        if (attributes == null && other.attributes != null) return false;
        if (attributes != null && other.attributes == null) return false;
        if (attributes != null && !attributes.equals(other.attributes)) return false;

        if (renderableType == null && other.renderableType != null) return false;
        if (renderableType != null && other.renderableType == null) return false;
        if (renderableType != null && !renderableType.equals(other.renderableType)) return false;

        if (styleAttributes == null && other.styleAttributes != null) return false;
        if (styleAttributes != null && other.styleAttributes == null) return false;
        if (styleAttributes != null && !styleAttributes.equals(other.styleAttributes)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = 31 * hashCode + (children == null ? 0 : Arrays.hashCode(children));
        hashCode = 31 * hashCode + (attributes == null ? 0 : attributes.hashCode());
        hashCode = 31 * hashCode + (renderableType == null ? 0 : renderableType.hashCode());
        hashCode = 31 * hashCode + (styleAttributes == null ? 0 : styleAttributes.hashCode());

        return hashCode;
    }
}
