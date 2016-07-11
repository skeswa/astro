package io.astro.lib;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.util.Collection;
import java.util.Map;

/**
 * @author skeswa
 */
public abstract class AstroActivity extends Activity implements Renderable {
    private View rootView;
    private ElementComposite rootComposite;

    private FieldValueSet fieldState = Component.EMPTY_FIELD_STATE;
    private AttributeValueSet attributeState = Component.EMPTY_ATTRIBUTE_STATE;
    private StyleAttributeValueSet styleAttributeState = Component.EMPTY_STYLE_ATTRIBUTE_STATE;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Time to perform the first render.
        final Element el = render();
        if (el == null) {
            // TODO(skeswa): better exception.
            throw new RuntimeException("Null is not a valid element.");
        }

        // Create the root composite from the output element.
        rootComposite = new ElementComposite(this, 0, null, el);
        rootView = rootComposite.getView();

        // Set the content view of this Activity.
        setContentView(rootView);

        // Now that the view stuff is taken care of, perform onMount.
        onMount();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Time to unmount this component.
        onUnmount();

        // Destroy the root composite.
        if (rootComposite != null) {
            rootComposite.destroy();
            rootComposite = null;
            rootView = null;
        }
    }

    @Override
    public void onMount() {}

    @Override
    public void onUnmount() {}

    @Override
    public void setCompositeReductionDepth(final int compositeReductionDepth) {
        // TODO(skeswa): better exception.
        throw new RuntimeException("Activity#setCompositeReductionDepth should never be invoked.");
    }

    @Override
    public void setComposite(final ElementComposite composite) {
        // TODO(skeswa): better exception.
        throw new RuntimeException("Activity#setComposite should never be invoked.");
    }

    @Override
    public void setChildren(final Element[] children) {
        // TODO(skeswa): better exception.
        throw new RuntimeException("Activity#setChildren should never be invoked.");
    }

    @Override
    public boolean shouldUpdate(final AttributeValueSet nextAttributeState) {
        return true;
    }

    @Override
    public boolean shouldUpdate(final StyleAttributeValueSet nextStyleAttributeValueSet) {
        return true;
    }

    boolean shouldUpdate(final FieldValueSet nextFieldState) {
        return !ObjectUtil.equals(fieldState, nextFieldState);
    }

    // TODO(skeswa): make updates happen in a parallel queue thread.
    @Override
    public void enqueueUpdate(final Update update) {
        if (rootComposite == null) {
            throw new IllegalArgumentException("Only mounted components may be updated.");
        }

        // Localize update state.
        final Object listenerContext = update == null ? null : update.getListenerContext();
        final UpdateListener listener = update == null ? null : update.getListener();
        final Map<Field, Object> fieldValueMap = update == null ? null : update.getFieldValueMap();

        // Create flag that determines whether this component should be re-rendered.
        boolean willUpdate = false;

        // If this renderable is a Component, then perform field state transfer logic. Only update
        // in the event that Component#shouldUpdate evaluates true, or this is a stateless update.
        if (fieldValueMap != null) {
            final FieldValueSet oldFieldState = fieldState;
            final FieldValueSet nextFieldState = oldFieldState == null ? new FieldValueSet
                (fieldValueMap) : oldFieldState.extend(fieldValueMap);
            willUpdate = shouldUpdate(nextFieldState);
            setFields(nextFieldState);
        }

        // Make the composite re-render this renderable.
        if (willUpdate) {
            // Time to get the next output element.
            final Element el = render();
            if (el == null) {
                // TODO(skeswa): better exception.
                throw new RuntimeException("Null is not a valid element.");
            }

            // Check whether we need to cycle the composite.
            final boolean shouldRefreshComposite = !rootComposite.inputElementIsCompatible(el);
            if (shouldRefreshComposite) {
                rootComposite.destroy();
                rootComposite = new ElementComposite(this, 0, null, el);
                rootView = rootComposite.getView();
            } else {
                rootComposite.reduce(el, 0);
            }
        }

        // Invoke the listener when appropriate.
        if (listener != null) {
            listener.onUpdate(listenerContext);
        }
    }

    @SuppressWarnings("unused")
    protected void onAttributesWillChange(final AttributeValueSet nextAttributeState) {}

    @Override
    public void setAttributes(AttributeValueSet nextAttributeState) {
        onAttributesWillChange(nextAttributeState);
        attributeState = nextAttributeState;
    }

    @SuppressWarnings("unused")
    protected void onFieldsWillChange(final FieldValueSet nextFieldState) {}

    void setFields(final FieldValueSet nextFieldState) {
        onFieldsWillChange(nextFieldState);
        fieldState = nextFieldState;
    }

    @SuppressWarnings("unused")
    protected <T> T valueOf(final Field<T> field) {
        return fieldState.valueOf(field);
    }

    @SuppressWarnings("unused")
    protected <T> T valueOf(final Attribute<T> attribute) {
        return attributeState.valueOf(attribute);
    }

    @SuppressWarnings("unused")
    protected <T> T valueOf(final StyleAttribute<T> styleAttribute) {
        return styleAttributeState.valueOf(styleAttribute);
    }

    @SuppressWarnings("unused")
    protected final ElementBuilder $(final ElementBuilder elementBuilder) {
        return elementBuilder;
    }

    @SuppressWarnings("unused")
    protected final ElementChildArgumentList $(final ElementBuilder...elementBuilders) {
        if (elementBuilders == null || elementBuilders.length == 0) {
            return null;
        }

        return new ElementChildArgumentList(elementBuilders);
    }

    @SuppressWarnings("unused")
    protected final ElementChildArgumentList $(final Collection<ElementBuilder> elementBuilders) {
        if (elementBuilders == null || elementBuilders.size() == 0) {
            return null;
        }

        return new ElementChildArgumentList(elementBuilders);
    }

    @SuppressWarnings("unused")
    protected final ElementBuilder $(final Class<? extends Attributable> componentType) {
        // The component type is required.
        if (componentType == null) {
            // TODO(skeswa): standardize parameter null check exception messages.
            throw new IllegalArgumentException("Null is not a valid renderable type.");
        }

        return new ElementBuilder(componentType);
    }

    public abstract Element render();
}
