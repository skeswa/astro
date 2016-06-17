package io.astro.lib;

/**
 * @author skeswa
 */
public class AttributeUtil {
    private static final String CAMEL_CASE_REGEX = "([a-z])([A-Z]+)";
    private static final String ATTRIBUTE_CASE_REPLACEMENT = "$1-$2";

    static String toAttributeName(String name) {
        return name.replaceAll(CAMEL_CASE_REGEX, ATTRIBUTE_CASE_REPLACEMENT).toLowerCase();
    }
}
