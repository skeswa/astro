package io.astro.lib;

/**
 * @author skeswa
 */
public interface Renderable extends ComponentType {
    void onMount();
    void onUnmount();

    int getCompositeReductionDepth();
    void setCompositeReductionDepth(final int compositeReductionDepth);

    ElementComposite getComposite();
    void setComposite(final ElementComposite composite);

    void setChildren(final Element[] children);

    boolean shouldUpdate(final AttributeValueSet nextAttributeState);
    boolean shouldUpdate(final StyleAttributeValueSet nextStyleAttributeValueSet);

    Element render();
}
