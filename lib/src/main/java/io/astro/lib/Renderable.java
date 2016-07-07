package io.astro.lib;

/**
 * @author skeswa
 */
public interface Renderable extends ComponentType {
    void onMount();

    void setRoot(final Placement placement);
    void setChildren(final Element[] children);

    boolean shouldUpdate(final AttributeValueSet nextAttributeState);
    boolean shouldUpdate(final StyleAttributeValueSet nextStyleAttributeValueSet);

    Element render();
}
