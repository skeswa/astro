package io.astro.lib;

/**
 * @author skeswa
 */
public class RenderableCreationException extends RuntimeException {
    public RenderableCreationException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
