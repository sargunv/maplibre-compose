package dev.sargunv.maplibrecompose.core

import androidx.compose.runtime.Immutable

/**
 * Defines which user map gestures are enabled
 *
 * @param isTiltGesturesEnabled whether user may tilt (pitch) the map by vertically dragging two
 *   fingers up or down
 * @param isZoomGesturesEnabled whether user may zoom the map in and out by pinching two fingers or
 *   by double tapping, holding, and moving the finger up and down as well as simply double tapping
 *   to zoom in
 *
 * Note: On desktop and web, disabling zoom gestures will also disable two-finger rotating.
 *
 * @param isRotateGesturesEnabled whether user may rotate the map by moving two fingers in a
 *   circular motion
 *
 * Note: on desktop and web, this also toggles tilting+rotating with the mouse.
 *
 * @param isScrollGesturesEnabled whether user may scroll the map by dragging or swiping with one
 *   finger
 * @param isKeyboardGesturesEnabled on desktop and web, whether user may move the map with the
 *   keyboard
 */
@Immutable
public data class GestureSettings(
  val isTiltGesturesEnabled: Boolean = true,
  val isZoomGesturesEnabled: Boolean = true,
  val isRotateGesturesEnabled: Boolean = true,
  val isScrollGesturesEnabled: Boolean = true,
  val isKeyboardGesturesEnabled: Boolean = true,
) {
  public companion object {
    public val AllEnabled: GestureSettings = GestureSettings()
  }
}
