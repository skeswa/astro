// Copyright 2004-present Facebook. All Rights Reserved.

package io.astro.lib.engine.uimanager.layoutanimation;

/**
 * Class responsible for handling layout view creation animation, applied to view whenever a
 * valid config was supplied for the layout animation of CREATE type.
 */
/* package */ class LayoutCreateAnimation extends BaseLayoutAnimation {

  @Override
  boolean isReverse() {
    return false;
  }
}
