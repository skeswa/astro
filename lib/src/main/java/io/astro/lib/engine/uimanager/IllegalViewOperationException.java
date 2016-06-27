/**
 * Copyright (c) 2015-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package io.astro.lib.engine.uimanager;

/**
 * An exception caused by JS requesting the UI manager to perform an illegal view operation.
 */
public class IllegalViewOperationException extends RuntimeException {

  public IllegalViewOperationException(String msg) {
    super(msg);
  }
}
