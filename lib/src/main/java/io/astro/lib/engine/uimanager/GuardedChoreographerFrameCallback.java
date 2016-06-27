/**
 * Copyright (c) 2015-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package io.astro.lib.engine.uimanager;

import android.view.Choreographer;

import io.astro.lib.engine.bridge.ReactContext;

/**
 * Abstract base for a Choreographer FrameCallback that should have any RuntimeExceptions it throws
 * handled by the {@link io.astro.lib.engine.bridge.NativeModuleCallExceptionHandler} registered if
 * the app is in dev mode.
 */
public abstract class GuardedChoreographerFrameCallback implements Choreographer.FrameCallback {

  private final ReactContext mReactContext;

  protected GuardedChoreographerFrameCallback(ReactContext reactContext) {
    mReactContext = reactContext;
  }

  @Override
  public final void doFrame(long frameTimeNanos) {
    try {
      doFrameGuarded(frameTimeNanos);
    } catch (RuntimeException e) {
      mReactContext.handleException(e);
    }
  }

  /**
   * Like the standard doFrame but RuntimeExceptions will be caught and passed to
   * {@link io.astro.lib.engine.bridge.ReactContext#handleException(RuntimeException)}.
   */
  protected abstract void doFrameGuarded(long frameTimeNanos);
}
