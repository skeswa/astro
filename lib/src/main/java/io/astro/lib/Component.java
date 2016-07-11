package io.astro.lib;

import java.util.Collection;
import java.util.HashMap;

/**
 * @author skeswa
 */
public abstract class Component implements Renderable {
    private static final Element[] EMPTY_CHILDREN = new Element[0];
    private static final FieldValueSet EMPTY_FIELD_STATE = new FieldValueSet(new HashMap<Field, Object>());
    private static final AttributeValueSet EMPTY_ATTRIBUTE_STATE = new AttributeValueSet(new HashMap<Attribute, Object>());
    private static final StyleAttributeValueSet EMPTY_STYLE_ATTRIBUTE_STATE = new StyleAttributeValueSet(new HashMap<StyleAttribute, Object>());

    private ElementComposite composite;
    private int compositeReductionDepth = -1;

    private Element[] children = EMPTY_CHILDREN;
    private FieldValueSet fieldState = EMPTY_FIELD_STATE;
    private AttributeValueSet attributeState = EMPTY_ATTRIBUTE_STATE;
    private StyleAttributeValueSet styleAttributeState = EMPTY_STYLE_ATTRIBUTE_STATE;

    @Override
    public void onMount() {}

    @Override
    public void onUnmount() {}

    @Override
    public int getCompositeReductionDepth() {
        return compositeReductionDepth;
    }

    @Override
    public void setCompositeReductionDepth(final int compositeReductionDepth) {
        this.compositeReductionDepth = compositeReductionDepth;
    }

    @Override
    public ElementComposite getComposite() {
        return composite;
    }

    @Override
    public void setComposite(final ElementComposite composite) {
        this.composite = composite;
    }

    protected void onChildrenWillChange(final Element[] nextChildren) {}

    @Override
    public void setChildren(final Element[] nextChildren) {
        onChildrenWillChange(nextChildren);
        children = nextChildren;
    }

    protected void onAttributesWillChange(final AttributeValueSet nextAttributeState) {}

    @Override
    public void setAttributes(AttributeValueSet nextAttributeState) {
        onAttributesWillChange(nextAttributeState);
        attributeState = nextAttributeState;
    }

    protected void onStyleAttributesWillChange(final StyleAttributeValueSet nextStyleAttributeState) {}

    @Override
    public void setStyleAttributes(final StyleAttributeValueSet nextStyleAttributeState) {
        onStyleAttributesWillChange(nextStyleAttributeState);
        styleAttributeState = nextStyleAttributeState;
    }

    protected void onFieldsWillChange(final FieldValueSet nextFieldState) {}

    FieldValueSet getFields() {
        return this.fieldState;
    }

    void setFields(final FieldValueSet nextFieldState) {
        onFieldsWillChange(nextFieldState);
        fieldState = nextFieldState;
    }

    @Override
    public boolean shouldUpdate(final AttributeValueSet nextAttributeState) {
        return true;
    }

    @Override
    public boolean shouldUpdate(final StyleAttributeValueSet nextStyleAttributeValueSet) {
        return true;
    }

    public boolean shouldUpdate(final FieldValueSet nextFieldState) {
        return fieldState == null && nextFieldState != null ||
            fieldState != null && nextFieldState == null ||
            (fieldState != null && !fieldState.equals(nextFieldState));
    }

    protected Element[] getChildren() {
        return children;
    }

    protected <T> T valueOf(final Field<T> field) {
        return fieldState.valueOf(field);
    }

    protected <T> T valueOf(final Attribute<T> attribute) {
        return attributeState.valueOf(attribute);
    }

    protected <T> T valueOf(final StyleAttribute<T> styleAttribute) {
        return styleAttributeState.valueOf(styleAttribute);
    }

    protected final ElementBuilder $(final ElementBuilder elementBuilder) {
        return elementBuilder;
    }

    protected final ElementChildArgumentList $(final ElementBuilder...elementBuilders) {
        if (elementBuilders == null || elementBuilders.length == 0) {
            return null;
        }

        return new ElementChildArgumentList(elementBuilders);
    }

    protected final ElementChildArgumentList $(final Collection<ElementBuilder> elementBuilders) {
        if (elementBuilders == null || elementBuilders.size() == 0) {
            return null;
        }

        return new ElementChildArgumentList(elementBuilders);
    }

    protected final ElementBuilder $(final Class<? extends ComponentType> componentType) {
        // The component type is required.
        if (componentType == null) {
            // TODO(skeswa): standardize parameter null check exception messages.
            throw new IllegalArgumentException("Null is not a valid renderable type.");
        }

        return new ElementBuilder(componentType);
    }

    @Override
    public abstract Element render();
}
