package io.astro.lib;

/**
 * @author skeswa
 */
public interface Renderable extends ComponentType {
    void onMount();
    void onUnmount();

    void setChildren(final Element[] children);
    void setComposite(final ElementComposite composite);
    void setCompositeReductionDepth(final int compositeReductionDepth);

    void enqueueUpdate(final Update update);
    boolean shouldUpdate(final AttributeValueSet nextAttributeState);
    boolean shouldUpdate(final StyleAttributeValueSet nextStyleAttributeValueSet);

    Element render();
}
