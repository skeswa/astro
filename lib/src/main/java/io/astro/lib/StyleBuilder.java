package io.astro.lib;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import io.astro.lib.style.constants.BorderStyle;
import io.astro.lib.style.constants.Flex;
import io.astro.lib.style.constants.FontStyle;
import io.astro.lib.style.constants.FontWeight;
import io.astro.lib.style.constants.Position;
import io.astro.lib.style.constants.ResizeMode;
import io.astro.lib.style.constants.TextAlignment;
import io.astro.lib.style.constants.TextDecoration;
import io.astro.lib.style.constants.Visibility;

/**
 * @author skeswa
 */
public class StyleBuilder {
    private Style ancestor;
    private final Map<StyleAttribute, Object> attributes;

    StyleBuilder() {
        this.attributes = new HashMap<>();
    }
    
    private <T> void add(final StyleAttribute<T> attribute, final T value) {
        if (attribute != null) attributes.put(attribute, value);
    }

    // General
    StyleBuilder from(final Style ancestor) {
        this.ancestor = ancestor;
        return this;
    }

    public Style create() {
        if (ancestor != null) {
            final Set<Entry<StyleAttribute, Object>> ancestorAttributes = ancestor.getAttributes().entrySet();
            for (final Entry<StyleAttribute, Object> entry : ancestorAttributes) {
                if (!attributes.containsKey(entry.getKey())) {
                    attributes.put(entry.getKey(), entry.getValue());
                }
            }
        }

        return new Style(attributes);
    }

    public <T> StyleBuilder attr(final StyleAttribute<T> attribute, final T value) {
        add(attribute, value);
        return this;
    }

    // Layout
    public StyleBuilder alignItems(final Flex.Alignment value) {
        add(StyleAttributes.alignItems, value);
        return this;
    }

    public StyleBuilder alignSelf(final Flex.Alignment value) {
        add(StyleAttributes.alignSelf, value);
        return this;
    }

    public StyleBuilder borderBottomWidth(final Number value) {
        add(StyleAttributes.borderBottomWidth, value);
        return this;
    }

    public StyleBuilder borderLeftWidth(final Number value) {
        add(StyleAttributes.borderLeftWidth, value);
        return this;
    }

    public StyleBuilder borderRightWidth(final Number value) {
        add(StyleAttributes.borderRightWidth, value);
        return this;
    }

    public StyleBuilder borderTopWidth(final Number value) {
        add(StyleAttributes.borderTopWidth, value);
        return this;
    }

    public StyleBuilder borderWidth(final Number value) {
        add(StyleAttributes.borderWidth, value);
        return this;
    }

    public StyleBuilder bottom(final Number value) {
        add(StyleAttributes.bottom, value);
        return this;
    }

    public StyleBuilder flex(final Number value) {
        add(StyleAttributes.flex, value);
        return this;
    }

    public StyleBuilder flexDirection(final Flex.Direction value) {
        add(StyleAttributes.flexDirection, value);
        return this;
    }

    public StyleBuilder flexWrap(final Flex.Wrap value) {
        add(StyleAttributes.flexWrap, value);
        return this;
    }

    public StyleBuilder height(final Number value) {
        add(StyleAttributes.height, value);
        return this;
    }

    public StyleBuilder justifyContent(final Flex.Justification value) {
        add(StyleAttributes.justifyContent, value);
        return this;
    }

    public StyleBuilder left(final Number value) {
        add(StyleAttributes.left, value);
        return this;
    }

    public StyleBuilder margin(final Number value) {
        add(StyleAttributes.margin, value);
        return this;
    }

    public StyleBuilder marginBottom(final Number value) {
        add(StyleAttributes.marginBottom, value);
        return this;
    }

    public StyleBuilder marginHorizontal(final Number value) {
        add(StyleAttributes.marginHorizontal, value);
        return this;
    }

    public StyleBuilder marginLeft(final Number value) {
        add(StyleAttributes.marginLeft, value);
        return this;
    }

    public StyleBuilder marginRight(final Number value) {
        add(StyleAttributes.marginRight, value);
        return this;
    }

    public StyleBuilder marginTop(final Number value) {
        add(StyleAttributes.marginTop, value);
        return this;
    }

    public StyleBuilder marginVertical(final Number value) {
        add(StyleAttributes.marginVertical, value);
        return this;
    }

    public StyleBuilder padding(final Number value) {
        add(StyleAttributes.padding, value);
        return this;
    }

    public StyleBuilder paddingBottom(final Number value) {
        add(StyleAttributes.paddingBottom, value);
        return this;
    }

    public StyleBuilder paddingHorizontal(final Number value) {
        add(StyleAttributes.paddingHorizontal, value);
        return this;
    }

    public StyleBuilder paddingLeft(final Number value) {
        add(StyleAttributes.paddingLeft, value);
        return this;
    }

    public StyleBuilder paddingRight(final Number value) {
        add(StyleAttributes.paddingRight, value);
        return this;
    }

    public StyleBuilder paddingTop(final Number value) {
        add(StyleAttributes.paddingTop, value);
        return this;
    }

    public StyleBuilder paddingVertical(final Number value) {
        add(StyleAttributes.paddingVertical, value);
        return this;
    }

    public StyleBuilder position(final Position value) {
        add(StyleAttributes.position, value);
        return this;
    }

    public StyleBuilder right(final Number value) {
        add(StyleAttributes.right, value);
        return this;
    }

    public StyleBuilder top(final Number value) {
        add(StyleAttributes.top, value);
        return this;
    }

    public StyleBuilder width(final Number value) {
        add(StyleAttributes.width, value);
        return this;
    }

    // Box
    public StyleBuilder backfaceVisibility(final Visibility value) {
        add(StyleAttributes.backfaceVisibility, value);
        return this;
    }

    public StyleBuilder backgroundColor(final Integer value) {
        add(StyleAttributes.backgroundColor, value);
        return this;
    }

    public StyleBuilder borderBottomColor(final Integer value) {
        add(StyleAttributes.borderBottomColor, value);
        return this;
    }

    public StyleBuilder borderBottomLeftRadius(final Number value) {
        add(StyleAttributes.borderBottomLeftRadius, value);
        return this;
    }

    public StyleBuilder borderBottomRightRadius(final Number value) {
        add(StyleAttributes.borderBottomRightRadius, value);
        return this;
    }

    public StyleBuilder borderColor(final Integer value) {
        add(StyleAttributes.borderColor, value);
        return this;
    }

    public StyleBuilder borderLeftColor(final Integer value) {
        add(StyleAttributes.borderLeftColor, value);
        return this;
    }

    public StyleBuilder borderRadius(final Number value) {
        add(StyleAttributes.borderRadius, value);
        return this;
    }

    public StyleBuilder borderRightColor(final Integer value) {
        add(StyleAttributes.borderRightColor, value);
        return this;
    }

    public StyleBuilder borderStyle(final BorderStyle value) {
        add(StyleAttributes.borderStyle, value);
        return this;
    }

    public StyleBuilder borderTopColor(final Integer value) {
        add(StyleAttributes.borderTopColor, value);
        return this;
    }

    public StyleBuilder borderTopLeftRadius(final Number value) {
        add(StyleAttributes.borderTopLeftRadius, value);
        return this;
    }

    public StyleBuilder borderTopRightRadius(final Number value) {
        add(StyleAttributes.borderTopRightRadius, value);
        return this;
    }

    public StyleBuilder elevation(final Number value) {
        add(StyleAttributes.elevation, value);
        return this;
    }

    public StyleBuilder opacity(final Number value) {
        add(StyleAttributes.opacity, value);
        return this;
    }

    public StyleBuilder overflow(final Visibility value) {
        add(StyleAttributes.overflow, value);
        return this;
    }

    // Image
    public StyleBuilder overlayColor(final Integer value) {
        add(StyleAttributes.overlayColor, value);
        return this;
    }

    public StyleBuilder resizeMode(final ResizeMode value) {
        add(StyleAttributes.resizeMode, value);
        return this;
    }

    public StyleBuilder tintColor(final Integer value) {
        add(StyleAttributes.tintColor, value);
        return this;
    }

    // Text
    public StyleBuilder color(final Integer value) {
        add(StyleAttributes.color, value);
        return this;
    }

    public StyleBuilder fontFamily(final String value) {
        add(StyleAttributes.fontFamily, value);
        return this;
    }

    public StyleBuilder fontSize(final Number value) {
        add(StyleAttributes.fontSize, value);
        return this;
    }

    public StyleBuilder fontStyle(final FontStyle value) {
        add(StyleAttributes.fontStyle, value);
        return this;
    }

    public StyleBuilder fontWeight(final FontWeight value) {
        add(StyleAttributes.fontWeight, value);
        return this;
    }

    public StyleBuilder lineHeight(final Number value) {
        add(StyleAttributes.lineHeight, value);
        return this;
    }

    public StyleBuilder textAlign(final TextAlignment.Horizontal value) {
        add(StyleAttributes.textAlign, value);
        return this;
    }

    public StyleBuilder textDecorationLine(final TextDecoration value) {
        add(StyleAttributes.textDecorationLine, value);
        return this;
    }

    public StyleBuilder textShadowColor(final Integer value) {
        add(StyleAttributes.textShadowColor, value);
        return this;
    }

    public StyleBuilder textShadowOffsetHeight(final Number value) {
        add(StyleAttributes.textShadowOffsetHeight, value);
        return this;
    }

    public StyleBuilder textShadowOffsetWidth(final Number value) {
        add(StyleAttributes.textShadowOffsetWidth, value);
        return this;
    }

    public StyleBuilder textShadowRadius(final Number value) {
        add(StyleAttributes.textShadowRadius, value);
        return this;
    }

    public StyleBuilder textAlignVertical(final TextAlignment.Vertical value) {
        add(StyleAttributes.textAlignVertical, value);
        return this;
    }
}
