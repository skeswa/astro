package io.astro.lib;

import java.util.List;

/**
 * @author skeswa
 */
public interface ElementChildArgument {
    Element getElement();
    List<? extends Element> getElements();
}
