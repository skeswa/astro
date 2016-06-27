package io.astro.lib;

import java.util.Collection;

/**
 * @author skeswa
 */
public abstract class Component extends BaseStatefulRenderable {

    protected final ElementBuilder $(final ElementBuilder elementBuilder) {
        return elementBuilder;
    }

    protected final ElementChildArgumentList $(final ElementBuilder...elementBuilders) {
        if (elementBuilders == null || elementBuilders.length == 0) {
            return null;
        }

        return new ElementChildArgumentList(elementBuilders);
    }

    protected final ElementChildArgumentList $(final Collection<ElementBuilder> elementBuilders) {
        if (elementBuilders == null || elementBuilders.size() == 0) {
            return null;
        }

        return new ElementChildArgumentList(elementBuilders);
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
