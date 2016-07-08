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
    static final StyleAttribute<Flex.Alignment> alignItems = StyleAttribute.create(Flex.Alignment.class);
    static final StyleAttribute<Flex.Alignment> alignSelf = StyleAttribute.create(Flex.Alignment.class);
    static final StyleAttribute<Number> borderBottomWidth = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> borderLeftWidth = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> borderRightWidth = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> borderTopWidth = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> borderWidth = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> bottom = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> flex = StyleAttribute.create(Number.class);
    static final StyleAttribute<Flex.Direction> flexDirection = StyleAttribute.create(Flex.Direction.class);
    static final StyleAttribute<Flex.Wrap> flexWrap = StyleAttribute.create(Flex.Wrap.class);
    static final StyleAttribute<Number> height = StyleAttribute.create(Number.class);
    static final StyleAttribute<Flex.Justification> justifyContent = StyleAttribute.create(Flex.Justification.class);
    static final StyleAttribute<Number> left = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> margin = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> marginBottom = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> marginHorizontal = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> marginLeft = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> marginRight = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> marginTop = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> marginVertical = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> padding = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> paddingBottom = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> paddingHorizontal = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> paddingLeft = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> paddingRight = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> paddingTop = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> paddingVertical = StyleAttribute.create(Number.class);
    static final StyleAttribute<Position> position = StyleAttribute.create(Position.class);
    static final StyleAttribute<Number> right = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> top = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> width = StyleAttribute.create(Number.class);
    // Box
    static final StyleAttribute<Visibility> backfaceVisibility = StyleAttribute.create(Visibility.class);
    static final StyleAttribute<Integer> backgroundColor = StyleAttribute.create(Integer.class);
    static final StyleAttribute<Integer> borderBottomColor = StyleAttribute.create(Integer.class);
    static final StyleAttribute<Number> borderBottomLeftRadius = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> borderBottomRightRadius = StyleAttribute.create(Number.class);
    static final StyleAttribute<Integer> borderColor = StyleAttribute.create(Integer.class);
    static final StyleAttribute<Integer> borderLeftColor = StyleAttribute.create(Integer.class);
    static final StyleAttribute<Number> borderRadius = StyleAttribute.create(Number.class);
    static final StyleAttribute<Integer> borderRightColor = StyleAttribute.create(Integer.class);
    static final StyleAttribute<BorderStyle> borderStyle = StyleAttribute.create(BorderStyle.class);
    static final StyleAttribute<Integer> borderTopColor = StyleAttribute.create(Integer.class);
    static final StyleAttribute<Number> borderTopLeftRadius = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> borderTopRightRadius = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> elevation = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> opacity = StyleAttribute.create(Number.class);
    static final StyleAttribute<Visibility> overflow = StyleAttribute.create(Visibility.class);
    // Image
    static final StyleAttribute<Integer> overlayColor = StyleAttribute.create(Integer.class);
    static final StyleAttribute<ResizeMode> resizeMode = StyleAttribute.create(ResizeMode.class);
    static final StyleAttribute<Integer> tintColor = StyleAttribute.create(Integer.class);
    // Text
    static final StyleAttribute<Integer> color = StyleAttribute.create(Integer.class);
    static final StyleAttribute<String> fontFamily = StyleAttribute.create(String.class);
    static final StyleAttribute<Number> fontSize = StyleAttribute.create(Number.class);
    static final StyleAttribute<FontStyle> fontStyle = StyleAttribute.create(FontStyle.class);
    static final StyleAttribute<FontWeight> fontWeight = StyleAttribute.create(FontWeight.class);
    static final StyleAttribute<Number> lineHeight = StyleAttribute.create(Number.class);
    static final StyleAttribute<TextAlignment.Horizontal> textAlign = StyleAttribute.create(TextAlignment.Horizontal.class);
    static final StyleAttribute<TextDecoration> textDecorationLine = StyleAttribute.create(TextDecoration.class);
    static final StyleAttribute<Integer> textShadowColor = StyleAttribute.create(Integer.class);
    static final StyleAttribute<Number> textShadowOffsetHeight = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> textShadowOffsetWidth = StyleAttribute.create(Number.class);
    static final StyleAttribute<Number> textShadowRadius = StyleAttribute.create(Number.class);
    static final StyleAttribute<TextAlignment.Vertical> textAlignVertical = StyleAttribute.create(TextAlignment.Vertical.class);
    //
}
