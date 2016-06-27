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
import io.astro.lib.engine.uimanager.events.Event;
import io.astro.lib.engine.uimanager.events.RCTEventEmitter;

public class DrawerOpenedEvent extends Event<DrawerOpenedEvent> {

  public static final String EVENT_NAME = "topDrawerOpened";

  public DrawerOpenedEvent(int viewId, long timestampMs) {
    super(viewId, timestampMs);
  }

  @Override
  public String getEventName() {
    return EVENT_NAME;
  }

  @Override
  public short getCoalescingKey() {
    // All events for a given view can be coalesced.
    return 0;
  }

  @Override
  public void dispatch(RCTEventEmitter rctEventEmitter) {
    rctEventEmitter.receiveEvent(getViewTag(), getEventName(), Arguments.createMap());
  }

}
