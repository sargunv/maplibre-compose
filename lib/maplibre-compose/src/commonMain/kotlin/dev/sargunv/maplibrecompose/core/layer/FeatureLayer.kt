package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.expression.BooleanValue
import dev.sargunv.maplibrecompose.expression.CompiledExpression

internal expect sealed class FeatureLayer(source: Source) : Layer {
  val source: Source
  abstract var sourceLayer: String

  abstract fun setFilter(filter: CompiledExpression<BooleanValue>)
}
