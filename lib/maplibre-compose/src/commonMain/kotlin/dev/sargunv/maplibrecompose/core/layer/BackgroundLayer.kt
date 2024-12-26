package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.expression.ColorValue
import dev.sargunv.maplibrecompose.expression.CompiledExpression
import dev.sargunv.maplibrecompose.expression.FloatValue
import dev.sargunv.maplibrecompose.expression.ImageValue

internal expect class BackgroundLayer(id: String) : Layer {
  fun setBackgroundColor(color: CompiledExpression<ColorValue>)

  fun setBackgroundPattern(pattern: CompiledExpression<ImageValue>)

  fun setBackgroundOpacity(opacity: CompiledExpression<FloatValue>)
}
