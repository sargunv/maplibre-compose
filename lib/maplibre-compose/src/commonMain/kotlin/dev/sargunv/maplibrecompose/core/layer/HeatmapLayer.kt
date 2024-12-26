package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.expression.BooleanValue
import dev.sargunv.maplibrecompose.expression.ColorValue
import dev.sargunv.maplibrecompose.expression.CompiledExpression
import dev.sargunv.maplibrecompose.expression.DpValue
import dev.sargunv.maplibrecompose.expression.FloatValue

internal expect class HeatmapLayer(id: String, source: Source) : FeatureLayer {
  override var sourceLayer: String

  override fun setFilter(filter: CompiledExpression<BooleanValue>)

  fun setHeatmapRadius(radius: CompiledExpression<DpValue>)

  fun setHeatmapWeight(weight: CompiledExpression<FloatValue>)

  fun setHeatmapIntensity(intensity: CompiledExpression<FloatValue>)

  fun setHeatmapColor(color: CompiledExpression<ColorValue>)

  fun setHeatmapOpacity(opacity: CompiledExpression<FloatValue>)
}
