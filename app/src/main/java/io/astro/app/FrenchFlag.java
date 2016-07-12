package io.astro.app;

import android.graphics.Color;

import io.astro.lib.Component;
import io.astro.lib.Element;

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
