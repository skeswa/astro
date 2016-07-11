package io.astro.lib;

import io.astro.lib.style.csslayout.CSSNode;

/**
 * @author skeswa
 */
public interface Styleable {
    CSSNode getCSSNode();
    void setStyleAttributes(final StyleAttributeValueSet nextStyleAttributeState);
}
