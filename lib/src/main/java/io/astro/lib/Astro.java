package io.astro.lib;

import android.view.View;

/**
 * @author skeswa
 */
public class Astro {
    public static View render(final Element element) {
        return new Placement(element).getView();
    }
}
