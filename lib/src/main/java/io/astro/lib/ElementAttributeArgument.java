package io.astro.lib;

/**
 * @author skeswa
 */
class ElementAttributeArgument<T> implements ElementArgument {
    private final Attribute<T> attribute;
    private final T value;

    ElementAttributeArgument(Attribute<T> attribute, T value) {
        this.attribute = attribute;
        this.value = value;
    }

    @Override
    public void bind(Element element) {
        element.declareAttribute(this);
    }
}
