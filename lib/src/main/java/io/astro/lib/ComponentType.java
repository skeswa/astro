package io.astro.lib;

import android.view.View;

/**
 * @author skeswa
 */
interface ComponentType {
    void onUnmount();

    void setAttributes(final AttributeValueSet nextAttributeState);
    void setStyleAttributes(final StyleAttributeValueSet nextStyleAttributeValueSet);
}
