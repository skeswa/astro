package io.astro.lib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author skeswa
 */
public class ElementBuilder implements ElementChildArgument {
    private List<Style> styles;
    private List<Element> children;
    private Map<Attribute, Object> attributeValueMap;
    private final Class<? extends Renderable> renderableType;

    ElementBuilder(final Class<? extends Renderable> renderableType) {
        this.renderableType = renderableType;
    }

    public <T> ElementBuilder attr(final Attribute<T> attribute, final T value) {
        if (attribute == null) {
            throw new IllegalArgumentException("Null is not a valid argument");
        }

        if (attributeValueMap == null) {
            attributeValueMap = new HashMap<>();
        }

        attributeValueMap.put(attribute, value);

        return this;
    }

    public ElementBuilder attrs(final Map<Attribute, Object> attrs) {
        if (attrs != null) {
            if (attributeValueMap == null) {
                attributeValueMap = new HashMap<>();
            }

            attributeValueMap.putAll(attrs);
        }

        return this;
    }

    public ElementBuilder style(final Style style) {
        if (style != null) {
            if (styles == null) {
                styles = new ArrayList<>();
            }

            styles.add(style);
        }

        return this;
    }

    public ElementBuilder styles(final Collection<Style> styles) {
        if (styles != null) {
            if (this.styles == null) {
                this.styles = new ArrayList<>();
            }

            this.styles.addAll(styles);
        }

        return this;
    }

    public ElementBuilder styles(final Style...styles) {
        if (styles != null) {
            if (this.styles == null) {
                this.styles = new ArrayList<>();
            }

            for (Style style : styles) {
                if (style != null) {
                    this.styles.add(style);
                }
            }
        }

        return this;
    }

    public ElementBuilder children(final ElementChildArgument...args) {
        if (args != null) {
            if (children == null) {
                children = new ArrayList<>();
            }

            for (final ElementChildArgument arg : args) {
                final Element element = arg.getElement();

                if (element != null) {
                    children.add(element);
                }
            }
        }

        return this;
    }

    public Element create() {
        Element[] children = null;
        if (this.children != null && this.children.size() > 0) {
            children = this.children.toArray(new Element[this.children.size()]);
        }

        Map<StyleAttribute, Object> styleAttributes = null;
        if (styles != null && styles.size() > 0) {
            styleAttributes = new HashMap<>();

            for (final Style style : styles) {
                styleAttributes.putAll(style.getAttributes());
            }
        }

        return new Element(children, attributeValueMap, renderableType, styleAttributes);
    }

    @Override
    public Element getElement() {
        return create();
    }

    @Override
    public List<? extends Element> getElements() {
        return null;
    }
}
