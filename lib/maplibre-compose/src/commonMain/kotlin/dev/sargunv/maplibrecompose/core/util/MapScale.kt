package dev.sargunv.maplibrecompose.core.util

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.kevincianfarini.alchemist.scalar.nanometers
import io.github.kevincianfarini.alchemist.type.Length
import io.github.kevincianfarini.alchemist.unit.LengthUnit.International.Nanometer
import kotlin.jvm.JvmInline
import kotlin.math.roundToLong

public operator fun Length.div(dp: Dp): MapScale =
  MapScale((this.toLong(Nanometer) / dp.value).roundToLong().nanometers)

public operator fun Length.div(scale: MapScale): Dp = (this / scale.lengthPerDp).dp

public operator fun Dp.times(scale: MapScale): Length = scale * this

public operator fun Number.times(scale: MapScale): MapScale = scale * this

/**
 * Represents a map scale: a ratio of real-world distance ([Length]) to device-independent pixels
 * ([Dp]).
 */
@JvmInline
public value class MapScale @PublishedApi internal constructor(public val lengthPerDp: Length) :
  Comparable<MapScale> {

  public operator fun times(dp: Dp): Length =
    (lengthPerDp.toLong(Nanometer) * dp.value).roundToLong().nanometers

  public operator fun div(other: MapScale): Double {
    return lengthPerDp / other.lengthPerDp
  }

  public operator fun div(other: Number): MapScale =
    MapScale((lengthPerDp.toLong(Nanometer) / other.toDouble()).roundToLong().nanometers)

  public operator fun unaryMinus(): MapScale = MapScale(-lengthPerDp)

  public operator fun times(other: Number): MapScale =
    MapScale((lengthPerDp.toLong(Nanometer) * other.toDouble()).roundToLong().nanometers)

  override fun toString(): String {
    return "$lengthPerDp/dp"
  }

  override fun compareTo(other: MapScale): Int = lengthPerDp.compareTo(other.lengthPerDp)
}
