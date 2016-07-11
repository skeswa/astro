package io.astro.lib.style.constants;

import io.astro.lib.style.csslayout.CSSAlign;

/**
 * @author skeswa
 */
public class Flex {
    public enum Alignment {
        AUTO(CSSAlign.AUTO),
        CENTER(CSSAlign.CENTER),
        FLEX_END(CSSAlign.FLEX_END),
        FLEX_START(CSSAlign.FLEX_START),
        STRETCH(CSSAlign.STRETCH);

        private final CSSAlign mapping;

        Alignment(final CSSAlign mapping) {
            this.mapping = mapping;
        }

        public CSSAlign getMapping() {
            return mapping;
        }
    }

    public enum Direction {
        COLUMN,
        ROW;
    }

    public enum Justification {
        CENTER,
        FLEX_END,
        FLEX_START,
        SPACE_AROUND,
        SPACE_BETWEEN
    }

    public enum Wrap {
        WRAP,
        NO_WRAP
    }
}
