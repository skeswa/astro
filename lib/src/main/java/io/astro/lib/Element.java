package io.astro.lib;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author skeswa
 */
public class Element {
    static final Element EMPTY = new Element(
        0,
        null,
        new Element[0],
        new HashMap<Attribute, Object>(),
        null,
        new HashMap<StyleAttribute, Object>()
    );

    private final long key;
    private final String ref;
    private final Element[] children;
    private final AttributeValueSet attributes;
    private final StyleAttributeValueSet styleAttributes;
    private final Class<? extends Renderable> renderableType;

    public Element(
        final long key,
        final String ref,
        final Element[] children,
        final Map<Attribute, Object> attributes,
        final Class<? extends Renderable> renderableType,
        final Map<StyleAttribute, Object> styleAttributes
    ) {
        this.key = key;
        this.ref = ref;
        this.children = children;
        this.attributes = new AttributeValueSet(attributes);
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

    public Class<? extends Renderable> getRenderableType() {
        return renderableType;
    }

    int identifier() {
        return Util.hash(key, renderableType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Element)) return false;

        final Element other = (Element) o;

        return
            Util.equals(key, other.key) &&
            Util.equals(ref, other.ref) &&
            Arrays.equals(children, other.children) &&
            Util.equals(attributes, other.attributes) &&
            Util.equals(renderableType, other.renderableType) &&
            Util.equals(styleAttributes, other.styleAttributes);
    }

    @Override
    public int hashCode() {
        return Util.hash(key, ref, children, attributes, renderableType, styleAttributes);
    }
}
