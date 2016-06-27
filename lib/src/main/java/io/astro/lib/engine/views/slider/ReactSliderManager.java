/**
 * Copyright (c) 2015-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package io.astro.lib.engine.views.slider;

import java.util.Map;

import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import io.astro.lib.style.csslayout.CSSMeasureMode;
import io.astro.lib.style.csslayout.CSSNode;
import io.astro.lib.style.csslayout.MeasureOutput;
import io.astro.lib.engine.bridge.ReactContext;
import io.astro.lib.engine.common.MapBuilder;
import io.astro.lib.engine.common.SystemClock;
import io.astro.lib.engine.uimanager.LayoutShadowNode;
import io.astro.lib.engine.uimanager.SimpleViewManager;
import io.astro.lib.engine.uimanager.ThemedReactContext;
import io.astro.lib.engine.uimanager.UIManagerModule;
import io.astro.lib.engine.uimanager.ViewProps;
import io.astro.lib.engine.uimanager.annotations.ReactProp;

/**
 * Manages instances of {@code ReactSlider}.
 *
 * Note that the slider is _not_ a controlled component.
 */
public class ReactSliderManager extends SimpleViewManager<ReactSlider> {

  private static final int STYLE = android.R.attr.seekBarStyle;

  private static final String REACT_CLASS = "RCTSlider";

  static class ReactSliderShadowNode extends LayoutShadowNode implements
      CSSNode.MeasureFunction {

    private int mWidth;
    private int mHeight;
    private boolean mMeasured;

    private ReactSliderShadowNode() {
      setMeasureFunction(this);
    }

    @Override
    public void measure(
        CSSNode node,
        float width,
        CSSMeasureMode widthMode,
        float height,
        CSSMeasureMode heightMode,
        MeasureOutput measureOutput) {
      if (!mMeasured) {
        SeekBar reactSlider = new ReactSlider(getThemedContext(), null, STYLE);
        final int spec = View.MeasureSpec.makeMeasureSpec(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            View.MeasureSpec.UNSPECIFIED);
        reactSlider.measure(spec, spec);
        mWidth = reactSlider.getMeasuredWidth();
        mHeight = reactSlider.getMeasuredHeight();
        mMeasured = true;
      }
      measureOutput.width = mWidth;
      measureOutput.height = mHeight;
    }
  }

  private static final SeekBar.OnSeekBarChangeListener ON_CHANGE_LISTENER =
      new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekbar, int progress, boolean fromUser) {
          ReactContext reactContext = (ReactContext) seekbar.getContext();
          reactContext.getNativeModule(UIManagerModule.class).getEventDispatcher().dispatchEvent(
              new ReactSliderEvent(
                  seekbar.getId(),
                  SystemClock.nanoTime(),
                  ((ReactSlider)seekbar).toRealProgress(progress),
                  fromUser));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekbar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekbar) {
          ReactContext reactContext = (ReactContext) seekbar.getContext();
          reactContext.getNativeModule(UIManagerModule.class).getEventDispatcher().dispatchEvent(
              new ReactSlidingCompleteEvent(
                  seekbar.getId(),
                  SystemClock.nanoTime(),
                  ((ReactSlider)seekbar).toRealProgress(seekbar.getProgress())));
        }
      };

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  @Override
  public LayoutShadowNode createShadowNodeInstance() {
    return new ReactSliderShadowNode();
  }

  @Override
  public Class getShadowNodeClass() {
    return ReactSliderShadowNode.class;
  }

  @Override
  protected ReactSlider createViewInstance(ThemedReactContext context) {
    return new ReactSlider(context, null, STYLE);
  }

  @ReactProp(name = ViewProps.ENABLED, defaultBoolean = true)
  public void setEnabled(ReactSlider view, boolean enabled) {
    view.setEnabled(enabled);
  }

  @ReactProp(name = "value", defaultDouble = 0d)
  public void setValue(ReactSlider view, double value) {
    view.setOnSeekBarChangeListener(null);
    view.setValue(value);
    view.setOnSeekBarChangeListener(ON_CHANGE_LISTENER);
  }

  @ReactProp(name = "minimumValue", defaultDouble = 0d)
  public void setMinimumValue(ReactSlider view, double value) {
    view.setMinValue(value);
  }

  @ReactProp(name = "maximumValue", defaultDouble = 1d)
  public void setMaximumValue(ReactSlider view, double value) {
    view.setMaxValue(value);
  }

  @ReactProp(name = "step", defaultDouble = 0d)
  public void setStep(ReactSlider view, double value) {
    view.setStep(value);
  }

  @Override
  protected void addEventEmitters(final ThemedReactContext reactContext, final ReactSlider view) {
    view.setOnSeekBarChangeListener(ON_CHANGE_LISTENER);
  }

  @Override
  public Map getExportedCustomDirectEventTypeConstants() {
    return MapBuilder.of(
        ReactSlidingCompleteEvent.EVENT_NAME,
        MapBuilder.of("registrationName", "onSlidingComplete"));
  }
}