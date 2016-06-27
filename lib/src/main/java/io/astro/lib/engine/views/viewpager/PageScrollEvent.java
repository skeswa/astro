/**
 * Copyright (c) 2015-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package io.astro.lib.engine.views.viewpager;

import io.astro.lib.engine.bridge.Arguments;
import io.astro.lib.engine.bridge.WritableMap;
import io.astro.lib.engine.uimanager.events.Event;
import io.astro.lib.engine.uimanager.events.RCTEventEmitter;

/**
 * Event emitted by {@link ReactViewPager} when user scrolls between pages (or when animating
 * between pages).
 *
 * Additional data provided by this event:
 *  - position - index of first page from the left that is currently visible
 *  - offset - value from range [0,1) describing stage between page transitions. Value x means that
 *    (1 - x) fraction of the page at "position" index is visible, and x fraction of the next page
 *    is visible.
 */
/* package */ class PageScrollEvent extends Event<PageScrollEvent> {

  public static final String EVENT_NAME = "topPageScroll";

  private final int mPosition;
  private final float mOffset;

  protected PageScrollEvent(int viewTag, long timestampMs, int position, float offset) {
    super(viewTag, timestampMs);
    mPosition = position;
    mOffset = offset;
  }

  @Override
  public String getEventName() {
    return EVENT_NAME;
  }

  @Override
  public void dispatch(RCTEventEmitter rctEventEmitter) {
    rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
  }

  private WritableMap serializeEventData() {
    WritableMap eventData = Arguments.createMap();
    eventData.putInt("position", mPosition);
    eventData.putDouble("offset", mOffset);
    return eventData;
  }
}
