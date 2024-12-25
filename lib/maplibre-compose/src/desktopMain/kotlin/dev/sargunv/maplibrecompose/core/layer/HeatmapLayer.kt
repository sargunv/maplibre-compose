package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.expression.BooleanValue
import dev.sargunv.maplibrecompose.core.expression.ColorValue
import dev.sargunv.maplibrecompose.core.expression.DpValue
import dev.sargunv.maplibrecompose.core.expression.Expression
import dev.sargunv.maplibrecompose.core.expression.FloatValue
import dev.sargunv.maplibrecompose.core.source.Source

internal actual class HeatmapLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {
  override val impl = TODO()

  actual override var sourceLayer: String = TODO()

  actual override fun setFilter(filter: Expression<BooleanValue>) {
    TODO()
  }

  actual fun setHeatmapRadius(radius: Expression<DpValue>) {
    TODO()
  }

  actual fun setHeatmapWeight(weight: Expression<FloatValue>) {
    TODO()
  }

  actual fun setHeatmapIntensity(intensity: Expression<FloatValue>) {
    TODO()
  }

  actual fun setHeatmapColor(color: Expression<ColorValue>) {
    TODO()
  }

  actual fun setHeatmapOpacity(opacity: Expression<FloatValue>) {
    TODO()
  }
}
