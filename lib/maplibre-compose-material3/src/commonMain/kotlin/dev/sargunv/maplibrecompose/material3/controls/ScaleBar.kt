package dev.sargunv.maplibrecompose.material3.controls

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.sargunv.maplibrecompose.compose.CameraState
import dev.sargunv.maplibrecompose.material3.generated.Res
import dev.sargunv.maplibrecompose.material3.generated.feet_symbol
import dev.sargunv.maplibrecompose.material3.generated.kilometers_symbol
import dev.sargunv.maplibrecompose.material3.generated.meters_symbol
import dev.sargunv.maplibrecompose.material3.generated.miles_symbol
import kotlin.math.roundToInt
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource

public object ScaleBarDefaults {
  public val width: Dp = 65.dp
}

/** Which measure to show on the scale bar. */
public enum class ScaleBarMeasure {
  METRIC,
  IMPERIAL,
  METRIC_AND_IMPERIAL;

  public fun isMetric(): Boolean = this != IMPERIAL
  public fun isImperial(): Boolean = this != METRIC
}

/**
 * A scale bar composable that shows the current scale of the map in feet, meters or feet and meters
 * when zoomed in to the map, changing to miles and kilometers, respectively, when zooming out.
 */
@Composable
public fun ScaleBar(
  cameraState: CameraState,
  modifier: Modifier = Modifier,
  measure: ScaleBarMeasure = ScaleBarMeasure.METRIC_AND_IMPERIAL,
  width: Dp = ScaleBarDefaults.width,
  haloColor: Color = MaterialTheme.colorScheme.surface,
  color: Color = contentColorFor(haloColor),
  textStyle: TextStyle = MaterialTheme.typography.labelMedium,
) {
  val textMeasurer = rememberTextMeasurer(32)
  val textHeight = with(LocalDensity.current) {
    textMeasurer.measure("0", textStyle).size.height.toDp()
  }
  val strokeWidth = 2.dp
  val haloStrokeWidth = 1.dp
  val fullStrokeWidth = haloStrokeWidth * 2 + strokeWidth
  val textPadding = 2.dp
  val texts = if (measure == ScaleBarMeasure.METRIC_AND_IMPERIAL) 2 else 1
  val totalHeight = (textHeight + textPadding) * texts + fullStrokeWidth
  val horizontalTextPadding = fullStrokeWidth + textPadding

  val maxBarWidthInMeters = (width.value * cameraState.metersPerDpAtTarget).toInt()
  val metersStop = findStop(maxBarWidthInMeters, METRIC_STOPS)
  val metricText = if (metersStop >= 1000) {
    "${metersStop/1000} ${stringResource(Res.string.kilometers_symbol)}"
  } else {
    "$metersStop ${stringResource(Res.string.meters_symbol)}"
  }

  val maxBarWidthInFeet = (width.value * cameraState.metersPerDpAtTarget / METERS_IN_FEET).toInt()
  val feetStop = findStop(maxBarWidthInFeet, IMPERIAL_STOPS)
  val imperialText = if (feetStop >= FEET_IN_MILE) {
    "${feetStop/FEET_IN_MILE} ${stringResource(Res.string.miles_symbol)}"
  } else {
    "$feetStop ${stringResource(Res.string.feet_symbol)}"
  }

  // TODO RTL

  Canvas(modifier.size(width, totalHeight)) {
    var y = 0f
    val lines = mutableListOf<Pair<Offset, Offset>>()
    val metersPerPx = cameraState.metersPerDpAtTarget.dp.toPx()

    if (measure.isImperial()) {
      val barLength = metersStop * METERS_IN_FEET / metersPerPx
      lines.add(Offset(0f, 0f) to Offset(barLength.toFloat(), 0f))
      // todo vertical lines
    }

    if (measure.isMetric()) {
      val barLength = feetStop / metersPerPx
      lines.add(Offset(0f, 0f) to Offset(barLength, 0f))
      // todo vertical lines
    }

    drawLinesWithHalo(
      color = color,
      haloColor = haloColor,
      lines = lines,
      strokeWidth = strokeWidth.toPx(),
      haloWidth = haloStrokeWidth.toPx(),
      cap = StrokeCap.Round,
    )

    // text is drawn on above the lines
    if (measure.isImperial()) {
      drawTextWithHalo(
        textLayoutResult = textMeasurer.measure(
          text = imperialText,
          style = textStyle,
        ),
        topLeft = Offset(horizontalTextPadding.toPx(), y),
        color = color,
        haloColor = haloColor,
        haloWidth = haloStrokeWidth.toPx()
      )
    }
    if (measure.isMetric()) {
      drawTextWithHalo(
        textLayoutResult = textMeasurer.measure(
          text = metricText,
          style = textStyle,
        ),
        topLeft = Offset(horizontalTextPadding.toPx(), y),
        color = color,
        haloColor = haloColor,
        haloWidth = haloStrokeWidth.toPx()
      )
    }
  }
}

/**
 * An animated scale bar that appears when the zoom level of the map changes, and then disappears
 * after [visibilityDuration]. This composable wraps [ScaleBar] with visibility animations.
 */
@Composable
public fun DisappearingScaleBar(
  cameraState: CameraState,
  modifier: Modifier = Modifier,
  measure: ScaleBarMeasure = ScaleBarMeasure.METRIC_AND_IMPERIAL,
  width: Dp = ScaleBarDefaults.width,
  haloColor: Color = MaterialTheme.colorScheme.surface,
  color: Color = contentColorFor(haloColor),
  textStyle: TextStyle = MaterialTheme.typography.labelMedium,
  visibilityDuration: Duration = 3.seconds,
  enterTransition: EnterTransition = fadeIn(),
  exitTransition: ExitTransition = fadeOut(),
) {
  val visible = remember { MutableTransitionState(true) }

  LaunchedEffect(key1 = cameraState.position.zoom) {
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
      cameraState = cameraState,
      measure = measure,
      width = width,
      haloColor = haloColor,
      color = color,
      textStyle = textStyle,
    )
  }
}

private const val METERS_IN_FEET: Double = 0.3048
private const val FEET_IN_MILE: Int = 5280

private fun DrawScope.drawLinesWithHalo(
  color: Color,
  haloColor: Color,
  lines: List<Pair<Offset, Offset>>,
  strokeWidth: Float = Stroke.HairlineWidth,
  haloWidth: Float = Stroke.HairlineWidth,
  cap: StrokeCap = Stroke.DefaultCap
) {
  for ((start, end) in lines) {
    drawLine(
      color = haloColor,
      start = start,
      end = end,
      strokeWidth = strokeWidth + haloWidth * 2,
      cap = cap,
    )
  }
  for ((start, end) in lines) {
    drawLine(
      color = color,
      start = start,
      end = end,
      strokeWidth = strokeWidth,
      cap = cap,
    )
  }
}

private fun DrawScope.drawTextWithHalo(
  textLayoutResult: TextLayoutResult,
  topLeft: Offset = Offset.Zero,
  color: Color = Color.Unspecified,
  haloColor: Color = Color.Unspecified,
  haloWidth: Float = 0f,
) {
  // * 2 because the stroke is painted half outside and half inside of the text shape
  val stroke = Stroke(width = haloWidth * 2, cap = StrokeCap.Round, join = StrokeJoin.Round)
  drawText(
    textLayoutResult = textLayoutResult,
    color = haloColor,
    topLeft = topLeft,
    drawStyle = stroke
  )
  drawText(
    textLayoutResult = textLayoutResult,
    color = color,
    topLeft = topLeft,
  )
}

/**
 * find the largest stop in the list of stops (sorted in ascending order) that is below or equal
 * [max].
 */
private fun findStop(max: Int, stops: List<Int>): Int {
  var maxStop = stops.first()
  for (stop in stops) {
    if (stop <= max) maxStop = stop
    else break
  }
  return maxStop
}

/** list of meters stops */
private val METRIC_STOPS: List<Int> = listOf(
  1,
  2,
  5,
  10,
  20,
  50,
  100,
  200,
  300,
  500,
  1000,
  2000,
  5000,
  10000,
  20000,
  50000,
  100000,
  200000,
  500000,
  1000000,
  2000000,
  5000000,
  10000000,
  20000000,
  40000000,
)

/** list of feet stops */
private val IMPERIAL_STOPS: List<Int> = listOf(
  2,
  5,
  10,
  20,
  50,
  100,
  200,
  500,
  1000,
  2000,
  FEET_IN_MILE,
  2 * FEET_IN_MILE,
  5 * FEET_IN_MILE,
  10 * FEET_IN_MILE,
  20 * FEET_IN_MILE,
  50 * FEET_IN_MILE,
  100 * FEET_IN_MILE,
  200 * FEET_IN_MILE,
  500 * FEET_IN_MILE,
  1000 * FEET_IN_MILE,
  2000 * FEET_IN_MILE,
  5000 * FEET_IN_MILE,
  10000 * FEET_IN_MILE,
  20000 * FEET_IN_MILE,
)
