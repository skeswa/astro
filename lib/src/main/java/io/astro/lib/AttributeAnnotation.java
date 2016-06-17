package io.astro.lib;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author skeswa
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AttributeAnnotation {
    String value();
}
