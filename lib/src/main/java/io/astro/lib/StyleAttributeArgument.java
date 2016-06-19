package io.astro.lib;

/**
 * @author skeswa
 */
public class StyleAttributeArgument<T> implements StyleArgument {
    private final StyleAttribute<T> attribute;
    private final T value;

    public StyleAttributeArgument(StyleAttribute<T> attribute, T value) {
        this.attribute = attribute;
        this.value = value;
    }

    public StyleAttribute<T> getAttribute() {
        return attribute;
    }

    public T getValue() {
        return value;
    }

    @Override
    public void bind(final Style style) {
        style.declareAttribute(this);
    }
}
