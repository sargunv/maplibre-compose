package dev.sargunv.maplibrecompose.material3.controls

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import dev.sargunv.maplibrecompose.material3.defaultScaleBarMeasures
import dev.sargunv.maplibrecompose.material3.drawPathsWithHalo
import dev.sargunv.maplibrecompose.material3.drawTextWithHalo
import dev.sargunv.maplibrecompose.material3.generated.Res
import dev.sargunv.maplibrecompose.material3.generated.feet_symbol
import dev.sargunv.maplibrecompose.material3.generated.kilometers_symbol
import dev.sargunv.maplibrecompose.material3.generated.meters_symbol
import dev.sargunv.maplibrecompose.material3.generated.miles_symbol
import dev.sargunv.maplibrecompose.material3.generated.yards_symbol
import org.jetbrains.compose.resources.stringResource

/** Which measures to show on the scale bar. */
public data class ScaleBarMeasures(val first: ScaleBarMeasure, val second: ScaleBarMeasure? = null)

/** Which measure to show on the scale bar. */
public enum class ScaleBarMeasure {
  /** meters / kilometers */
  Metric,
  /** feet / miles */
  FeetAndMiles,
  /** yards / miles */
  YardsAndMiles,
}

/**
 * A scale bar composable that shows the current scale of the map in feet, meters or feet and meters
 * when zoomed in to the map, changing to miles and kilometers, respectively, when zooming out.
 *
 * @param metersPerDp how many meters are displayed in one device independent pixel (dp), i.e. the
 *   scale. See
 *   [CameraState.metersPerDpAtTarget][dev.sargunv.maplibrecompose.compose.CameraState.metersPerDpAtTarget]
 * @param modifier the [Modifier] to be applied to this layout node
 * @param measures which measure to show on the scale bar (feet/miles, meters/kilometers or both).
 *   If `null`, a measure will be selected based on the system settings or otherwise the user's
 *   locale.
 * @param haloColor halo for better visibility when displayed on top of the map
 * @param color scale bar and text color.
 * @param textStyle the text style. The text size is the deciding factor how large the scale bar is
 *   is displayed.
 * @param alignment horizontal alignment of the scale bar and text
 */
@Composable
public fun ScaleBar(
  metersPerDp: Double,
  modifier: Modifier = Modifier,
  measures: ScaleBarMeasures? = null,
  haloColor: Color = MaterialTheme.colorScheme.surface,
  color: Color = contentColorFor(haloColor),
  textStyle: TextStyle = MaterialTheme.typography.labelSmall,
  alignment: Alignment.Horizontal = Alignment.Start,
) {
  val m = stringResource(Res.string.meters_symbol)
  val km = stringResource(Res.string.kilometers_symbol)
  val ft = stringResource(Res.string.feet_symbol)
  val yd = stringResource(Res.string.yards_symbol)
  val mi = stringResource(Res.string.miles_symbol)

  @Suppress("NAME_SHADOWING") val measures = measures ?: defaultScaleBarMeasures()

  val textMeasurer = rememberTextMeasurer()
  // longest possible text
  val maxTextSizePx =
    remember(textMeasurer, textStyle, m, km, ft, yd, mi) {
      listOf(m, km, ft, yd, mi)
        .map { textMeasurer.measure("5000 $it", textStyle).size }
        .maxBy { it.width }
    }
  val maxTextSize = with(LocalDensity.current) { maxTextSizePx.toSize().toDpSize() }

  // bar stroke width
  val strokeWidth = 2.dp
  val haloStrokeWidth = 1.dp
  // padding of text to bar stroke
  val textHorizontalPadding = 4.dp
  val textVerticalPadding = 0.dp

  // multiplied by 2.5 because the next stop can be the x2.5 of a previous stop (e.g. 2km -> 5km),
  // so the bar can end at approx 1/2.5th of the total width. We want to avoid that the bar
  // intersects with the text, i.e. is drawn behind the text
  val totalMaxWidth = maxTextSize.width * 2.5f + (textHorizontalPadding + strokeWidth) * 2f

  val fullStrokeWidth = haloStrokeWidth * 2 + strokeWidth

  val textCount = if (measures.second != null) 2 else 1
  val totalHeight = (maxTextSize.height + textVerticalPadding) * textCount + fullStrokeWidth

  Canvas(modifier.size(totalMaxWidth, totalHeight)) {
    val fullStrokeWidthPx = fullStrokeWidth.toPx()
    val textHeightPx = maxTextSizePx.height
    val textHorizontalPaddingPx = textHorizontalPadding.toPx()
    val textVerticalPaddingPx = textVerticalPadding.toPx()
    // scale bar start/end should not overlap horizontally with canvas bounds
    val maxBarLengthPx = size.width - fullStrokeWidthPx
    // bar ends should go to the vertical center of the text
    val barEndsHeightPx = textHeightPx / 2f + textVerticalPadding.toPx() + fullStrokeWidthPx / 2f
    val metersPerPx = metersPerDp.dp.toPx()

    var y = 0f
    val paths = ArrayList<List<Offset>>(2)
    val texts = ArrayList<Pair<Offset, TextLayoutResult>>(2)

    if (true) { // just want a scope here
      val params = scaleBarParams(measures.first, metersPerPx, maxBarLengthPx, m, km, ft, yd, mi)
      val offsetX =
        alignment.align(
          size = params.barLengthPx.toInt(),
          space = (size.width - fullStrokeWidthPx).toInt(),
          layoutDirection = layoutDirection,
        )
      paths.add(
        listOf(
          Offset(offsetX + fullStrokeWidthPx / 2f, 0f + textHeightPx / 2f),
          Offset(0f, barEndsHeightPx),
          Offset(params.barLengthPx, 0f),
          Offset(0f, -barEndsHeightPx),
        )
      )
      texts.add(
        Pair(
          Offset(textHorizontalPaddingPx + fullStrokeWidthPx, 0f),
          textMeasurer.measure(params.text, textStyle),
        )
      )

      y += textHeightPx + textVerticalPaddingPx
    }

    if (measures.second != null) {
      val params = scaleBarParams(measures.second, metersPerPx, maxBarLengthPx, m, km, ft, yd, mi)
      val offsetX =
        alignment.align(
          size = params.barLengthPx.toInt(),
          space = (size.width - fullStrokeWidthPx).toInt(),
          layoutDirection = layoutDirection,
        )
      paths.add(
        listOf(
          Offset(offsetX + fullStrokeWidthPx / 2f, y + fullStrokeWidthPx / 2f + barEndsHeightPx),
          Offset(0f, -barEndsHeightPx),
          Offset(params.barLengthPx, 0f),
          Offset(0f, +barEndsHeightPx),
        )
      )
      texts.add(
        Pair(
          Offset(
            textHorizontalPaddingPx + fullStrokeWidthPx,
            y + textVerticalPaddingPx + fullStrokeWidthPx,
          ),
          textMeasurer.measure(params.text, textStyle),
        )
      )
    }

    drawPathsWithHalo(
      color = color,
      haloColor = haloColor,
      paths = paths,
      strokeWidth = strokeWidth.toPx(),
      haloWidth = haloStrokeWidth.toPx(),
      cap = StrokeCap.Round,
    )

    for ((offset, textLayoutResult) in texts) {
      val offsetX =
        alignment.align(
          size = textLayoutResult.size.width,
          space = (size.width - 2 * offset.x).toInt(),
          layoutDirection = layoutDirection,
        ) + offset.x
      drawTextWithHalo(
        textLayoutResult = textLayoutResult,
        topLeft = Offset(offsetX, offset.y),
        color = color,
        haloColor = haloColor,
        haloWidth = haloStrokeWidth.toPx(),
      )
    }
  }
}

private const val METERS_IN_FEET: Double = 0.3048
private const val METERS_IN_YARD: Double = 0.9144
private const val FEET_IN_MILE: Int = 5280
private const val YARDS_IN_MILE: Int = 1760

private data class ScaleBarParams(val barLengthPx: Float, val text: String)

private fun scaleBarParams(
  scaleBarMeasure: ScaleBarMeasure,
  metersPerPx: Float,
  maxBarLengthPx: Float,
  m: String,
  km: String,
  ft: String,
  yd: String,
  mi: String,
): ScaleBarParams =
  when (scaleBarMeasure) {
    ScaleBarMeasure.Metric -> metricBarParams(metersPerPx, maxBarLengthPx, m, km)
    ScaleBarMeasure.FeetAndMiles -> feetBarParams(metersPerPx, maxBarLengthPx, ft, mi)
    ScaleBarMeasure.YardsAndMiles -> yardsBarParams(metersPerPx, maxBarLengthPx, yd, mi)
  }

private fun metricBarParams(
  metersPerPx: Float,
  maxBarLengthPx: Float,
  m: String,
  km: String,
): ScaleBarParams {
  val stop = findStop(maxBarLengthPx * metersPerPx, METRIC_STOPS)
  return ScaleBarParams(
    text =
      if (stop >= 1000) {
        "${(stop/1000f).toShortString()} $km"
      } else {
        "${stop.toShortString()} $m"
      },
    barLengthPx = stop / metersPerPx,
  )
}

private fun yardsBarParams(
  metersPerPx: Float,
  maxBarLengthPx: Float,
  yd: String,
  mi: String,
): ScaleBarParams {
  val stop = findStop((maxBarLengthPx * metersPerPx / METERS_IN_YARD).toFloat(), YARDS_STOPS)
  return ScaleBarParams(
    text =
      if (stop >= YARDS_IN_MILE) {
        "${(stop/YARDS_IN_MILE).toShortString()} $mi"
      } else {
        "${stop.toShortString()} $yd"
      },
    barLengthPx = (stop * METERS_IN_YARD / metersPerPx).toFloat(),
  )
}

private fun feetBarParams(
  metersPerPx: Float,
  maxBarLengthPx: Float,
  ft: String,
  mi: String,
): ScaleBarParams {
  val stop = findStop((maxBarLengthPx * metersPerPx / METERS_IN_FEET).toFloat(), FEET_STOPS)
  return ScaleBarParams(
    text =
      if (stop >= FEET_IN_MILE) {
        "${(stop/FEET_IN_MILE).toShortString()} $mi"
      } else {
        "${stop.toShortString()} $ft"
      },
    barLengthPx = (stop * METERS_IN_FEET / metersPerPx).toFloat(),
  )
}

/**
 * find the largest stop in the list of stops (sorted in ascending order) that is below or equal
 * [max].
 */
private fun findStop(max: Float, stops: List<Float>): Float {
  var maxStop = stops.first()
  for (stop in stops) {
    if (stop <= max) maxStop = stop else break
  }
  return maxStop
}

/** format like an int if this has no decimals */
private fun Float.toShortString() = if (this % 1 == 0f) toInt().toString() else toString()

/** list of meters stops */
private val METRIC_STOPS: List<Float> =
  listOf(
    0.1f,
    0.2f,
    0.5f,
    1f,
    2f,
    5f,
    10f,
    20f,
    50f,
    100f,
    200f,
    500f,
    1000f,
    2000f,
    5000f,
    10000f,
    20000f,
    50000f,
    100000f,
    200000f,
    500000f,
    1000000f,
    2000000f,
    5000000f,
    10000000f,
    20000000f,
    40000000f,
  )

/** list of feet stops */
private val FEET_STOPS: List<Float> =
  listOf(
    0.1f,
    0.2f,
    0.5f,
    1f,
    2f,
    5f,
    10f,
    20f,
    50f,
    100f,
    200f,
    500f,
    1000f,
    2000f,
    1f * FEET_IN_MILE,
    2f * FEET_IN_MILE,
    5f * FEET_IN_MILE,
    10f * FEET_IN_MILE,
    20f * FEET_IN_MILE,
    50f * FEET_IN_MILE,
    100f * FEET_IN_MILE,
    200f * FEET_IN_MILE,
    500f * FEET_IN_MILE,
    1000f * FEET_IN_MILE,
    2000f * FEET_IN_MILE,
    5000f * FEET_IN_MILE,
    10000f * FEET_IN_MILE,
    20000f * FEET_IN_MILE,
  )

/** list of feet stops */
private val YARDS_STOPS: List<Float> =
  listOf(
    0.1f,
    0.2f,
    0.5f,
    1f,
    2f,
    5f,
    10f,
    20f,
    50f,
    100f,
    200f,
    500f,
    1000f,
    1f * YARDS_IN_MILE,
    2f * YARDS_IN_MILE,
    5f * YARDS_IN_MILE,
    10f * YARDS_IN_MILE,
    20f * YARDS_IN_MILE,
    50f * YARDS_IN_MILE,
    100f * YARDS_IN_MILE,
    200f * YARDS_IN_MILE,
    500f * YARDS_IN_MILE,
    1000f * YARDS_IN_MILE,
    2000f * YARDS_IN_MILE,
    5000f * YARDS_IN_MILE,
    10000f * YARDS_IN_MILE,
    20000f * YARDS_IN_MILE,
  )
