package io.astro.lib;

/**
 * @author skeswa
 */
public interface StatefulRenderable extends Renderable {
    void setFields(final FieldValueSet nextFieldState);
    boolean shouldUpdate(final FieldValueSet nextFieldState);
}
