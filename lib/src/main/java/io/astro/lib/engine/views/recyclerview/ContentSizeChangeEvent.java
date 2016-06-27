// Copyright 2004-present Facebook. All Rights Reserved.

package io.astro.lib.engine.views.recyclerview;

import io.astro.lib.engine.bridge.Arguments;
import io.astro.lib.engine.bridge.WritableMap;
import io.astro.lib.engine.uimanager.PixelUtil;
import io.astro.lib.engine.uimanager.events.Event;
import io.astro.lib.engine.uimanager.events.RCTEventEmitter;

/**
 * Event dispatched by {@link RecyclerViewBackedScrollView} when total height of it's children
 * changes
 */
public class ContentSizeChangeEvent extends Event<ContentSizeChangeEvent> {

  public static final String EVENT_NAME = "topContentSizeChange";

  private final int mWidth;
  private final int mHeight;

  public ContentSizeChangeEvent(int viewTag, long timestampMs, int width, int height) {
    super(viewTag, timestampMs);
    mWidth = width;
    mHeight = height;
  }

  @Override
  public String getEventName() {
    return EVENT_NAME;
  }

  @Override
  public void dispatch(RCTEventEmitter rctEventEmitter) {
    WritableMap data = Arguments.createMap();
    data.putDouble("width", PixelUtil.toDIPFromPixel(mWidth));
    data.putDouble("height", PixelUtil.toDIPFromPixel(mHeight));
    rctEventEmitter.receiveEvent(getViewTag(), EVENT_NAME, data);
  }
}
