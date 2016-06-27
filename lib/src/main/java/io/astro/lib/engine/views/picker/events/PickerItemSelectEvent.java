/**
 * Copyright (c) 2015-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package io.astro.lib.engine.views.picker.events;

import io.astro.lib.engine.bridge.Arguments;
import io.astro.lib.engine.bridge.WritableMap;
import io.astro.lib.engine.uimanager.events.Event;
import io.astro.lib.engine.uimanager.events.RCTEventEmitter;

public class PickerItemSelectEvent extends Event<PickerItemSelectEvent> {
  public static final String EVENT_NAME = "topSelect";

  private final int mPosition;

  public PickerItemSelectEvent(int id, long uptimeMillis, int position) {
    super(id, uptimeMillis);
    mPosition = position;
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
    return eventData;
  }
}
