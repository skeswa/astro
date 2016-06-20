package io.astro.lib;

import android.graphics.Color;

import io.astro.lib.style.constants.Flex;

import static io.astro.lib.TestComponent1.age;
import static io.astro.lib.TestComponent1.name;
import static io.astro.lib.TestComponent1.onClick;

/**
 * @author skeswa
 */
public class TestComponent2 extends Component implements ClickListener {
    private static final Field<Boolean> visible = Field.create(Boolean.class, false);

    private static final Style containerStyle = Style
        .alignItems(Flex.Alignment.CENTER)
        .flexDirection(Flex.Direction.ROW)
        .justifyContent(Flex.Justification.CENTER)
        .padding(15)
        .create();

    private static final Style itemStyle = Style
        .backgroundColor(Color.BLUE)
        .marginLeft(12)
        .create();

    private static final Style firstItemStyle = Style.from(itemStyle)
        .marginLeft(null)
        .create();

    @Override
    public void onClick() {
        Update
            .set(visible, true)
            .execute(this);
    }

    @Override
    public Element render() {
        return
            $(TestComponent1.class)
                .styles(containerStyle, valueOf(visible) ? CommonStyles.invisible : null)
                .attr(name, "value")
                .attr(age, 56)
                .attr(onClick, this)
                .children(
                    $(TestComponent1.class)
                        .style(firstItemStyle)
                        .attr(name, "random")
                        .attr(age, (int) (Math.random() * 19)),
                    $(TestComponent1.class)
                        .style(firstItemStyle)
                        .attr(name, "random")
                        .attr(age, (int) (Math.random() * 19)),
                    $(TestComponent1.class)
                        .style(firstItemStyle)
                        .attr(name, "random")
                        .attr(age, (int) (Math.random() * 19))
                )
                .create();
    }
}
