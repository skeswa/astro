package io.astro.lib.engine.bridge;

/**
 * @author skeswa
 */
public interface ReadableMapKeySetIterator {
    boolean hasNextKey();
    String nextKey();
}