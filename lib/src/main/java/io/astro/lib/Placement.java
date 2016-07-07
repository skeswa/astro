package io.astro.lib;

import android.content.Context;
import android.view.View;

/**
 * @author skeswa
 */
public class Placement {
    private Context context;
    private ElementComposite root;

    Placement(final Context context, final Element element) {
        if (element == null) {
            throw new IllegalArgumentException("Null is not a valid Element.");
        }

        this.context = context;
        this.root = new ElementComposite(this, -1, null, element);
    }

    public Context getContext() {
        return context;
    }

    public View getView() {
        return this.root.getView();
    }
}