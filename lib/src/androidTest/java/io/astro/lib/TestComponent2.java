package io.astro.lib;

import java.util.Arrays;
import java.util.HashMap;

import static io.astro.lib.TestComponent1.name;
import static io.astro.lib.TestComponent1.age;

/**
 * @author skeswa
 */
public class TestComponent2 extends Component {
    @Override
    public Element render() {
        return (
            $(TestComponent1.class,
                attr(name, "value"),
                attr(age, 56),
                attrs(new HashMap<Attribute, Object>()),
                children(
                    $(Arrays.asList(
                        $(TestComponent1.class, attr(TestComponent1.name, "well well well")),
                        $(TestComponent1.class),
                        $(TestComponent1.class)
                    )),
                    $(TestComponent1.class,
                        attr(TestComponent1.name, "value"),
                        attr(TestComponent1.age, 56)),
                    $(TestComponent1.class,
                        attr(TestComponent1.name, "value"),
                        attr(TestComponent1.age, 56)),
                    $(TestComponent1.class,
                        attr(TestComponent1.name, "value"),
                        attr(TestComponent1.age, 56)),
                    $(TestComponent1.class,
                        attr(TestComponent1.name, "value"),
                        attr(TestComponent1.age, 56))
                ))
            );
    }
}
