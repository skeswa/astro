/**
 * Copyright (c) 2015-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package io.astro.lib.engine.views.scroll;

import android.os.SystemClock;

/**
 * Android has a bug where onScrollChanged is called twice per frame with the same params during
 * flings. We hack around that here by trying to detect that duplicate call and not dispatch it. See
 * https://code.google.com/p/android/issues/detail?id=39473
 */
public class OnScrollDispatchHelper {

  private static final int MIN_EVENT_SEPARATION_MS = 10;

  private int mPrevX = Integer.MIN_VALUE;
  private int mPrevY = Integer.MIN_VALUE;
  private long mLastScrollEventTimeMs = -(MIN_EVENT_SEPARATION_MS + 1);

  /**
   * Call from a ScrollView in onScrollChanged, returns true if this onScrollChanged is legit (not a
   * duplicate) and should be dispatched.
   */
  public boolean onScrollChanged(int x, int y) {
    long eventTime = SystemClock.uptimeMillis();
    boolean shouldDispatch =
        eventTime - mLastScrollEventTimeMs > MIN_EVENT_SEPARATION_MS ||
            mPrevX != x ||
            mPrevY != y;

    mLastScrollEventTimeMs = eventTime;
    mPrevX = x;
    mPrevY = y;

    return shouldDispatch;
  }
}
