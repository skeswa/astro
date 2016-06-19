package io.astro.lib;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skeswa
 */
public class Style {
    private StyleAttributeArgument[] attributes;

    Style(final List<StyleAttributeArgument<?>> attributes) {
        if (attributes != null && attributes.size() > 0) {
            this.attributes = attributes.toArray(new StyleAttributeArgument[attributes.size()]);
        }
    }

    StyleAttributeArgument[] getAttributes() {
        return attributes;
    }

    // General
    public static <T> StyleBuilder attr(final StyleAttribute<T> attribute, final T value) {
        return new StyleBuilder().attr(attribute, value);
    }

    public static <T> StyleBuilder from(final Style ancestor) {
        return new StyleBuilder().from(ancestor);
    }

    // Layout
    public static StyleBuilder alignItems(final Flex.Alignment value) {
        return new StyleBuilder().alignItems(value);
        
    }

    public static StyleBuilder alignSelf(final Flex.Alignment value) {
        return new StyleBuilder().alignSelf(value);
        
    }

    public static StyleBuilder borderBottomWidth(final Number value) {
        return new StyleBuilder().borderBottomWidth(value);
        
    }

    public static StyleBuilder borderLeftWidth(final Number value) {
        return new StyleBuilder().borderLeftWidth(value);
        
    }

    public static StyleBuilder borderRightWidth(final Number value) {
        return new StyleBuilder().borderRightWidth(value);
        
    }

    public static StyleBuilder borderTopWidth(final Number value) {
        return new StyleBuilder().borderTopWidth(value);
        
    }

    public static StyleBuilder borderWidth(final Number value) {
        return new StyleBuilder().borderWidth(value);
        
    }

    public static StyleBuilder bottom(final Number value) {
        return new StyleBuilder().bottom(value);
        
    }

    public static StyleBuilder flex(final Number value) {
        return new StyleBuilder().flex(value);
        
    }

    public static StyleBuilder flexDirection(final Flex.Direction value) {
        return new StyleBuilder().flexDirection(value);
        
    }

    public static StyleBuilder flexWrap(final Flex.Wrap value) {
        return new StyleBuilder().flexWrap(value);
        
    }

    public static StyleBuilder height(final Number value) {
        return new StyleBuilder().height(value);
        
    }

    public static StyleBuilder justifyContent(final Flex.Justification value) {
        return new StyleBuilder().justifyContent(value);
        
    }

    public static StyleBuilder left(final Number value) {
        return new StyleBuilder().left(value);
        
    }

    public static StyleBuilder margin(final Number value) {
        return new StyleBuilder().margin(value);
        
    }

    public static StyleBuilder marginBottom(final Number value) {
        return new StyleBuilder().marginBottom(value);
        
    }

    public static StyleBuilder marginHorizontal(final Number value) {
        return new StyleBuilder().marginHorizontal(value);
        
    }

    public static StyleBuilder marginLeft(final Number value) {
        return new StyleBuilder().marginLeft(value);
        
    }

    public static StyleBuilder marginRight(final Number value) {
        return new StyleBuilder().marginRight(value);
        
    }

    public static StyleBuilder marginTop(final Number value) {
        return new StyleBuilder().marginTop(value);
        
    }

    public static StyleBuilder marginVertical(final Number value) {
        return new StyleBuilder().marginVertical(value);
        
    }

    public static StyleBuilder padding(final Number value) {
        return new StyleBuilder().padding(value);
        
    }

    public static StyleBuilder paddingBottom(final Number value) {
        return new StyleBuilder().paddingBottom(value);
        
    }

    public static StyleBuilder paddingHorizontal(final Number value) {
        return new StyleBuilder().paddingHorizontal(value);
        
    }

    public static StyleBuilder paddingLeft(final Number value) {
        return new StyleBuilder().paddingLeft(value);
        
    }

    public static StyleBuilder paddingRight(final Number value) {
        return new StyleBuilder().paddingRight(value);
        
    }

    public static StyleBuilder paddingTop(final Number value) {
        return new StyleBuilder().paddingTop(value);
        
    }

    public static StyleBuilder paddingVertical(final Number value) {
        return new StyleBuilder().paddingVertical(value);
        
    }

    public static StyleBuilder position(final Position value) {
        return new StyleBuilder().position(value);
        
    }

    public static StyleBuilder right(final Number value) {
        return new StyleBuilder().right(value);
        
    }

    public static StyleBuilder top(final Number value) {
        return new StyleBuilder().top(value);
        
    }

    public static StyleBuilder width(final Number value) {
        return new StyleBuilder().width(value);
        
    }

    // Box
    public static StyleBuilder backfaceVisibility(final Visibility value) {
        return new StyleBuilder().backfaceVisibility(value);
        
    }

    public static StyleBuilder backgroundColor(final Integer value) {
        return new StyleBuilder().backgroundColor(value);
        
    }

    public static StyleBuilder borderBottomColor(final Integer value) {
        return new StyleBuilder().borderBottomColor(value);
        
    }

    public static StyleBuilder borderBottomLeftRadius(final Number value) {
        return new StyleBuilder().borderBottomLeftRadius(value);
        
    }

    public static StyleBuilder borderBottomRightRadius(final Number value) {
        return new StyleBuilder().borderBottomRightRadius(value);
        
    }

    public static StyleBuilder borderColor(final Integer value) {
        return new StyleBuilder().borderColor(value);
        
    }

    public static StyleBuilder borderLeftColor(final Integer value) {
        return new StyleBuilder().borderLeftColor(value);
        
    }

    public static StyleBuilder borderRadius(final Number value) {
        return new StyleBuilder().borderRadius(value);
        
    }

    public static StyleBuilder borderRightColor(final Integer value) {
        return new StyleBuilder().borderRightColor(value);
        
    }

    public static StyleBuilder borderStyle(final BorderStyle value) {
        return new StyleBuilder().borderStyle(value);
        
    }

    public static StyleBuilder borderTopColor(final Integer value) {
        return new StyleBuilder().borderTopColor(value);
        
    }

    public static StyleBuilder borderTopLeftRadius(final Number value) {
        return new StyleBuilder().borderTopLeftRadius(value);
        
    }

    public static StyleBuilder borderTopRightRadius(final Number value) {
        return new StyleBuilder().borderTopRightRadius(value);
        
    }

    public static StyleBuilder elevation(final Number value) {
        return new StyleBuilder().elevation(value);
        
    }

    public static StyleBuilder overflow(final Visibility value) {
        return new StyleBuilder().overflow(value);
        
    }

    // Image
    public static StyleBuilder overlayColor(final Integer value) {
        return new StyleBuilder().overlayColor(value);
        
    }

    public static StyleBuilder resizeMode(final ResizeMode value) {
        return new StyleBuilder().resizeMode(value);
        
    }

    public static StyleBuilder tintColor(final Integer value) {
        return new StyleBuilder().tintColor(value);
        
    }

    // Text
    public static StyleBuilder color(final Integer value) {
        return new StyleBuilder().color(value);
        
    }

    public static StyleBuilder fontFamily(final String value) {
        return new StyleBuilder().fontFamily(value);
        
    }

    public static StyleBuilder fontSize(final Number value) {
        return new StyleBuilder().fontSize(value);
        
    }

    public static StyleBuilder fontStyle(final FontStyle value) {
        return new StyleBuilder().fontStyle(value);
        
    }

    public static StyleBuilder fontWeight(final FontWeight value) {
        return new StyleBuilder().fontWeight(value);
        
    }

    public static StyleBuilder lineHeight(final Number value) {
        return new StyleBuilder().lineHeight(value);
        
    }

    public static StyleBuilder textAlign(final HorizontalTextAlignment value) {
        return new StyleBuilder().textAlign(value);
        
    }

    public static StyleBuilder textDecorationLine(final TextDecoration value) {
        return new StyleBuilder().textDecorationLine(value);
        
    }

    public static StyleBuilder textShadowColor(final Integer value) {
        return new StyleBuilder().textShadowColor(value);
        
    }

    public static StyleBuilder textShadowOffsetHeight(final Number value) {
        return new StyleBuilder().textShadowOffsetHeight(value);
        
    }

    public static StyleBuilder textShadowOffsetWidth(final Number value) {
        return new StyleBuilder().textShadowOffsetWidth(value);
        
    }

    public static StyleBuilder textShadowRadius(final Number value) {
        return new StyleBuilder().textShadowRadius(value);
        
    }

    public static StyleBuilder textAlignVertical(final VerticalTextAlignment value) {
        return new StyleBuilder().textAlignVertical(value);
        
    }
}
