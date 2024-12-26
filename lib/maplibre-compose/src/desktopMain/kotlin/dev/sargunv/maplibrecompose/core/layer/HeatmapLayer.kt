package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.expressions.ast.CompiledExpression
import dev.sargunv.maplibrecompose.expressions.value.BooleanValue
import dev.sargunv.maplibrecompose.expressions.value.ColorValue
import dev.sargunv.maplibrecompose.expressions.value.DpValue
import dev.sargunv.maplibrecompose.expressions.value.FloatValue

internal actual class HeatmapLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {
  override val impl = TODO()

  actual override var sourceLayer: String = TODO()

  actual override fun setFilter(filter: CompiledExpression<BooleanValue>) {
    TODO()
  }

  actual fun setHeatmapRadius(radius: CompiledExpression<DpValue>) {
    TODO()
  }

  actual fun setHeatmapWeight(weight: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setHeatmapIntensity(intensity: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setHeatmapColor(color: CompiledExpression<ColorValue>) {
    TODO()
  }

  actual fun setHeatmapOpacity(opacity: CompiledExpression<FloatValue>) {
    TODO()
  }
}
