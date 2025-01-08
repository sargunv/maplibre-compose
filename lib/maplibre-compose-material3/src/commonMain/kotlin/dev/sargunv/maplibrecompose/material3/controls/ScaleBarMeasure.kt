package dev.sargunv.maplibrecompose.material3.controls

import androidx.compose.runtime.Composable
import dev.sargunv.maplibrecompose.material3.generated.Res
import dev.sargunv.maplibrecompose.material3.generated.feet_symbol
import dev.sargunv.maplibrecompose.material3.generated.kilometers_symbol
import dev.sargunv.maplibrecompose.material3.generated.meters_symbol
import dev.sargunv.maplibrecompose.material3.generated.miles_symbol
import dev.sargunv.maplibrecompose.material3.generated.yards_symbol
import io.github.kevincianfarini.alchemist.scalar.feet
import io.github.kevincianfarini.alchemist.scalar.kilometers
import io.github.kevincianfarini.alchemist.scalar.meters
import io.github.kevincianfarini.alchemist.scalar.miles
import io.github.kevincianfarini.alchemist.scalar.nanometers
import io.github.kevincianfarini.alchemist.scalar.yards
import io.github.kevincianfarini.alchemist.type.Length
import io.github.kevincianfarini.alchemist.unit.LengthUnit
import kotlin.math.pow
import kotlin.math.roundToInt
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

/** A measure to show in the scale bar */
public interface ScaleBarMeasure {
  /** List of stops, sorted ascending, at which the scalebar should show */
  public val stops: List<Length>

  @Composable public fun getText(stop: Length): String

  /** A measure of meters and kilometers */
  public data object Metric : ScaleBarMeasure {

    override val stops: List<Length> =
      buildStops(mantissas = listOf(1, 2, 5).map { it.meters }, exponents = -1..7)

    @Composable
    override fun getText(stop: Length): String =
      if (stop >= 1.kilometers)
        stop.toDisplayString(LengthUnit.International.Kilometer, Res.string.kilometers_symbol)
      else stop.toDisplayString(LengthUnit.International.Meter, Res.string.meters_symbol)
  }

  /** A measure of international feet and miles */
  public data object FeetAndMiles : ScaleBarMeasure {

    override val stops: List<Length> =
      listOf(
          buildStops(mantissas = listOf(1, 2, 5).map { it.feet }, exponents = -1..3).dropLast(1),
          buildStops(mantissas = listOf(1, 2, 5).map { it.miles }, exponents = 0..4),
        )
        .flatten()

    @Composable
    override fun getText(stop: Length): String =
      if (stop >= 1.miles)
        stop.toDisplayString(LengthUnit.UnitedStatesCustomary.Mile, Res.string.miles_symbol)
      else stop.toDisplayString(LengthUnit.UnitedStatesCustomary.Foot, Res.string.feet_symbol)
  }

  /** A measure of international yard and miles */
  public data object YardsAndMiles : ScaleBarMeasure {

    override val stops: List<Length> =
      listOf(
          buildStops(mantissas = listOf(1, 2, 5).map { it.yards }, exponents = -1..3).dropLast(2),
          buildStops(mantissas = listOf(1, 2, 5).map { it.miles }, exponents = 0..4),
        )
        .flatten()

    @Composable
    override fun getText(stop: Length): String =
      if (stop >= 1.miles)
        stop.toDisplayString(LengthUnit.UnitedStatesCustomary.Mile, Res.string.miles_symbol)
      else stop.toDisplayString(LengthUnit.UnitedStatesCustomary.Yard, Res.string.yards_symbol)
  }
}

/** format a number with a unit symbol, not showing the decimal point if it's an integer */
@Composable
private fun Length.toDisplayString(unit: LengthUnit, symbolResource: StringResource): String {
  val symbol = stringResource(symbolResource)
  val value = this.toDouble(unit)
  val valueAsInt = value.toInt()
  return if (valueAsInt.toDouble() == value) "${valueAsInt} $symbol" else "${value} $symbol"
}

/** build a list of stops by multiplying mantissas by 10^exponents, like scientific notation */
private fun buildStops(mantissas: List<Length>, exponents: IntRange) = buildList {
  for (e in exponents) for (m in mantissas) add(m * 10.0.pow(e))
}

// https://github.com/kevincianfarini/alchemist/issues/53
private operator fun Length.times(other: Double) =
  (this.toDouble(LengthUnit.International.Nanometer) * other).roundToInt().nanometers
