package io.astro.lib;

import io.astro.lib.style.csslayout.CSSNode;

/**
 * @author skeswa
 */
public interface StyleAttributeApplier<T> {
    void apply(final CSSNode node, final T value);
}
