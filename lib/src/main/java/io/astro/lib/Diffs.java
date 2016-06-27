package io.astro.lib;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author skeswa
 */
public class Diffs {
    static List<ElementTransform> calculate(
        final Node rootNode,
        final Element nextRootEl
    ) {
        final List<ElementTransform> diffs = new ArrayList<>();
        // The zero index is due to the fact that trees have exactly one child (the root).
        calculateDiffs(null, 0, rootNode, nextRootEl, diffs);
        return diffs;
    }

    private static void calculateDiffs(
        final Node parentNode,
        final int index,
        final Node node,
        final Element nextEl,
        List<ElementTransform> diffs
    ) {
        if (node.getElement().identifier() == nextEl.identifier()) {
            // The elements are still fundamentally the same.
            calculateRefDiffs(diffs, node, nextEl);
            calculateAttributeDiffs(diffs, node, nextEl);
            calculateStyleAttributeDiffs(diffs, node, nextEl);
            calculateChildrenDiffs(
                diffs,
                node,
                node.getChildren(),
                nextEl.getChildren()
            );
        } else {
            // The elements are fundamentally different.
            diffs.add(new ElementTransformRemove(
                parentNode,
                index,
                node
            ));
            diffs.add(new ElementTransformInsert(
                parentNode,
                index,
                nextEl
            ));
        }
    }

    private static void calculateRefDiffs(
        final List<ElementTransform> diffs,
        final Node node,
        final Element nextEl
    ) {
        final Element el = node.getElement();
        if (!Util.equals(el.getRef(), nextEl.getRef())) {
            diffs.add(new ElementTransformSetRef(node, nextEl.getRef()));
        }
    }

    private static void calculateAttributeDiffs(
        final List<ElementTransform> diffs,
        final Node node,
        final Element nextEl
    ) {
        final Element el = node.getElement();
        final Map<Attribute, Object> attributes = el.getAttributes().getAttributeValueMap();
        final Map<Attribute, Object> nextAttributes = nextEl.getAttributes().getAttributeValueMap();

        // Track new or tweaked attributes.
        for (final Attribute<?> key : nextAttributes.keySet()) {
            final Object nextAttributeValue = nextAttributes.get(key);

            if (!attributes.containsKey(key)) {
                diffs.add(new ElementTransformSetAttribute(node, key, nextAttributeValue));
                continue;
            }

            final Object attributeValue = attributes.get(key);

            if (!Util.equals(nextAttributeValue, attributeValue)) {
                diffs.add(new ElementTransformSetAttribute(node, key, nextAttributeValue));
            }
        }

        // Track removed attributes.
        for (final Attribute<?> key : attributes.keySet()) {
            if (!nextAttributes.containsKey(key)) {
                diffs.add(new ElementTransformSetAttribute(node, key, null));
            }
        }
    }

    private static void calculateStyleAttributeDiffs(
        final List<ElementTransform> diffs,
        final Node node,
        final Element nextEl
    ) {
        final Element el = node.getElement();
        final Map<StyleAttribute, Object> attributes =
            el.getStyleAttributes().getStyleAttributeValueMap();
        final Map<StyleAttribute, Object> nextStyleAttributes =
            nextEl.getStyleAttributes().getStyleAttributeValueMap();

        // Track new or tweaked attributes.
        for (final StyleAttribute<?> key : nextStyleAttributes.keySet()) {
            final Object nextStyleAttributeValue = nextStyleAttributes.get(key);

            if (!attributes.containsKey(key)) {
                diffs.add(new ElementTransformSetStyleAttribute(
                    node,
                    key,
                    nextStyleAttributeValue
                ));
                continue;
            }

            final Object attributeValue = attributes.get(key);

            if (!Util.equals(nextStyleAttributeValue, attributeValue)) {
                diffs.add(new ElementTransformSetStyleAttribute(
                    node,
                    key,
                    nextStyleAttributeValue
                ));
            }
        }

        // Track removed attributes.
        for (final StyleAttribute<?> key : attributes.keySet()) {
            if (!nextStyleAttributes.containsKey(key)) {
                diffs.add(new ElementTransformSetStyleAttribute(node, key, null));
            }
        }
    }

    private static void calculateChildrenDiffs(
        final List<ElementTransform> diffs,
        final Node parentNode,
        final Node[] children,
        final Element[] nextChildren
    ) {
        final HashMap<Node, Integer> deletedChildren = new HashMap<>();
        final SparseArray<NodeIndexTuple> childTuples = new SparseArray<>();

        if (children != null) {
            for (int i = 0; i < children.length; i++) {
                final Node child = children[i];
                final int childIdentifier = child.getElement().identifier();

                deletedChildren.put(child, i);
                childTuples.put(childIdentifier, new NodeIndexTuple(i, child));
            }
        }

        if (nextChildren != null) {
            for (int i = 0; i < nextChildren.length; i++) {
                final Element nextChild = nextChildren[i];
                final NodeIndexTuple childTuple = childTuples.get(nextChild.identifier());

                if (childTuple == null) {
                    diffs.add(new ElementTransformInsert(parentNode, i, nextChild));
                } else {
                    // If the index changed, then there was a move.
                    if (childTuple.index != i) {
                        diffs.add(new ElementTransformMove(
                            parentNode,
                            childTuple.index,
                            i
                        ));
                    }

                    // Calculate diffs on these two comparable elements.
                    calculateDiffs(parentNode, i, childTuple.node, nextChild, diffs);

                    // This child clearly wasn't deleted.
                    deletedChildren.remove(childTuple.node);
                }
            }

            for (final Node deletedChild : deletedChildren.keySet()) {
                diffs.add(new ElementTransformRemove(
                    parentNode,
                    deletedChildren.get(deletedChild),
                    deletedChild
                ));
            }
        }
    }

    private static class NodeIndexTuple {
        private final int index;
        private final Node node;

        public NodeIndexTuple(int index, Node node) {
            this.index = index;
            this.node = node;
        }
    }
}
