package io.astro.lib;

import io.astro.lib.style.csslayout.CSSNode;

/**
 * @author skeswa
 */
public interface Styleable extends Viewable {
    CSSNode getCSSNode();
    void setStyleAttributes(final StyleAttributeValueSet nextStyleAttributeState);
}
