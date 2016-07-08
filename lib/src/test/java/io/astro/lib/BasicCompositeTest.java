package io.astro.lib;

import android.view.View;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author skeswa
 */
public class BasicCompositeTest {
    @Test
    public void testBasicCompositeTree() {
        final Element el = new ElementBuilder(FrenchFlag.class).create();
        final Placement placement = new Placement(null, el);
        final View view = placement.getView();
        System.out.println("Success!");
    }
}
