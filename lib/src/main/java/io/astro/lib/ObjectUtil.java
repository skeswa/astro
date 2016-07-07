package io.astro.lib;

import java.util.Arrays;

/**
 * @author skeswa
 */
public class ObjectUtil {
    public static boolean equals(final Object a, final Object b) {
        return (a == null) ? (b == null) : a.equals(b);
    }

    public static int hash(Object... values) {
        return Arrays.hashCode(values);
    }
}
