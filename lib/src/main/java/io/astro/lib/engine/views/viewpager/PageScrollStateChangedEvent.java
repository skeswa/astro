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
 * Event emitted by {@link ReactViewPager} when user scrolling state changed.
 *
 * Additional data provided by this event:
 *  - pageScrollState - {Idle,Dragging,Settling}
 */
class PageScrollStateChangedEvent extends Event<PageScrollStateChangedEvent> {

  public static final String EVENT_NAME = "topPageScrollStateChanged";

  private final String mPageScrollState;

  protected PageScrollStateChangedEvent(int viewTag, long timestampMs, String pageScrollState) {
    super(viewTag, timestampMs);
    mPageScrollState = pageScrollState;
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
    eventData.putString("pageScrollState", mPageScrollState);
    return eventData;
  }
}
