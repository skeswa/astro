package io.astro.lib;

import java.util.Arrays;
import java.util.Map;

/**
 * @author skeswa
 */
public class Element {
    private final long key;
    private final String ref;
    private final Element[] children;
    private final AttributeValueSet attributes;
    private final StyleAttributeValueSet styleAttributes;
    private final Class<? extends Viewable> viewableType;
    private final Class<? extends Renderable> renderableType;

    public Element(
        final long key,
        final String ref,
        final boolean isNative,
        final Element[] children,
        final Map<Attribute, Object> attributes,
        final Class<? extends Viewable> viewableType,
        final Class<? extends Renderable> renderableType,
        final Map<StyleAttribute, Object> styleAttributes
    ) {
        this.key = key;
        this.ref = ref;
        this.children = children;
        this.attributes = new AttributeValueSet(attributes);
        this.viewableType = viewableType;
        this.renderableType = renderableType;
        this.styleAttributes = new StyleAttributeValueSet(styleAttributes);
    }

    public long getKey() {
        return key;
    }

    public String getRef() {
        return ref;
    }

    Element[] getChildren() {
        return children;
    }

    public AttributeValueSet getAttributes() {
        return attributes;
    }

    public StyleAttributeValueSet getStyleAttributes() {
        return styleAttributes;
    }

    public Class<? extends Viewable> getViewableType() {
        return viewableType;
    }

    public Class<? extends Renderable> getRenderableType() {
        return renderableType;
    }

    int identifier() {
        return ObjectUtil.hash(key, renderableType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Element)) return false;

        final Element other = (Element) o;

        return
            ObjectUtil.equals(key, other.key) &&
            ObjectUtil.equals(ref, other.ref) &&
            Arrays.equals(children, other.children) &&
            ObjectUtil.equals(attributes, other.attributes) &&
            ObjectUtil.equals(renderableType, other.renderableType) &&
            ObjectUtil.equals(styleAttributes, other.styleAttributes);
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hash(key, ref, children, attributes, renderableType, styleAttributes);
    }
}
