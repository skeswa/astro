package io.astro.app;

import android.graphics.Color;

import java.util.Timer;
import java.util.TimerTask;

import io.astro.lib.Component;
import io.astro.lib.Element;
import io.astro.lib.Field;
import io.astro.lib.Update;

/**
 * @author skeswa
 */
public class FrenchFlag extends Component {
    private static final Field<Integer> color1 = Field.create(Integer.class, Color.BLUE);
    private static final Field<Integer> color2 = Field.create(Integer.class, Color.WHITE);
    private static final Field<Integer> color3 = Field.create(Integer.class, Color.RED);

    private Timer timer;

    @Override
    public void onMount() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Update
                    .set(color1, Color.blue((int) (Math.random() * 255)))
                    .set(color3, Color.red((int) (Math.random() * 255)))
                    .execute(FrenchFlag.this);
            }
        }, 0, 5000);
    }

    @Override
    public void onUnmount() {
        timer.cancel();
        timer = null;
    }

    @Override
    public Element render() {
        return
            $(LinearLayoutComponent.class)
                .attr(LinearLayoutComponent.horizontal, Boolean.TRUE)
                .children(
                    $(Line.class)
                        .attr(Line.color, valueOf(color1))
                        .attr(Line.horizontal, Boolean.FALSE),
                    $(Line.class)
                        .attr(Line.color, valueOf(color2))
                        .attr(Line.horizontal, Boolean.FALSE),
                    $(Line.class)
                        .attr(Line.color, valueOf(color3))
                        .attr(Line.horizontal, Boolean.FALSE)
                )
                .create();
    }
}
