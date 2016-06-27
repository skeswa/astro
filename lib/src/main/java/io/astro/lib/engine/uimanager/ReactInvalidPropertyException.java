/**
 * Copyright (c) 2015-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package io.astro.lib.engine.uimanager;

public class ReactInvalidPropertyException extends RuntimeException {

  public ReactInvalidPropertyException(String property, String value, String expectedValues) {
    super("Invalid React property `" + property + "` with value `" + value +
            "`, expected " + expectedValues);
  }
}