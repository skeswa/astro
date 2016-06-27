package io.astro.lib;

/**
 * @author skeswa
 */
public class ElementTransformSetStyleAttribute implements ElementTransform {
    private final Node node;
    private final StyleAttribute<?> styleAttribute;
    private final Object attributeValue;

    public ElementTransformSetStyleAttribute(
        final Node node,
        final StyleAttribute<?> styleAttribute,
        final Object attributeValue
    ) {
        this.node = node;
        this.styleAttribute = styleAttribute;
        this.attributeValue = attributeValue;
    }

    @Override
    public void apply() {

    }
}
