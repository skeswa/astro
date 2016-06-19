package io.astro.lib;

/**
 * @author skeswa
 */
public class ElementAttributeListArgument implements ElementArgument {
    private final ElementAttributeArgument[] assignments;

    ElementAttributeListArgument(final ElementAttributeArgument[] assignments) {
        this.assignments = assignments;
    }

    @Override
    public void bind(Element element) {
        for (ElementAttributeArgument assignment : assignments) {
            element.declareAttribute(assignment);
        }
    }
}
