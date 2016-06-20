package io.astro.lib;

import java.util.List;
import java.util.Map;

/**
 * @author skeswa
 */
public interface Renderable {
    void onMount();
    void setChildren(final Element[] children);
    void setAttributes(final AttributeValueSet nextAttributeState);
    boolean shouldUpdate(final AttributeValueSet nextAttributeState);
    void onUnmount();

    Element render();
}
