package io.astro.lib;

import android.graphics.Color;

/**
 * @author skeswa
 */
public class FrenchFlag extends Component {
    @Override
    public Element render() {
        return
            $(LinearLayoutComponent.class)
                .attr(LinearLayoutComponent.horizontal, Boolean.TRUE)
                .children(
                    $(Line.class)
                        .attr(Line.color, Color.BLUE)
                        .attr(Line.horizontal, Boolean.FALSE),
                    $(Line.class)
                        .attr(Line.color, Color.WHITE)
                        .attr(Line.horizontal, Boolean.FALSE),
                    $(Line.class)
                        .attr(Line.color, Color.RED)
                        .attr(Line.horizontal, Boolean.FALSE)
                )
                .create();
    }
}
