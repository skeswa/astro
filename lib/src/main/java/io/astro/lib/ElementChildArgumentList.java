package io.astro.lib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author skeswa
 */
public class ElementChildArgumentList implements ElementChildArgument {
    private final ElementChildArgument[] elementsArray;
    private final Collection<ElementBuilder> elementsCollection;

    ElementChildArgumentList(ElementBuilder[] elements) {
        this.elementsArray = elements;
        this.elementsCollection = null;
    }

    ElementChildArgumentList(Collection<ElementBuilder> elements) {
        this.elementsArray = null;
        this.elementsCollection = elements;
    }


    @Override
    public ElementBuilder getElementBuilder() {
        return null;
    }

    @Override
    public List<ElementBuilder> getElementBuilders() {
        if (elementsArray != null) {
            final List<ElementBuilder> elementBuilders = new ArrayList<>(elementsArray.length);

            for (final ElementChildArgument arg : elementsArray) {
                final ElementBuilder elementBuilder = arg.getElementBuilder();
                if (elementBuilder != null) {
                    elementBuilders.add(elementBuilder);
                } else {
                    final List<ElementBuilder> subElementBuilders = arg.getElementBuilders();
                    if (subElementBuilders != null) {
                        elementBuilders.addAll(subElementBuilders);
                    }
                }
            }

            return elementBuilders;
        } else {
            if (elementsCollection == null) {
                return new ArrayList<>();
            } else {
                return new ArrayList<>(elementsCollection);
            }
        }
    }
}
