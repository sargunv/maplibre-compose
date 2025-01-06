package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.expressions.ast.CompiledExpression
import dev.sargunv.maplibrecompose.expressions.value.BooleanValue
import dev.sargunv.maplibrecompose.expressions.value.ColorValue
import dev.sargunv.maplibrecompose.expressions.value.DpValue
import dev.sargunv.maplibrecompose.expressions.value.FloatValue

internal expect class HeatmapLayer(id: String, source: Source) : FeatureLayer {
  override var sourceLayer: String

  override fun setFilter(filter: CompiledExpression<BooleanValue>)

  fun setHeatmapRadius(radius: CompiledExpression<DpValue>)

  fun setHeatmapWeight(weight: CompiledExpression<FloatValue>)

  fun setHeatmapIntensity(intensity: CompiledExpression<FloatValue>)

  fun setHeatmapColor(color: CompiledExpression<ColorValue>)

  fun setHeatmapOpacity(opacity: CompiledExpression<FloatValue>)
}
