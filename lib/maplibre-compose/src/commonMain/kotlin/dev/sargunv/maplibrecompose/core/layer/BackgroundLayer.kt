package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.expressions.ast.CompiledExpression
import dev.sargunv.maplibrecompose.expressions.value.ColorValue
import dev.sargunv.maplibrecompose.expressions.value.FloatValue
import dev.sargunv.maplibrecompose.expressions.value.ImageValue

internal expect class BackgroundLayer(id: String) : Layer {
  fun setBackgroundColor(color: CompiledExpression<ColorValue>)

  fun setBackgroundPattern(pattern: CompiledExpression<ImageValue>)

  fun setBackgroundOpacity(opacity: CompiledExpression<FloatValue>)
}
