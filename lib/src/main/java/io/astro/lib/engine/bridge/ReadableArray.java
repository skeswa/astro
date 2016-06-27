package io.astro.lib.engine.bridge;

/**
 * Interface for an array that allows typed access to its members. Used to pass parameters from JS
 * to Java.
 */
public interface ReadableArray {

    int size();
    boolean isNull(int index);
    boolean getBoolean(int index);
    double getDouble(int index);
    int getInt(int index);
    String getString(int index);
    ReadableArray getArray(int index);
    ReadableMap getMap(int index);
    ReadableType getType(int index);
}
