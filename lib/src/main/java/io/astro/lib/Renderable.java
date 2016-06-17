package io.astro.lib;

import java.util.List;

/**
 * @author skeswa
 */
public interface Renderable {
    void onMount();
    void onAttributeChanged(final Attribute<?> attribute, final Object newValue);
    void onChildrenChanged(final List<Element> children);
    void onUnmount();

    Element render();
}
