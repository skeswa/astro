package io.astro.lib;

/**
 * @author skeswa
 */
class NumberUtil {
    /**
     * Turns 1 into "1st", 2 into "2nd" etc.
     * @param i the number to ordinalize.
     * @return the ordinalized number.
     */
    static String ordinalize(int i) {
        int j = i % 10, k = i % 100;
        if (j == 1 && k != 11) {
            return i + "st";
        }
        if (j == 2 && k != 12) {
            return i + "nd";
        }
        if (j == 3 && k != 13) {
            return i + "rd";
        }

        return i + "th";
    }
}
