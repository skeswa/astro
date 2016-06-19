package io.astro.lib;

import java.util.Collection;

/**
 * @author skeswa
 */
public class ElementStyleListArgument implements ElementArgument {
    private final Style[] stylesArray;
    private final Collection<Style> stylesCollection;

    ElementStyleListArgument(final Style[] styles) {
        this.stylesArray = styles;
        this.stylesCollection = null;
    }

    ElementStyleListArgument(final Collection<Style> styles) {
        this.stylesArray = null;
        this.stylesCollection = styles;
    }

    @Override
    public void bind(Element element) {
        if (stylesArray != null) {
            for (Style style : stylesArray) {
                element.declareStyle(style);
            }
        } else if (stylesCollection != null) {
            for (Style style : stylesCollection) {
                element.declareStyle(style);
            }
        }
    }
}
