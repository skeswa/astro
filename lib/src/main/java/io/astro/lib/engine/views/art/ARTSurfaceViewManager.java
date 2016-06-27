/**
 * Copyright (c) 2015-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package io.astro.lib.engine.views.art;

import android.graphics.Bitmap;

import io.astro.lib.style.csslayout.CSSMeasureMode;
import io.astro.lib.style.csslayout.CSSNode;
import io.astro.lib.style.csslayout.MeasureOutput;
import io.astro.lib.engine.uimanager.BaseViewManager;
import io.astro.lib.engine.uimanager.ThemedReactContext;

/**
 * ViewManager for ARTSurfaceView React views. Renders as a {@link ARTSurfaceView} and handles
 * invalidating the native view on shadow view updates happening in the underlying tree.
 */
public class ARTSurfaceViewManager extends
    BaseViewManager<ARTSurfaceView, ARTSurfaceViewShadowNode> {

  private static final String REACT_CLASS = "ARTSurfaceView";

  private static final CSSNode.MeasureFunction MEASURE_FUNCTION = new CSSNode.MeasureFunction() {
    @Override
    public void measure(
        CSSNode node,
        float width,
        CSSMeasureMode widthMode,
        float height,
        CSSMeasureMode heightMode,
        MeasureOutput measureOutput) {
      throw new IllegalStateException("SurfaceView should have explicit width and height set");
    }
  };

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  @Override
  public ARTSurfaceViewShadowNode createShadowNodeInstance() {
    ARTSurfaceViewShadowNode node = new ARTSurfaceViewShadowNode();
    node.setMeasureFunction(MEASURE_FUNCTION);
    return node;
  }

  @Override
  public Class<ARTSurfaceViewShadowNode> getShadowNodeClass() {
    return ARTSurfaceViewShadowNode.class;
  }

  @Override
  protected ARTSurfaceView createViewInstance(ThemedReactContext reactContext) {
    return new ARTSurfaceView(reactContext);
  }

  @Override
  public void updateExtraData(ARTSurfaceView root, Object extraData) {
    root.setBitmap((Bitmap) extraData);
  }
}
