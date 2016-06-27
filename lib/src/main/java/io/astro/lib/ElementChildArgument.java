package io.astro.lib;

import java.util.List;

/**
 * @author skeswa
 */
public interface ElementChildArgument {
    ElementBuilder getElementBuilder();
    List<ElementBuilder> getElementBuilders();
}
