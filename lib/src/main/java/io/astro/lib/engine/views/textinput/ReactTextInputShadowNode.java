/**
 * Copyright (c) 2015-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package io.astro.lib.engine.views.textinput;

import javax.annotation.Nullable;

import android.text.Spannable;
import android.util.TypedValue;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.EditText;

import io.astro.lib.style.csslayout.CSSConstants;
import io.astro.lib.style.csslayout.CSSMeasureMode;
import io.astro.lib.style.csslayout.CSSNode;
import io.astro.lib.style.csslayout.MeasureOutput;
import io.astro.lib.style.csslayout.Spacing;
import com.facebook.infer.annotation.Assertions;
import io.astro.lib.engine.common.annotations.VisibleForTesting;
import io.astro.lib.engine.uimanager.PixelUtil;
import io.astro.lib.engine.uimanager.ThemedReactContext;
import io.astro.lib.engine.uimanager.UIViewOperationQueue;
import io.astro.lib.engine.uimanager.ViewDefaults;
import io.astro.lib.engine.uimanager.annotations.ReactProp;
import io.astro.lib.engine.views.text.ReactTextShadowNode;
import io.astro.lib.engine.views.text.ReactTextUpdate;

@VisibleForTesting
public class ReactTextInputShadowNode extends ReactTextShadowNode implements
    CSSNode.MeasureFunction {

  private @Nullable EditText mEditText;
  private @Nullable float[] mComputedPadding;
  private int mJsEventCount = UNSET;

  public ReactTextInputShadowNode() {
    super(false);
    setMeasureFunction(this);
  }

  @Override
  public void setThemedContext(ThemedReactContext themedContext) {
    super.setThemedContext(themedContext);

    // TODO #7120264: cache this stuff better
    mEditText = new EditText(getThemedContext());
    // This is needed to fix an android bug since 4.4.3 which will throw an NPE in measure,
    // setting the layoutParams fixes it: https://code.google.com/p/android/issues/detail?id=75877
    mEditText.setLayoutParams(
        new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT));

    setDefaultPadding(Spacing.LEFT, mEditText.getPaddingLeft());
    setDefaultPadding(Spacing.TOP, mEditText.getPaddingTop());
    setDefaultPadding(Spacing.RIGHT, mEditText.getPaddingRight());
    setDefaultPadding(Spacing.BOTTOM, mEditText.getPaddingBottom());
    mComputedPadding = spacingToFloatArray(getPadding());
  }

  @Override
  public void measure(
      CSSNode node,
      float width,
      CSSMeasureMode widthMode,
      float height,
      CSSMeasureMode heightMode,
      MeasureOutput measureOutput) {
    // measure() should never be called before setThemedContext()
    EditText editText = Assertions.assertNotNull(mEditText);

    editText.setTextSize(
        TypedValue.COMPLEX_UNIT_PX,
        mFontSize == UNSET ?
            (int) Math.ceil(PixelUtil.toPixelFromSP(ViewDefaults.FONT_SIZE_SP)) : mFontSize);
    mComputedPadding = spacingToFloatArray(getPadding());
    editText.setPadding(
        (int) Math.ceil(getPadding().get(Spacing.LEFT)),
        (int) Math.ceil(getPadding().get(Spacing.TOP)),
        (int) Math.ceil(getPadding().get(Spacing.RIGHT)),
        (int) Math.ceil(getPadding().get(Spacing.BOTTOM)));

    if (mNumberOfLines != UNSET) {
      editText.setLines(mNumberOfLines);
    }

    editText.measure(getMeasureSpec(width, widthMode), getMeasureSpec(height, heightMode));
    measureOutput.width = editText.getMeasuredWidth();
    measureOutput.height = editText.getMeasuredHeight();
  }

  private int getMeasureSpec(float size, CSSMeasureMode mode) {
    if (mode == CSSMeasureMode.EXACTLY) {
      return MeasureSpec.makeMeasureSpec((int) size, MeasureSpec.EXACTLY);
    } else if (mode == CSSMeasureMode.AT_MOST) {
      return MeasureSpec.makeMeasureSpec((int) size, MeasureSpec.AT_MOST);
    } else {
      return MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
    }
  }

  @Override
  public void onBeforeLayout() {
    // We don't have to measure the text within the text input.
    return;
  }

  @ReactProp(name = "mostRecentEventCount")
  public void setMostRecentEventCount(int mostRecentEventCount) {
    mJsEventCount = mostRecentEventCount;
  }

  @Override
  public void onCollectExtraUpdates(UIViewOperationQueue uiViewOperationQueue) {
    super.onCollectExtraUpdates(uiViewOperationQueue);
    if (mComputedPadding != null) {
      uiViewOperationQueue.enqueueUpdateExtraData(getReactTag(), mComputedPadding);
      mComputedPadding = null;
    }

    if (mJsEventCount != UNSET) {
      Spannable preparedSpannableText = fromTextCSSNode(this);
      ReactTextUpdate reactTextUpdate =
          new ReactTextUpdate(preparedSpannableText, mJsEventCount, mContainsImages);
      uiViewOperationQueue.enqueueUpdateExtraData(getReactTag(), reactTextUpdate);
    }
  }

  @Override
  public void setPadding(int spacingType, float padding) {
    super.setPadding(spacingType, padding);
    mComputedPadding = spacingToFloatArray(getPadding());
    markUpdated();
  }

  private static float[] spacingToFloatArray(Spacing spacing) {
    return new float[] {
        spacing.get(Spacing.LEFT),
        spacing.get(Spacing.TOP),
        spacing.get(Spacing.RIGHT),
        spacing.get(Spacing.BOTTOM),
    };
  }
}
