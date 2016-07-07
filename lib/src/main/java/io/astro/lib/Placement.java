package io.astro.lib;

import android.view.View;

/**
 * @author skeswa
 */
public class Placement {
    private ElementComposite root;

    Placement(final Element element) {
        if (element == null) {
            throw new IllegalArgumentException("Null is not a valid Element.");
        }

        this.root = new ElementComposite(this, -1, null, element);
    }

    public View getView() {
        return this.root.getView();
    }
}