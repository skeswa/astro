package io.astro.lib.engine.bridge;

/**
 * Interface for a map that allows typed access to its members. Used to pass parameters from JS to
 * Java.
 */
public interface ReadableMap {

    boolean hasKey(String name);
    boolean isNull(String name);
    boolean getBoolean(String name);
    double getDouble(String name);
    int getInt(String name);
    String getString(String name);
    ReadableArray getArray(String name);
    ReadableMap getMap(String name);
    ReadableType getType(String name);
    ReadableMapKeySetIterator keySetIterator();
}
