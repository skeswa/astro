package io.astro.lib;

import android.graphics.Color;

/**
 * @author skeswa
 */
public class Line extends Component {
    public static final Attribute<Integer> color = Attribute.create(Integer.class, Color.RED);
    public static final Attribute<Boolean> horizontal = Attribute.create(Boolean.class, Boolean.TRUE);

    @Override
    public Element render() {
        return
            $(LinearLayoutComponent.class)
                .attr(LinearLayoutComponent.horizontal, valueOf(horizontal))
                .children(
                    $(ColorBox.class)
                        .attr(ColorBox.width, 100)
                        .attr(ColorBox.height, 100)
                        .attr(ColorBox.color, valueOf(color)),
                    $(ColorBox.class)
                        .attr(ColorBox.width, 100)
                        .attr(ColorBox.height, 100)
                        .attr(ColorBox.color, valueOf(color)),
                    $(ColorBox.class)
                        .attr(ColorBox.width, 100)
                        .attr(ColorBox.height, 100)
                        .attr(ColorBox.color, valueOf(color))
                )
                .create();
    }
}
