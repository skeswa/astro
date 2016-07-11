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
    private final boolean isStyleable;
    private final AttributeValueSet attributes;
    private final StyleAttributeValueSet styleAttributes;
    private final Class<? extends Viewable> viewableType;
    private final Class<? extends Renderable> renderableType;

    public Element(
        final long key,
        final String ref,
        final Element[] children,
        final boolean isStyleable,
        final Map<Attribute, Object> attributes,
        final Class<? extends Viewable> viewableType,
        final Class<? extends Renderable> renderableType,
        final Map<StyleAttribute, Object> styleAttributes
    ) {
        this.key = key;
        this.ref = ref;
        this.children = children;
        this.attributes = new AttributeValueSet(attributes);
        this.isStyleable = isStyleable;
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

    public boolean isStyleable() {
        return isStyleable;
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

    private StringBuilder stringify(int depth) {
        final StringBuilder builder = new StringBuilder();

        // Get the component type.
        String componentType;
        if (viewableType != null) {
            componentType = viewableType.getName();
        } else if (renderableType != null) {
            componentType = renderableType.getName();
        } else {
            componentType = "???";
        }

        // Opening tag.
        builder.append('<');
        builder.append(componentType);

        if (attributes.getAttributeValueMap().size() > 0) {
            int i = 1;
            for (final Map.Entry<Attribute, Object> entry : attributes.getAttributeValueMap().entrySet()) {
                builder.append(" attr");
                builder.append(i++);
                builder.append("={");
                builder.append(entry.getValue());
                builder.append('}');
            }
        }

        if (children != null && children.length > 0) {
            final int childDepth = depth + 1;

            builder.append('>');
            builder.append('\n');

            for (final Element child : children) {
                for (int i = 0; i < childDepth; i++) {
                    builder.append('\t');
                }

                builder.append(child.stringify(childDepth));
                builder.append('\n');
            }

            for (int i = 0; i < depth; i++) {
                builder.append('\t');
            }

            builder.append("</");
            builder.append(componentType);
            builder.append('>');
        } else {
            builder.append(" />");
        }

        return builder;
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

    @Override
    public String toString() {
        return stringify(0).toString();
    }
}
