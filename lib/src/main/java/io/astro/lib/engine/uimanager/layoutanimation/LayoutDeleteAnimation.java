// Copyright 2004-present Facebook. All Rights Reserved.

package io.astro.lib.engine.uimanager.layoutanimation;

/**
 * Class responsible for handling layout view deletion animation, applied to view whenever a
 * valid config was supplied for the layout animation of DELETE type.
 */
/* package */ class LayoutDeleteAnimation extends BaseLayoutAnimation {

  @Override
  boolean isReverse() {
    return true;
  }
}
