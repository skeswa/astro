package io.astro.lib;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author skeswa
 */
class ElementComposite {
    /**
     * The Android context to which this composite belongs.
     */
    private final Context context;
    /**
     * The index of this composite in {@link #parent}'s array of childComposites.
     */
    private int indexInParent = -1;
    /**
     * The composite that owns this composite.
     */
    private ElementComposite parent;
    /**
     * The composites owned by this composite.
     */
    private ElementComposite[] childComposites;

    /**
     * The most reduced form of the original input element. This element matches the
     * {@link #outputViewable}.
     */
    private Element outputElement;
    /**
     * The renderable generated for the {@link #outputElement}.
     */
    private Viewable outputViewable;
    /**
     * The list of every successive reduction of element-renderable pairs.
     */
    private List<ElementReduction> reductions;

    /**
     * Creates a new element composite.
     *
     * @param context             the context of the Views under this composite.
     * @param indexInParent       the index of the view that this composite represents in the
     *                            {@link #parent}'s array of children.
     * @param parent              the parent composite of this composite.
     * @param initialInputElement the template used to construct this composite and its children.
     */
    ElementComposite(
        final Context context,
        final int indexInParent,
        final ElementComposite parent,
        final Element initialInputElement
    ) {
        this.context = context;
        this.parent = parent;
        this.indexInParent = indexInParent;
        this.reductions = new ArrayList<>();

        // Perform the initial reduce.
        reduce(initialInputElement, 0);
    }

    /**
     * Updates the renderable and its children starting at the reduction depth specified.
     *
     * @param reductionDepth the level in the hierarchy at which the reduction resumes.
     */
    void update(final int reductionDepth) {
        if (reductionDepth >= reductions.size()) {
            throw new IllegalArgumentException(
                "Reduction depth " + reductionDepth + " is out of bounds.");
        }

        // Start reduction at the index specified.
        reduce(reductions.get(reductionDepth).element, reductionDepth);
    }

    /**
     * Reduces the input element into an Android View by recursively rendering the sub-elements of
     * the input element. The initial depth is how deep in the Renderable hierarchy to start
     * reduction.
     *
     * @param inputElement          the element to be reduced.
     * @param initialReductionDepth the level in the hierarchy at which the reduction begins.
     */
    void reduce(final Element inputElement, final int initialReductionDepth) {
        int reductionDepth = initialReductionDepth;
        Element currElement = inputElement;

        while (currElement != null && currElement.getViewableType() == null) {
            // First check to see whether this reduction has already been performed before.
            final boolean currReductionExists = reductions.size() > reductionDepth;

            // If there is already a reduction in place, look to apply diffs.
            if (currReductionExists) {
                // Get the pre-existing reduction.
                final ElementReduction reduction = reductions.get(reductionDepth);

                // Use the current renderable to derive the next output element. After that, advance
                // the current element to the next element.
                final Element nextElement = renderRenderable(
                    reduction.renderable,
                    reduction.element,
                    currElement
                );

                // If the next element is null, it means that another render was not necessary.
                // Since no follow up needs to occur, exit here.
                if (nextElement == null) {
                    return;
                }

                // Replace the old reduction with an updated version.
                reductions.set(
                    reductionDepth,
                    new ElementReduction(nextElement, reduction.renderable)
                );
            } else {
                // If there is no reduction in place, look to create it.
                final Renderable currRenderable = createRenderable(currElement, reductionDepth);

                // Place the new renderable in the current reduction.
                reductions.add(new ElementReduction(currElement, currRenderable));

                // Use the current renderable to derive the next output element. After that, advance
                // the current element to the next element.
                currElement = currRenderable.render();
            }

            // The next reduction will be one level deeper.
            reductionDepth++;
        }

        // If the current element is null, throw a tantrum.
        if (currElement == null) {
            throw new IllegalElementException(
                "All Renderables must eventually render a Viewable. Renderable \"" +
                    inputElement.getRenderableType().toString() + "\" does not."
            );
        }

        // If we got this far, it means the current element represents a viewable. Before we
        // continue, we gotta shave off extraneous reductions if they are no longer necessary.
        if (reductionDepth < reductions.size()) {
            for (int i = reductions.size() - 1; i >= reductionDepth; i--) {
                unmountRenderable(reductions.get(i).renderable);
                reductions.remove(i);
            }

            // Destroy the existing output viewable and its corresponding element.
            destroyOutputs();
        }

        // Pass along the most reduced element to the output viewable.
        updateOutputViewable(currElement);
    }

    /**
     * Gets the view generated by the output viewable of this composite.
     *
     * @return the view generated by the output viewable of this composite.
     */
    View getView() {
        if (outputViewable == null) {
            return null;
        }

        return outputViewable.getView();
    }

    /**
     * Gets the renderable that is part of the reduction at the specified depth.
     *
     * @param reductionDepth the level in the hierarchy at which the reduction resumes.
     * @return the renderable that is part of the reduction at the specified depth
     */
    Renderable getRenderableAtDepth(final int reductionDepth) {
        if (reductionDepth >= reductions.size()) {
            throw new IllegalArgumentException(
                "Reduction depth " + reductionDepth + " is out of bounds.");
        }

        return reductions.get(reductionDepth).renderable;
    }

    /**
     * Returns true if the input element may be passed to
     * {@link ElementComposite#reduce(Element, int)}. Returns false if a new composite must be
     * created.
     *
     * @param inputElement the input element used for comparison.
     * @return true if the input element may be passed to
     * {@link ElementComposite#reduce(Element, int)}.
     */
    boolean inputElementIsCompatible(final Element inputElement) {
        return inputElement == null ||
            reductions.size() < 1 ||
            inputElement.identifier() == reductions.get(0).element.identifier();
    }

    /**
     * Destroy's the state of this composite and that of all of its children.
     */
    void destroy() {
        // Destroy is depth-first, so destroy all the childComposites first.
        if (childComposites != null) {
            for (final ElementComposite child : childComposites) {
                child.destroy();
            }

            // Clear away the destroyed childComposites.
            childComposites = null;
        }

        // After that, destroy all the outputs.
        destroyOutputs();

        // Get rid of every renderable in the reductions pipeline.
        if (reductions.size() > 0) {
            for (int i = reductions.size() - 1; i >= 0; i--) {
                unmountRenderable(reductions.get(i).renderable);
            }

            // Get rid of all the reductions in one fell swoop.
            reductions.clear();
        }

        // Dis-associate from parent composite for garbage collection.
        parent = null;
        indexInParent = -1;
    }

    /**
     * Destroys the output state of this composite.
     */
    private void destroyOutputs() {
        if (outputViewable != null) {
            // Declare that this viewable is going away for good.
            outputViewable.onUnmount();

            // If the viewable has view childComposites, get rid of the childComposites.
            if (outputViewable.getView() instanceof ViewGroup) {
                ((ViewGroup) outputViewable.getView()).removeAllViews();
            }

            // Suggest de-allocation to JVM.
            outputViewable = null;
        }

        outputElement = null;
    }

    /**
     * Updates the output state of this composite according to nextEl.
     *
     * @param nextEl the element that represents that changes that must be made within
     *               {@link #outputViewable}.
     */
    private void updateOutputViewable(final Element nextEl) {
        // Figure out whether or not the existing viewable can be tweaked, or if it needs to be
        // replaced completely.
        if (outputElement == null || outputElement.identifier() != nextEl.identifier()) {
            // The output viewable and its childComposites simply need to be replaced.
            final ElementComposite parent = this.parent;
            final int indexInParent = this.indexInParent;

            // Start by destroying what we have now.
            destroy();

            // Then, create a brand new output viewable.
            final Viewable nextOutputViewable = createViewable(nextEl);
            final Element[] nextOutputElementChildren = nextEl.getChildren();
            final ElementComposite[] nextElementCompositeChildren =
                new ElementComposite[nextOutputElementChildren.length];

            // Stuff the appropriate values into the arrays.
            for (int i = 0; i < nextOutputElementChildren.length; i++) {
                final Element childElement = nextOutputElementChildren[i];
                final ElementComposite childComposite = new ElementComposite(context, i, this,
                    childElement);

                nextElementCompositeChildren[i] = childComposite;
                nextOutputViewable.insertChild(childComposite.getView(), i);
            }

            // Replace the view in the parent if necessary.
            if (indexInParent != -1
                && parent != null
                && parent.outputViewable != null
                && parent.outputViewable.getView() instanceof ViewGroup) {
                final ViewGroup parentViewGroup = ((ViewGroup) parent.outputViewable.getView());
                parentViewGroup.removeViewAt(indexInParent);
                parentViewGroup.addView(nextOutputViewable.getView(), indexInParent);
            }

            // Bind all the new state to the correct fields.
            this.parent = parent;
            this.childComposites = nextElementCompositeChildren;
            this.outputElement = nextEl;
            this.indexInParent = indexInParent;
            this.outputViewable = nextOutputViewable;
        } else {
            // The output viewable and its childComposites simply need to be updated.

            // Update the attributes of the output viewable.
            if (!ObjectUtil.equals(outputElement.getAttributes(), nextEl.getAttributes())) {
                outputViewable.setAttributes(nextEl.getAttributes());
            }
            if (nextEl.isStyleable()) {
                ((Styleable) outputViewable).setStyleAttributes(nextEl.getStyleAttributes());
            }

            // Compare old children to new children at an item-by-item level if the children have
            // changed in any way.
            if (!Arrays.equals(outputElement.getChildren(), nextEl.getChildren())) {
                final Element[] childElements = outputElement.getChildren();
                final Element[] nextChildElements = nextEl.getChildren();

                final HashMap<Element, Integer> deletedChildElements = new HashMap<>();
                final SparseArray<ElementIndexTuple> childElementTuples = new SparseArray<>();

                //
                for (int i = 0; i < childElements.length; i++) {
                    final Element childElement = childElements[i];
                    final int childIdentifier = childElement.identifier();

                    childElementTuples.put(childIdentifier, new ElementIndexTuple(i, childElement));
                    deletedChildElements.put(childElement, i);
                }

                // Create a correctly resized array for the next version of the child composites
                // array.
                final ElementComposite[] nextCompositeChildren = new
                    ElementComposite[nextChildElements.length];

                // Identify inserted and moved elements to affect the correct change to the array of
                // composites.
                for (int i = 0; i < nextChildElements.length; i++) {
                    final Element nextChildElement = nextChildElements[i];
                    final ElementIndexTuple childElementTuple = childElementTuples.get(
                        nextChildElement.identifier()
                    );

                    if (childElementTuple == null) {
                        // This element was inserted.
                        final ElementComposite nextCompositeChild = new ElementComposite(context, i,
                            this, nextChildElement);
                        nextCompositeChildren[i] = nextCompositeChild;
                        outputViewable.insertChild(nextCompositeChild.outputViewable.getView(), i);
                    } else {
                        // Localize state.
                        final int oldIndex = childElementTuple.index;
                        final ElementComposite childComposite = childComposites[oldIndex];

                        // Update the child composite.
                        childComposite.reduce(nextChildElement, 0);

                        // Place the composite in the array.
                        nextCompositeChildren[i] = childComposite;

                        // If the index changed, then there was a move.
                        if (oldIndex != i) {
                            outputViewable.moveChild(oldIndex, i);
                            childComposite.indexInParent = i;
                        }

                        // This child clearly wasn't deleted.
                        deletedChildElements.remove(childElementTuple.element);
                    }
                }

                // Identify deleted composites.
                for (final Integer deletedChildElementIndex : deletedChildElements.values()) {
                    final ElementComposite removedElementComposite =
                        childComposites[deletedChildElementIndex];
                    removedElementComposite.destroy();
                }

                // Suggest de-allocation to all childComposites references that were not preserved.
                for (int i = 0; i < childComposites.length; i++) {
                    childComposites[i] = null;
                }

                // At this point nextCompositeChildren is correct, so it can replace this
                // .childComposites.
                childComposites = nextCompositeChildren;
            }
        }
    }

    /**
     * Creates a new renderable according to the type specified by element.
     *
     * @param placement the placement of the astro placement.
     * @param element
     * @return
     */
    @SuppressWarnings("all")
    private Renderable createRenderable(
        final Element element,
        final int reductionDepth
    ) {
        try {
            final Renderable renderable = element.getRenderableType().newInstance();

            renderable.setComposite(this);
            renderable.setCompositeReductionDepth(reductionDepth);
            renderable.setAttributes(element.getAttributes());
            renderable.setChildren(element.getChildren());

            return renderable;
        } catch (IllegalAccessException e) {
            throw new RenderableCreationException("Could not create a new instance of Renderable " +
                "\"" + element.getRenderableType().getName() + "\"", e);
        } catch (InstantiationException e) {
            throw new RenderableCreationException("Could not create a new instance of Renderable " +
                "\"" + element.getRenderableType().getName() + "\"", e);
        }
    }

    @SuppressWarnings("all")
    private Viewable createViewable(final Element element) {
        try {
            final Viewable viewable = element.getViewableType().newInstance();

            viewable.onMount(context);
            viewable.setAttributes(element.getAttributes());

            if (element.isStyleable()) {
                ((Styleable) viewable).setStyleAttributes(element.getStyleAttributes());
            }

            return viewable;
        } catch (IllegalAccessException e) {
            throw new ViewableCreationException("Could not create a new instance of Viewable \""
                + element.getViewableType().getName() + "\"", e);
        } catch (InstantiationException e) {
            throw new ViewableCreationException("Could not create a new instance of Viewable \""
                + element.getViewableType().getName() + "\"", e);
        }
    }

    private static void unmountRenderable(final Renderable renderable) {
        // Unmount the renderable.
        renderable.onUnmount();

        // Get rid of the references for garbage collection.
        renderable.setComposite(null);
        renderable.setChildren(null);
    }

    private static Element renderRenderable(
        final Renderable renderable,
        final Element el,
        final Element nextEl
    ) {
        // Check whether the next element is compatible with the current element.
        if (el.identifier() != nextEl.identifier()) {
            throw new IllegalArgumentException("The provided element is not a valid reduction " +
                "target.");
        }

        final boolean shouldUpdate = attributesCauseUpdate(renderable, el, nextEl) ||
            styleAttributesCauseUpdate(renderable, el, nextEl) ||
            childrenCauseUpdate(el, nextEl);

        // Update the state of the renderable now that the comparisons have been finished.
        renderable.setAttributes(el.getAttributes());
        renderable.setChildren(el.getChildren());

        if (shouldUpdate) {
            // Perform a render now that the renderable's state is all caught up.
            return renderable.render();
        }

        // The null means that there was no change.
        return null;
    }

    private static boolean attributesCauseUpdate(
        final Renderable renderable,
        final Element el,
        final Element nextEl
    ) {
        return !ObjectUtil.equals(el.getAttributes(), nextEl.getAttributes()) && renderable
            .shouldUpdate(nextEl
                .getAttributes());
    }

    private static boolean styleAttributesCauseUpdate(
        final Renderable renderable,
        final Element el,
        final Element nextEl
    ) {
        return !ObjectUtil.equals(el.getStyleAttributes(), nextEl.getStyleAttributes()) &&
            renderable
                .shouldUpdate
                    (nextEl.getStyleAttributes());
    }

    private static boolean childrenCauseUpdate(final Element el, final Element nextEl) {
        return !Arrays.equals(el.getChildren(), nextEl.getChildren());
    }

    private static class ElementIndexTuple {
        private final int index;
        private final Element element;

        public ElementIndexTuple(final int index, final Element element) {
            this.index = index;
            this.element = element;
        }
    }

    private static class ElementReduction {
        private final Element element;
        private final Renderable renderable;

        private ElementReduction(final Element element, final Renderable renderable) {
            this.element = element;
            this.renderable = renderable;
        }
    }
}
