package io.astro.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author skeswa
 */
public class ElementChildArgumentList implements ElementChildArgument {
    private final ElementChildArgument[] elementsArray;
    private final Collection<? extends Element> elementsCollection;

    ElementChildArgumentList(Element[] elements) {
        this.elementsArray = elements;
        this.elementsCollection = null;
    }

    ElementChildArgumentList(Collection<? extends Element> elements) {
        this.elementsArray = null;
        this.elementsCollection = elements;
    }

    @Override
    public Element getElement() {
        return null;
    }

    @Override
    public List<? extends Element> getElements() {
        if (elementsArray != null) {
            final List<Element> elements = new ArrayList<>(elementsArray.length);

            for (final ElementChildArgument arg : elementsArray) {
                final Element element = arg.getElement();
                if (element != null) {
                    elements.add(element);
                } else {
                    final List<? extends Element> subElements = arg.getElements();
                    if (subElements != null) {
                        elements.addAll(subElements);
                    }
                }
            }

            return elements;
        } else {
            return new ArrayList<>(elementsCollection);
        }
    }
}
