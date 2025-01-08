package dev.sargunv.maplibrecompose.material3.controls

import androidx.compose.runtime.Composable
import dev.sargunv.maplibrecompose.material3.generated.Res
import dev.sargunv.maplibrecompose.material3.generated.feet_symbol
import dev.sargunv.maplibrecompose.material3.generated.kilometers_symbol
import dev.sargunv.maplibrecompose.material3.generated.meters_symbol
import dev.sargunv.maplibrecompose.material3.generated.miles_symbol
import dev.sargunv.maplibrecompose.material3.generated.yards_symbol
import kotlin.math.pow
import org.jetbrains.compose.resources.stringResource

/** A measure to show in the scale bar */
public interface ScaleBarMeasure {
  /** one unit of this measure in meters */
  public val unitToMeter: Double

  /** List of stops, sorted ascending, at which the scalebar should show */
  public val stops: List<Double>

  @Composable public fun getText(stop: Double): String

  /** A measure of meters and kilometers */
  public data object Metric : ScaleBarMeasure {
    override val unitToMeter: Double = 1.0

    override val stops: List<Double> = buildStops(mantissas = listOf(1, 2, 5), exponents = -1..7)

    @Composable
    override fun getText(stop: Double): String =
      if (stop >= 1000) {
        "${(stop/1000.0).toShortString()} ${stringResource(Res.string.kilometers_symbol)}"
      } else {
        "${stop.toShortString()} ${stringResource(Res.string.meters_symbol)}"
      }
  }

  /** A measure of international feet and miles */
  public data object FeetAndMiles : ScaleBarMeasure {

    private const val FEET_IN_MILE: Int = 5280

    override val unitToMeter: Double = 0.3048

    override val stops: List<Double> =
      listOf(
          buildStops(mantissas = listOf(1, 2, 5), exponents = -1..3).dropLast(1),
          buildStops(mantissas = listOf(1, 2, 5), exponents = 0..4).map { it * FEET_IN_MILE },
        )
        .flatten()

    @Composable
    override fun getText(stop: Double): String =
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

    override val stops: List<Double> =
      listOf(
          buildStops(mantissas = listOf(1, 2, 5), exponents = -1..3).dropLast(2),
          buildStops(mantissas = listOf(1, 2, 5), exponents = 0..4).map { it * YARDS_IN_MILE },
        )
        .flatten()

    @Composable
    override fun getText(stop: Double): String =
      if (stop >= YARDS_IN_MILE) {
        "${(stop/YARDS_IN_MILE).toShortString()} ${stringResource(Res.string.miles_symbol)}"
      } else {
        "${stop.toShortString()} ${stringResource(Res.string.yards_symbol)}"
      }
  }
}

/** format like an int if this has no decimals */
private fun Double.toShortString() = if (this % 1 == 0.0) toInt().toString() else toString()

/** build a list of stops by multiplying mantissas by 10^exponents, like scientific notation */
private fun buildStops(mantissas: List<Int>, exponents: IntRange) = buildList {
  for (e in exponents) for (m in mantissas) add(m * 10.0.pow(e))
}
