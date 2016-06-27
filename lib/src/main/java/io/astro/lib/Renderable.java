package io.astro.lib;

/**
 * @author skeswa
 */
public interface Renderable {
    void onMount();
    void onUnmount();
    boolean isMounted();

    void setChildren(final Element[] children);
    void setAttributes(final AttributeValueSet nextAttributeState);
    void setStyleAttributes(final StyleAttributeValueSet nextStyleAttributeValueSet);

    boolean shouldUpdate(final AttributeValueSet nextAttributeState);
    boolean shouldUpdate(final StyleAttributeValueSet nextStyleAttributeValueSet);

    Element render();
}
