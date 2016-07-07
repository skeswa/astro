package io.astro.lib;

import java.util.*;

/**
 * @author skeswa
 */
public class ElementBuilder implements ElementChildArgument {
    private static final long RESERVED_KEYS_COUNT = 10000;

    private Long key;
    private String ref;
    private boolean isNative;
    private List<Style> styles;
    private List<ElementBuilder> children;
    private Map<Attribute, Object> attributeValueMap;
    private final Class<? extends Viewable> viewableType;
    private final Class<? extends Renderable> renderableType;

    @SuppressWarnings("unchecked")
    ElementBuilder(final Class<? extends ComponentType> componentType) {
        if (componentType == null) {
            throw new IllegalArgumentException("Null is not a valid component type");
        }

        if (Renderable.class.isAssignableFrom(componentType)) {
            this.viewableType = null;
            this.renderableType = (Class<? extends Renderable>) componentType;
        } else if (Viewable.class.isAssignableFrom(componentType)) {
            this.viewableType = (Class<? extends Viewable>) componentType;
            this.renderableType = null;
        }

        throw new IllegalArgumentException("Component type must implement Renderable or Viewable");
    }

    public <T> ElementBuilder ref(final String ref) {
        if (ref == null) {
            throw new IllegalArgumentException("Null is not a valid ref");
        }

        this.ref = ref;

        return this;
    }

    public <T> ElementBuilder key(final long key) {
        if (key < 0) {
            throw new IllegalArgumentException("Keys must be non-negative");
        }

        if (key > Long.MAX_VALUE - RESERVED_KEYS_COUNT) {
            throw new IllegalArgumentException("Keys must be less than " + (Long.MAX_VALUE - RESERVED_KEYS_COUNT));
        }

        this.key = key + RESERVED_KEYS_COUNT;

        return this;
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
                final ElementBuilder element = arg.getElementBuilder();

                if (element != null) {
                    children.add(element);
                }
            }
        }

        return this;
    }

    public Element create() {
        long keyIndex = 0;
        Element[] children = null;
        if (this.children != null && this.children.size() > 0) {
            children = new Element[this.children.size()];
            for (int i = 0; i < this.children.size(); i++) {
                final ElementBuilder elementBuilder = this.children.get(i);
                if (elementBuilder.key == null) {
                    elementBuilder.key = keyIndex++;
                }

                children[i] = elementBuilder.create();
            }
        }

        Map<StyleAttribute, Object> styleAttributes = null;
        if (styles != null && styles.size() > 0) {
            styleAttributes = new HashMap<>();

            for (final Style style : styles) {
                styleAttributes.putAll(style.getAttributes());
            }
        }

        return new Element(
            key,
            ref,
            isNative,
            children,
            attributeValueMap,
            viewableType,
            renderableType,
            styleAttributes
        );
    }

    @Override
    public ElementBuilder getElementBuilder() {
        return this;
    }

    @Override
    public List<ElementBuilder> getElementBuilders() {
        return null;
    }
}
