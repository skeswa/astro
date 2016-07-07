package io.astro.lib;

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
     * The placement that encapsulates the renderables represented by this composite.
     */
    private Placement placement;
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
     * @param placement                the placement of the astro placement.
     * @param indexInParent       the index of the view that this composite represents in the
     *                            {@link #parent}'s array of children.
     * @param parent              the parent composite of this composite.
     * @param initialInputElement the template used to construct this composite and its children.
     */
    ElementComposite(
        final Placement placement,
        final int indexInParent,
        final ElementComposite parent,
        final Element initialInputElement
    ) {
        this.placement = placement;
        this.parent = parent;
        this.indexInParent = indexInParent;

        // Perform the initial update.
        update(initialInputElement);
    }

    /**
     * Updates this composite according to the different between old input element and inputElement.
     *
     * @param inputElement the element that this composite should resemble.
     */
    void update(final Element inputElement) {
        int reductionDepth = 0;
        Element currElement = inputElement;

        while (currElement != null && currElement.getViewableType() != null) {
            // Assert that the reductions list at least exists.
            if (reductions == null) {
                reductions = new ArrayList<>();
            }

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
                final Renderable currRenderable = createRenderable(
                    this.placement,
                    currElement
                );

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
                reductions.get(i).renderable.onUnmount();
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
     * Destroy's the state of this composite and that of all of its children.
     */
    private void destroy() {
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
        if (reductions != null) {
            for (int i = reductions.size() - 1; i >= 0; i--) {
                reductions.get(i).renderable.onUnmount();
            }

            // Get rid of all the reductions in one fell swoop.
            reductions.clear();

            // Clear away the destroyed reductions.
            reductions = null;
        }

        // Dis-associate from parent composite for garbage collection reasons.
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
            final Viewable nextOutputViewable = createViewable(placement, nextEl);
            final Element[] nextOutputElementChildren = nextEl.getChildren();
            final ElementComposite[] nextElementCompositeChildren =
                new ElementComposite[nextOutputElementChildren.length];

            // Stuff the appropriate values into the arrays.
            for (int i = 0; i < nextOutputElementChildren.length; i++) {
                final Element childElement = nextOutputElementChildren[i];
                nextElementCompositeChildren[i] = new ElementComposite(placement, i, this, childElement);
                nextOutputViewable.insertChild(outputViewable.getView(), i);
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
            if (!ObjectUtil.equals(outputElement.getStyleAttributes(), nextEl.getStyleAttributes())) {
                outputViewable.setStyleAttributes(nextEl.getStyleAttributes());
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
                        final ElementComposite nextCompositeChild = new ElementComposite(placement, i,
                            this, nextChildElement);
                        nextCompositeChildren[i] = nextCompositeChild;
                        outputViewable.insertChild(nextCompositeChild.outputViewable.getView(), i);
                    } else {
                        // Localize state.
                        final int oldIndex = childElementTuple.index;
                        final ElementComposite childComposite = childComposites[oldIndex];

                        // Update the child composite.
                        childComposite.update(nextChildElement);

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
    private static Renderable createRenderable(final Placement placement, final Element element) {
        try {
            final Renderable renderable = element.getRenderableType().newInstance();

            renderable.setRoot(placement);
            renderable.setAttributes(element.getAttributes());
            renderable.setStyleAttributes(element.getStyleAttributes());
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
    private static Viewable createViewable(final Placement placement, final Element element) {
        try {
            final Viewable viewable = element.getViewableType().newInstance();

            viewable.onMount(placement.getContext());
            viewable.setAttributes(element.getAttributes());
            viewable.setStyleAttributes(element.getStyleAttributes());

            return viewable;
        } catch (IllegalAccessException e) {
            throw new ViewableCreationException("Could not create a new instance of Viewable \""
                + element.getViewableType().getName() + "\"", e);
        } catch (InstantiationException e) {
            throw new ViewableCreationException("Could not create a new instance of Viewable \""
                + element.getViewableType().getName() + "\"", e);
        }
    }

    private static Element renderRenderable(
        final Renderable renderable,
        final Element el,
        final Element nextEl
    ) {
        final boolean shouldUpdate = attributesCauseUpdate(renderable, el, nextEl) ||
            styleAttributesCauseUpdate(renderable, el, nextEl) ||
            childrenCauseUpdate(el, nextEl);

        // Update the state of the renderable now that the comparisons have been finished.
        renderable.setAttributes(el.getAttributes());
        renderable.setStyleAttributes(el.getStyleAttributes());
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
        return !ObjectUtil.equals(el.getStyleAttributes(), nextEl.getStyleAttributes()) && renderable
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
