/**
 * Copyright (c) 2015-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package io.astro.lib.engine.views.textinput;

import io.astro.lib.engine.bridge.Arguments;
import io.astro.lib.engine.bridge.WritableMap;
import io.astro.lib.engine.uimanager.events.Event;
import io.astro.lib.engine.uimanager.events.RCTEventEmitter;

/**
 * Event emitted by EditText native view when the text selection changes.
 */
/* package */ class ReactTextInputSelectionEvent
    extends Event<ReactTextInputSelectionEvent> {

  private static final String EVENT_NAME = "topSelectionChange";

  private int mSelectionStart;
  private int mSelectionEnd;

  public ReactTextInputSelectionEvent(
      int viewId,
      long timestampMs,
      int selectionStart,
      int selectionEnd) {
    super(viewId, timestampMs);
    mSelectionStart = selectionStart;
    mSelectionEnd = selectionEnd;
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

    WritableMap selectionData = Arguments.createMap();
    selectionData.putInt("start", mSelectionStart);
    selectionData.putInt("end", mSelectionEnd);

    eventData.putMap("selection", selectionData);
    return eventData;
  }
}
