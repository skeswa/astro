package io.astro.lib;

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
class StyleAttributes {
    // Layout
    static final StyleAttribute<Flex.Alignment> alignItems = new StyleAttribute<>();
    static final StyleAttribute<Flex.Alignment> alignSelf = new StyleAttribute<>();
    static final StyleAttribute<Number> borderBottomWidth = new StyleAttribute<>();
    static final StyleAttribute<Number> borderLeftWidth = new StyleAttribute<>();
    static final StyleAttribute<Number> borderRightWidth = new StyleAttribute<>();
    static final StyleAttribute<Number> borderTopWidth = new StyleAttribute<>();
    static final StyleAttribute<Number> borderWidth = new StyleAttribute<>();
    static final StyleAttribute<Number> bottom = new StyleAttribute<>();
    static final StyleAttribute<Number> flex = new StyleAttribute<>();
    static final StyleAttribute<Flex.Direction> flexDirection = new StyleAttribute<>();
    static final StyleAttribute<Flex.Wrap> flexWrap = new StyleAttribute<>();
    static final StyleAttribute<Number> height = new StyleAttribute<>();
    static final StyleAttribute<Flex.Justification> justifyContent = new StyleAttribute<>();
    static final StyleAttribute<Number> left = new StyleAttribute<>();
    static final StyleAttribute<Number> margin = new StyleAttribute<>();
    static final StyleAttribute<Number> marginBottom = new StyleAttribute<>();
    static final StyleAttribute<Number> marginHorizontal = new StyleAttribute<>();
    static final StyleAttribute<Number> marginLeft = new StyleAttribute<>();
    static final StyleAttribute<Number> marginRight = new StyleAttribute<>();
    static final StyleAttribute<Number> marginTop = new StyleAttribute<>();
    static final StyleAttribute<Number> marginVertical = new StyleAttribute<>();
    static final StyleAttribute<Number> padding = new StyleAttribute<>();
    static final StyleAttribute<Number> paddingBottom = new StyleAttribute<>();
    static final StyleAttribute<Number> paddingHorizontal = new StyleAttribute<>();
    static final StyleAttribute<Number> paddingLeft = new StyleAttribute<>();
    static final StyleAttribute<Number> paddingRight = new StyleAttribute<>();
    static final StyleAttribute<Number> paddingTop = new StyleAttribute<>();
    static final StyleAttribute<Number> paddingVertical = new StyleAttribute<>();
    static final StyleAttribute<Position> position = new StyleAttribute<>();
    static final StyleAttribute<Number> right = new StyleAttribute<>();
    static final StyleAttribute<Number> top = new StyleAttribute<>();
    static final StyleAttribute<Number> width = new StyleAttribute<>();
    // Box
    static final StyleAttribute<Visibility> backfaceVisibility = new StyleAttribute<>();
    static final StyleAttribute<Integer> backgroundColor = new StyleAttribute<>();
    static final StyleAttribute<Integer> borderBottomColor = new StyleAttribute<>();
    static final StyleAttribute<Number> borderBottomLeftRadius = new StyleAttribute<>();
    static final StyleAttribute<Number> borderBottomRightRadius = new StyleAttribute<>();
    static final StyleAttribute<Integer> borderColor = new StyleAttribute<>();
    static final StyleAttribute<Integer> borderLeftColor = new StyleAttribute<>();
    static final StyleAttribute<Number> borderRadius = new StyleAttribute<>();
    static final StyleAttribute<Integer> borderRightColor = new StyleAttribute<>();
    static final StyleAttribute<BorderStyle> borderStyle = new StyleAttribute<>();
    static final StyleAttribute<Integer> borderTopColor = new StyleAttribute<>();
    static final StyleAttribute<Number> borderTopLeftRadius = new StyleAttribute<>();
    static final StyleAttribute<Number> borderTopRightRadius = new StyleAttribute<>();
    static final StyleAttribute<Number> elevation = new StyleAttribute<>();
    static final StyleAttribute<Number> opacity = new StyleAttribute<>();
    static final StyleAttribute<Visibility> overflow = new StyleAttribute<>();
    // Image
    static final StyleAttribute<Integer> overlayColor = new StyleAttribute<>();
    static final StyleAttribute<ResizeMode> resizeMode = new StyleAttribute<>();
    static final StyleAttribute<Integer> tintColor = new StyleAttribute<>();
    // Text
    static final StyleAttribute<Integer> color = new StyleAttribute<>();
    static final StyleAttribute<String> fontFamily = new StyleAttribute<>();
    static final StyleAttribute<Number> fontSize = new StyleAttribute<>();
    static final StyleAttribute<FontStyle> fontStyle = new StyleAttribute<>();
    static final StyleAttribute<FontWeight> fontWeight = new StyleAttribute<>();
    static final StyleAttribute<Number> lineHeight = new StyleAttribute<>();
    static final StyleAttribute<TextAlignment.Horizontal> textAlign = new StyleAttribute<>();
    static final StyleAttribute<TextDecoration> textDecorationLine = new StyleAttribute<>();
    static final StyleAttribute<Integer> textShadowColor = new StyleAttribute<>();
    static final StyleAttribute<Number> textShadowOffsetHeight = new StyleAttribute<>();
    static final StyleAttribute<Number> textShadowOffsetWidth = new StyleAttribute<>();
    static final StyleAttribute<Number> textShadowRadius = new StyleAttribute<>();
    static final StyleAttribute<TextAlignment.Vertical> textAlignVertical = new StyleAttribute<>();
    //
}
