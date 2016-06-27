/**
 * Copyright (c) 2015-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package io.astro.lib.engine.views.drawer.events;

import io.astro.lib.engine.bridge.Arguments;
import io.astro.lib.engine.bridge.WritableMap;
import io.astro.lib.engine.uimanager.events.Event;
import io.astro.lib.engine.uimanager.events.RCTEventEmitter;

/**
 * Event emitted by a DrawerLayout as it is being moved open/closed.
 */
public class DrawerSlideEvent extends Event<DrawerSlideEvent> {

  public static final String EVENT_NAME = "topDrawerSlide";

  private final float mOffset;

  public DrawerSlideEvent(int viewId, long timestampMs, float offset) {
    super(viewId, timestampMs);
    mOffset = offset;
  }

  public float getOffset() {
    return mOffset;
  }

  @Override
  public String getEventName() {
    return EVENT_NAME;
  }

  @Override
  public short getCoalescingKey() {
    // All slide events for a given view can be coalesced.
    return 0;
  }

  @Override
  public void dispatch(RCTEventEmitter rctEventEmitter) {
    rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
  }

  private WritableMap serializeEventData() {
    WritableMap eventData = Arguments.createMap();
    eventData.putDouble("offset", getOffset());
    return eventData;
  }
}
