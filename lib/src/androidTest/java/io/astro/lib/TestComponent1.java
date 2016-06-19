package io.astro.lib;

/**
 * @author skeswa
 */
public class TestComponent1 extends Component {
    public static final Attribute<Integer> age = Attribute.create(Integer.class);
    public static final Attribute<String> name = Attribute.create(String.class);
    public static final Attribute<ClickListener> onClick = Attribute.create(ClickListener.class);

    @Override
    public Element render() {
        return null;
    }
}
