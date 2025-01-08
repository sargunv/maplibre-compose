package dev.sargunv.maplibrecompose.material3.controls

import androidx.compose.runtime.Composable
import dev.sargunv.maplibrecompose.material3.generated.Res
import dev.sargunv.maplibrecompose.material3.generated.feet_symbol
import dev.sargunv.maplibrecompose.material3.generated.kilometers_symbol
import dev.sargunv.maplibrecompose.material3.generated.meters_symbol
import dev.sargunv.maplibrecompose.material3.generated.miles_symbol
import dev.sargunv.maplibrecompose.material3.generated.yards_symbol
import org.jetbrains.compose.resources.stringResource

/** A measure to show in the scale bar */
public interface ScaleBarMeasure {
  /** one unit of this measure in meters */
  public val unitToMeter: Double

  /** List of stops, sorted ascending, at which the scalebar should show */
  public val stops: List<Float>

  @Composable public fun getText(stop: Float): String

  /** A measure of meters and kilometers */
  public data object Metric : ScaleBarMeasure {
    override val unitToMeter: Double = 1.0

    override val stops: List<Float> =
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

    @Composable
    override fun getText(stop: Float): String =
      if (stop >= 1000) {
        "${(stop/1000f).toShortString()} ${stringResource(Res.string.kilometers_symbol)}"
      } else {
        "${stop.toShortString()} ${stringResource(Res.string.meters_symbol)}"
      }
  }

  /** A measure of international feet and miles */
  public data object FeetAndMiles : ScaleBarMeasure {

    private const val FEET_IN_MILE: Int = 5280

    override val unitToMeter: Double = 0.3048

    override val stops: List<Float> =
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

    @Composable
    override fun getText(stop: Float): String =
      if (stop >= FEET_IN_MILE) {
        "${(stop/FEET_IN_MILE).toShortString()} ${stringResource(Res.string.miles_symbol)}"
      } else {
        "${stop.toShortString()} ${stringResource(Res.string.feet_symbol)}"
      }
  }

  /** A measure of international yard and miles */
  public data object YardsAndMiles : ScaleBarMeasure {

    private const val YARDS_IN_MILE: Int = 1760

    override val unitToMeter: Double = 0.9144

    override val stops: List<Float> =
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

    @Composable
    override fun getText(stop: Float): String =
      if (stop >= YARDS_IN_MILE) {
        "${(stop/YARDS_IN_MILE).toShortString()} ${stringResource(Res.string.miles_symbol)}"
      } else {
        "${stop.toShortString()} ${stringResource(Res.string.yards_symbol)}"
      }
  }
}

/** format like an int if this has no decimals */
private fun Float.toShortString() = if (this % 1 == 0f) toInt().toString() else toString()
