// Copyright 2004-present Facebook. All Rights Reserved.

package io.astro.lib.engine.views.recyclerview;

import javax.annotation.Nullable;

import java.util.Map;

import android.view.View;

import io.astro.lib.engine.bridge.ReadableArray;
import io.astro.lib.engine.common.MapBuilder;
import io.astro.lib.engine.uimanager.annotations.ReactProp;
import io.astro.lib.engine.uimanager.ThemedReactContext;
import io.astro.lib.engine.uimanager.ViewGroupManager;
import io.astro.lib.engine.views.scroll.ReactScrollViewCommandHelper;
import io.astro.lib.engine.views.scroll.ScrollEventType;

/**
 * View manager for {@link RecyclerViewBackedScrollView}.
 */
public class RecyclerViewBackedScrollViewManager extends
    ViewGroupManager<RecyclerViewBackedScrollView>
    implements ReactScrollViewCommandHelper.ScrollCommandHandler<RecyclerViewBackedScrollView> {

  private static final String REACT_CLASS = "AndroidRecyclerViewBackedScrollView";

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  // TODO(8624925): Implement removeClippedSubviews support for native ListView

  @ReactProp(name = "onContentSizeChange")
  public void setOnContentSizeChange(RecyclerViewBackedScrollView view, boolean value) {
    view.setSendContentSizeChangeEvents(value);
  }

  @Override
  protected RecyclerViewBackedScrollView createViewInstance(ThemedReactContext reactContext) {
    return new RecyclerViewBackedScrollView(reactContext);
  }

  @Override
  public void addView(RecyclerViewBackedScrollView parent, View child, int index) {
    parent.addViewToAdapter(child, index);
  }

  @Override
  public int getChildCount(RecyclerViewBackedScrollView parent) {
    return parent.getChildCountFromAdapter();
  }

  @Override
  public View getChildAt(RecyclerViewBackedScrollView parent, int index) {
    return parent.getChildAtFromAdapter(index);
  }

  @Override
  public void removeViewAt(RecyclerViewBackedScrollView parent, int index) {
    parent.removeViewFromAdapter(index);
  }

  /**
   * Provides implementation of commands supported by {@link ReactScrollViewManager}
   */
  @Override
  public void receiveCommand(
      RecyclerViewBackedScrollView view,
      int commandId,
      @Nullable ReadableArray args) {
    ReactScrollViewCommandHelper.receiveCommand(this, view, commandId, args);
  }

  @Override
  public void scrollTo(
      RecyclerViewBackedScrollView scrollView,
      ReactScrollViewCommandHelper.ScrollToCommandData data) {
    scrollView.scrollTo(data.mDestX, data.mDestY, data.mAnimated);
  }

  @Override
  public @Nullable
  Map getExportedCustomDirectEventTypeConstants() {
    return MapBuilder.builder()
        .put(ScrollEventType.SCROLL.getJSEventName(), MapBuilder.of("registrationName", "onScroll"))
        .put(
            ContentSizeChangeEvent.EVENT_NAME,
            MapBuilder.of("registrationName", "onContentSizeChange"))
        .build();
  }
}
