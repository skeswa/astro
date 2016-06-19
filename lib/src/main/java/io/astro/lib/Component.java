package io.astro.lib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author skeswa
 */
public abstract class Component implements Renderable {
    private List<Element> children;
    private Map<Field<?>, Object> fieldValueMap;
    private Map<Attribute<?>, Object> attributeValueMap;

    @Override
    public void onMount() {
        fieldValueMap = new HashMap<>();
        attributeValueMap = new HashMap<>();
    }

    @Override
    public void onAttributeChanged(final Attribute<?> attribute, final Object newValue) {
        final Object oldValue = attributeValueMap.get(attribute);
        onAttributeChanged(attribute, oldValue, newValue);
        attributeValueMap.put(attribute, newValue);
    }

    public void onAttributeChanged(Attribute<?> attribute, Object oldValue, Object newValue) {}

    @Override
    public void onChildrenChanged(final List<Element> children) {
        this.children = children;
    }

    @Override
    public void onUnmount() {
        fieldValueMap = null;
        attributeValueMap = null;
    }

    @Override
    public abstract Element render();

    protected final <T> ElementAttributeArgument<T> attr(final Attribute<T> attr, final T value) {
        return new ElementAttributeArgument<T>(attr, value);
    }

    @SuppressWarnings("unchecked")
    protected final ElementAttributeListArgument attrs(final Map<Attribute, Object> attrs) {
        if (attrs == null) {
            return null;
        }

        final Set<Entry<Attribute, Object>> entrySet = attrs.entrySet();
        final ElementAttributeArgument[] assignments = new ElementAttributeArgument[entrySet.size()];
        int i = 0;
        for (Entry<Attribute, Object> entry : entrySet) {
            assignments[i++] = new ElementAttributeArgument(entry.getKey(), entry.getValue());
        }

        return new ElementAttributeListArgument(assignments);
    }

    protected final ElementStyleArgument style(final Style style) {
        return new ElementStyleArgument(style);
    }

    protected final ElementStyleListArgument styles(final Collection<Style> styles) {
        if (styles == null) {
            return null;
        }

        return new ElementStyleListArgument(styles);
    }

    protected final ElementStyleListArgument styles(final Style...styles) {
        if (styles == null) {
            return null;
        }

        return new ElementStyleListArgument(styles);
    }

    protected final ElementChildrenArgument children(ElementChild... elementChildren) {
        return new ElementChildrenArgument(elementChildren);
    }

    protected final Element $(final Element element) {
        return element;
    }

    protected final ChildElementList $(final Element[] elements) {
        if (elements == null || elements.length == 0) {
            return null;
        }

        return new ChildElementList(elements);
    }

    protected final ChildElementList $(final Collection<? extends Element> elements) {
        if (elements == null || elements.size() == 0) {
            return null;
        }

        return new ChildElementList(elements);
    }

    protected final Element $(final Class<? extends Renderable> componentType, final ElementArgument... args) {
        // The component type is required.
        if (componentType == null) {
            // TODO(skeswa): standardize parameter null check exception messages.
            throw new IllegalArgumentException("The specified component type was null.");
        }

        // Create the element that will eventually get returned.
        final Element element = new Element(componentType);

        // If there are no args, none of the rest of this matters that much.
        if (args == null || args.length == 0) {
            return element;
        }

        // Bind all of the assignments.
        final List<ElementAttributeArgument> assignments = new ArrayList<>(args.length);
        for (ElementArgument arg : args) {
            if (arg != null) {
                arg.bind(element);
            }
        }

        return element;
    }

    @SuppressWarnings("unchecked")
    protected final <T> T valueOf(final Attribute<T> attribute) {
        if (attribute == null) {
            throw new IllegalArgumentException("Null is not a valid attribute.");
        }

        if (attributeValueMap == null) {
            throw new IllegalStateException("Component is not mounted.");
        }

        if (!attributeValueMap.containsKey(attribute)) {
            attributeValueMap.put(attribute, attribute.getDefaultValue());
        }

        return (T) attributeValueMap.get(attribute);
    }

    @SuppressWarnings("unchecked")
    protected final <T> T valueOf(final Field<T> field) {
        if (field == null) {
            throw new IllegalArgumentException("Null is not a valid field.");
        }

        if (fieldValueMap == null) {
            throw new IllegalStateException("Component is not mounted.");
        }

        if (!fieldValueMap.containsKey(field)) {
            fieldValueMap.put(field, field.getDefaultValue());
        }

        return (T) fieldValueMap.get(field);
    }
}
