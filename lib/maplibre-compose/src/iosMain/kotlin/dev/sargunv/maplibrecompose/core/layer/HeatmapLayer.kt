package dev.sargunv.maplibrecompose.core.layer

import cocoapods.MapLibre.MLNHeatmapStyleLayer
import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.core.util.toNSExpression
import dev.sargunv.maplibrecompose.core.util.toNSPredicate
import dev.sargunv.maplibrecompose.expressions.ast.CompiledExpression
import dev.sargunv.maplibrecompose.expressions.value.BooleanValue
import dev.sargunv.maplibrecompose.expressions.value.ColorValue
import dev.sargunv.maplibrecompose.expressions.value.DpValue
import dev.sargunv.maplibrecompose.expressions.value.FloatValue

internal actual class HeatmapLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {

  override val impl = MLNHeatmapStyleLayer(id, source.impl)

  actual override var sourceLayer: String
    get() = impl.sourceLayerIdentifier!!
    set(value) {
      warnIfUnloaded("sourceLayerIdentifier")
      if (!isUnloaded) impl.sourceLayerIdentifier = value
    }

  actual override fun setFilter(filter: CompiledExpression<BooleanValue>) {
    warnIfUnloaded("setFilter")
    if (!isUnloaded) impl.predicate = filter.toNSPredicate()
  }

  actual fun setHeatmapRadius(radius: CompiledExpression<DpValue>) {
    warnIfUnloaded("setHeatmapRadius")
    if (!isUnloaded) impl.heatmapRadius = radius.toNSExpression()
  }

  actual fun setHeatmapWeight(weight: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setHeatmapWeight")
    if (!isUnloaded) impl.heatmapWeight = weight.toNSExpression()
  }

  actual fun setHeatmapIntensity(intensity: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setHeatmapIntensity")
    if (!isUnloaded) impl.heatmapIntensity = intensity.toNSExpression()
  }

  actual fun setHeatmapColor(color: CompiledExpression<ColorValue>) {
    warnIfUnloaded("setHeatmapColor")
    if (!isUnloaded) impl.heatmapColor = color.toNSExpression()
  }

  actual fun setHeatmapOpacity(opacity: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setHeatmapOpacity")
    if (!isUnloaded) impl.heatmapOpacity = opacity.toNSExpression()
  }
}
