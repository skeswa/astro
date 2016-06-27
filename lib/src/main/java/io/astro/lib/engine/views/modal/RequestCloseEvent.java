/**
 * Copyright (c) 2015-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package io.astro.lib.engine.views.modal;

import io.astro.lib.engine.uimanager.events.Event;
import io.astro.lib.engine.uimanager.events.RCTEventEmitter;

/**
 * {@link Event} for dismissing a Dialog.
 */
/* package */ class RequestCloseEvent extends Event<RequestCloseEvent> {

  public static final String EVENT_NAME = "topRequestClose";

  protected RequestCloseEvent(int viewTag, long timestampMs) {
    super(viewTag, timestampMs);
  }

  @Override
  public String getEventName() {
    return EVENT_NAME;
  }

  @Override
  public void dispatch(RCTEventEmitter rctEventEmitter) {
    rctEventEmitter.receiveEvent(getViewTag(), getEventName(), null);
  }
}
