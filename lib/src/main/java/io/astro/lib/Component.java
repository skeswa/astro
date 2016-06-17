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
    private Map<Attribute<?>, Object> attributeValueMap = new HashMap<>();

    @Override
    public void onMount() {
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

    protected final Element $(final Class<? extends Renderable> componentType, final ElementDeclarationArgument... args) {
        // The component type is required.
        if (componentType == null) {
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
        for (ElementDeclarationArgument arg : args) {
            if (arg != null) {
                arg.bind(element);
            }
        }

        return element;
    }
}
