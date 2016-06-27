/**
 * Copyright (c) 2015-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package io.astro.lib.engine.views.toolbar.events;

import io.astro.lib.engine.bridge.WritableMap;
import io.astro.lib.engine.bridge.WritableNativeMap;
import io.astro.lib.engine.uimanager.events.Event;
import io.astro.lib.engine.uimanager.events.RCTEventEmitter;

/**
 * Represents a click on the toolbar.
 * Position is meaningful when the click happenned on a menu
 */
public class ToolbarClickEvent extends Event<ToolbarClickEvent> {

  private static final String EVENT_NAME = "topSelect";
  private final int position;

  public ToolbarClickEvent(int viewId, long timestampMs, int position) {
    super(viewId, timestampMs);
    this.position = position;
  }

  public int getPosition() {
    return position;
  }

  @Override
  public String getEventName() {
    return EVENT_NAME;
  }

  @Override
  public boolean canCoalesce() {
    return false;
  }

  @Override
  public void dispatch(RCTEventEmitter rctEventEmitter) {
    WritableMap event = new WritableNativeMap();
    event.putInt("position", getPosition());
    rctEventEmitter.receiveEvent(getViewTag(), getEventName(), event);
  }

}
