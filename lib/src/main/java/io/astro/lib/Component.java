package io.astro.lib;

import java.util.Collection;

/**
 * @author skeswa
 */
public abstract class Component extends BaseStatefulRenderable {

    protected final Element $(final Element element) {
        return element;
    }

    protected final ElementChildArgumentList $(final Element...elements) {
        if (elements == null || elements.length == 0) {
            return null;
        }

        return new ElementChildArgumentList(elements);
    }

    protected final ElementChildArgumentList $(final Collection<? extends Element> elements) {
        if (elements == null || elements.size() == 0) {
            return null;
        }

        return new ElementChildArgumentList(elements);
    }

    protected final ElementBuilder $(final Class<? extends Renderable> renderableType) {
        // The component type is required.
        if (renderableType == null) {
            // TODO(skeswa): standardize parameter null check exception messages.
            throw new IllegalArgumentException("Null is not a valid renderable type.");
        }

        return new ElementBuilder(renderableType);
    }
}
