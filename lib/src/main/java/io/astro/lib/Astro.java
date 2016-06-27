package io.astro.lib;

import android.view.View;

/**
 * @author skeswa
 */
public class Astro {
    public static View render(final Element element) {
        Renderable component;
        try {
            component = (Renderable) element.getRenderableType().newInstance();
        } catch (Throwable e) {
            throw new RuntimeException("Could create a new instance of " + element.getRenderableType().toString(), e);
        }

        // Perform the first renderable.
        component.setStyleAttributes(element.getStyleAttributes());
        component.setAttributes(element.getAttributes());
        component.setChildren(element.getChildren());
        final Element componentOutput = component.render();

        // TODO(skeswa): this.
        return null;
    }
}
