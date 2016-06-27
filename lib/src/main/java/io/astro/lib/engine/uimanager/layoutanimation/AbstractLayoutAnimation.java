// Copyright 2004-present Facebook. All Rights Reserved.

package io.astro.lib.engine.uimanager.layoutanimation;

import javax.annotation.Nullable;

import java.util.Map;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import io.astro.lib.engine.bridge.ReadableMap;
import io.astro.lib.engine.common.MapBuilder;
import io.astro.lib.engine.uimanager.IllegalViewOperationException;

/**
 * Class responsible for parsing and converting layout animation data into native {@link Animation}
 * in order to animate layout when a valid configuration has been supplied by the application.
 */
/* package */ abstract class AbstractLayoutAnimation {

  // Forces animation to be playing 10x slower, used for debug purposes.
  private static final boolean SLOWDOWN_ANIMATION_MODE = false;

  abstract boolean isValid();

  /**
   * Create an animation object for the current animation type, based on the view and final screen
   * coordinates. If the application-supplied configuraiton does not specify an animation definition
   * for this types, or if the animation definition is invalid, returns null.
   */
  abstract @Nullable Animation createAnimationImpl(View view, int x, int y, int width, int height);

  private static final Map<InterpolatorType, Interpolator> INTERPOLATOR = MapBuilder.of(
      InterpolatorType.LINEAR, new LinearInterpolator(),
      InterpolatorType.EASE_IN, new AccelerateInterpolator(),
      InterpolatorType.EASE_OUT, new DecelerateInterpolator(),
      InterpolatorType.EASE_IN_EASE_OUT, new AccelerateDecelerateInterpolator(),
      InterpolatorType.SPRING, new SimpleSpringInterpolator());

  private @Nullable Interpolator mInterpolator;
  private int mDelayMs;

  protected @Nullable AnimatedPropertyType mAnimatedProperty;
  protected int mDurationMs;

  public void reset() {
    mAnimatedProperty = null;
    mDurationMs = 0;
    mDelayMs = 0;
    mInterpolator = null;
  }

  public void initializeFromConfig(ReadableMap data, int globalDuration) {
    mAnimatedProperty = data.hasKey("property") ?
        AnimatedPropertyType.fromString(data.getString("property")) : null;
    mDurationMs = data.hasKey("duration") ? data.getInt("duration") : globalDuration;
    mDelayMs = data.hasKey("delay") ? data.getInt("delay") : 0;
    if (!data.hasKey("type")) {
      throw new IllegalArgumentException("Missing interpolation type.");
    }
    mInterpolator = getInterpolator(InterpolatorType.fromString(data.getString("type")));

    if (!isValid()) {
      throw new IllegalViewOperationException("Invalid layout animation : " + data);
    }
  }

  /**
   * Create an animation object to be used to animate the view, based on the animation config
   * supplied at initialization time and the new view position and size.
   *
   * @param view the view to create the animation for
   * @param x the new X position for the view
   * @param y the new Y position for the view
   * @param width the new width value for the view
   * @param height the new height value for the view
   */
   public final @Nullable Animation createAnimation(
       View view,
       int x,
       int y,
       int width,
       int height) {
    if (!isValid()) {
      return null;
    }
    Animation animation = createAnimationImpl(view, x, y, width, height);
    if (animation != null) {
      int slowdownFactor = SLOWDOWN_ANIMATION_MODE ? 10 : 1;
      animation.setDuration(mDurationMs * slowdownFactor);
      animation.setStartOffset(mDelayMs * slowdownFactor);
      animation.setInterpolator(mInterpolator);
    }
    return animation;
  }

  private static Interpolator getInterpolator(InterpolatorType type) {
    Interpolator interpolator = INTERPOLATOR.get(type);
    if (interpolator == null) {
      throw new IllegalArgumentException("Missing interpolator for type : " + type);
    }
    return interpolator;
  }
}
