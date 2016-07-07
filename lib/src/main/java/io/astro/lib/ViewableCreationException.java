package io.astro.lib;

/**
 * @author skeswa
 */
public class ViewableCreationException extends RuntimeException {
    public ViewableCreationException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
