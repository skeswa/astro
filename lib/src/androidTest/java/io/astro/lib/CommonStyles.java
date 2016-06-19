package io.astro.lib;

/**
 * @author skeswa
 */
public class CommonStyles extends Stylesheet {
    static final Style visible = Style.create(
        attr(opacity, 1)
    );

    static final Style invisible = Style.create(
        attr(opacity, 0)
    );
}
