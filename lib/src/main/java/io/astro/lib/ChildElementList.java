package io.astro.lib;

import java.util.Collection;

/**
 * @author skeswa
 */
public class ChildElementList implements ElementChild {
    private final Element[] elementsArray;
    private final Collection<? extends Element> elementsCollection;

    ChildElementList(Element[] elements) {
        this.elementsArray = elements;
        this.elementsCollection = null;
    }

    ChildElementList(Collection<? extends Element> elements) {
        this.elementsArray = null;
        this.elementsCollection = elements;
    }

    @Override
    public void bind(Element parent) {
        if (elementsArray != null) {
            for (Element child : elementsArray) {
                parent.declareChildElement(child);
            }
        } else {
            for (Element child : elementsCollection) {
                parent.declareChildElement(child);
            }
        }
    }
}
