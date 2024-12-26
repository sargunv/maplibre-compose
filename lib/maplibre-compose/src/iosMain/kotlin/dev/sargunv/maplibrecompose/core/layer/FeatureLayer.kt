package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.expression.ast.CompiledExpression
import dev.sargunv.maplibrecompose.expression.value.BooleanValue

internal actual sealed class FeatureLayer actual constructor(actual val source: Source) : Layer() {
  actual abstract var sourceLayer: String

  actual abstract fun setFilter(filter: CompiledExpression<BooleanValue>)
}
