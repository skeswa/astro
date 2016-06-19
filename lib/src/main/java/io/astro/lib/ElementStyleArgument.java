package io.astro.lib;

/**
 * @author skeswa
 */
public class ElementStyleArgument implements ElementArgument {
    private final Style style;

    ElementStyleArgument(Style style) {
        this.style = style;
    }

    @Override
    public void bind(Element element) {
        element.declareStyle(this);
    }
}
