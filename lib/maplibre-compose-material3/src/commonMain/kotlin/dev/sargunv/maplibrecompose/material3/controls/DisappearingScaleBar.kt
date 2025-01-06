package dev.sargunv.maplibrecompose.material3.controls

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import kotlinx.coroutines.delay
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * An animated scale bar that appears when the [zoom] level of the map changes, and then disappears
 * after [visibilityDuration]. This composable wraps [ScaleBar] with visibility animations.
 *
 * @param metersPerDp how many meters are displayed in one device independent pixel (dp), i.e. the
 *   scale. See [CameraState.metersPerDpAtTarget][dev.sargunv.maplibrecompose.compose.CameraState.metersPerDpAtTarget]
 * @param zoom zoom level of the map
 * @param modifier the [Modifier] to be applied to this layout node
 * @param measure which measure to show on the scale bar (feet/miles, meters/kilometers or both)
 * @param haloColor halo for better visibility when displayed on top of the map
 * @param color scale bar and text color.
 * @param textStyle the text style. The text size is the deciding factor how large the scale bar is
 *   is displayed.
 * @param visibilityDuration how long it should be visible after the zoom changed
 * @param enterTransition EnterTransition(s) used for the appearing animation
 * @param exitTransition ExitTransition(s) used for the disappearing animation
 */
@Composable
public fun DisappearingScaleBar(
  metersPerDp: Double,
  zoom: Double,
  modifier: Modifier = Modifier,
  measure: ScaleBarMeasure = ScaleBarMeasure.IMPERIAL_AND_METRIC,
  haloColor: Color = MaterialTheme.colorScheme.surface,
  color: Color = contentColorFor(haloColor),
  textStyle: TextStyle = MaterialTheme.typography.labelMedium,
  visibilityDuration: Duration = 3.seconds,
  enterTransition: EnterTransition = fadeIn(),
  exitTransition: ExitTransition = fadeOut(),
) {
  val visible = remember { MutableTransitionState(true) }

  LaunchedEffect(zoom) {
    // Show ScaleBar
    visible.targetState = true
    delay(visibilityDuration)
    // Hide ScaleBar after timeout period
    visible.targetState = false
  }

  AnimatedVisibility(
    visibleState = visible,
    modifier = modifier,
    enter = enterTransition,
    exit = exitTransition,
  ) {
    ScaleBar(
      metersPerDp = metersPerDp,
      measure = measure,
      haloColor = haloColor,
      color = color,
      textStyle = textStyle,
    )
  }
}
