package io.astro.lib.engine.bridge;

import android.os.Handler;
import android.os.Looper;

import javax.annotation.Nullable;

/**
 * @author skeswa
 */
public class UiThreadUtil {
    @Nullable
    private static Handler sMainHandler;

    /**
     * @return {@code true} if the current thread is the UI thread.
     */
    public static boolean isOnUiThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    /**
     * Throws an {@link AssertionError} if the current thread is not the UI thread.
     */
    public static void assertOnUiThread() {
        if (!isOnUiThread()) throw new AssertionError("Expected to run on UI thread!");
    }

    /**
     * Throws an {@link AssertionError} if the current thread is the UI thread.
     */
    public static void assertNotOnUiThread() {
        if (isOnUiThread()) throw new AssertionError("Expected not to run on UI thread!");
    }

    /**
     * Runs the given {@code Runnable} on the UI thread.
     */
    public static void runOnUiThread(Runnable runnable) {
        synchronized (UiThreadUtil.class) {
            if (sMainHandler == null) {
                sMainHandler = new Handler(Looper.getMainLooper());
            }
        }
        sMainHandler.post(runnable);
    }
}
