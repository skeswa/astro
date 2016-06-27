package io.astro.lib;

/**
 * @author skeswa
 */
public class ElementTransformSetAttribute implements ElementTransform {
    private final Node node;
    private final Attribute<?> attribute;
    private final Object attributeValue;

    public ElementTransformSetAttribute(
        final Node node,
        final Attribute<?> attribute,
        final Object attributeValue
    ) {
        this.node = node;
        this.attribute = attribute;
        this.attributeValue = attributeValue;
    }

    @Override
    public void apply() {

    }
}
