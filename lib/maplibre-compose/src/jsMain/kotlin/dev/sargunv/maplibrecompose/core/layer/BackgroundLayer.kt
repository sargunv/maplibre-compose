package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.expressions.ast.CompiledExpression
import dev.sargunv.maplibrecompose.expressions.value.ColorValue
import dev.sargunv.maplibrecompose.expressions.value.FloatValue
import dev.sargunv.maplibrecompose.expressions.value.ImageValue

internal actual class BackgroundLayer actual constructor(id: String) : Layer() {

  override val impl: Nothing = TODO()

  actual fun setBackgroundColor(color: CompiledExpression<ColorValue>) {
    TODO()
  }

  actual fun setBackgroundPattern(pattern: CompiledExpression<ImageValue>) {
    TODO()
  }

  actual fun setBackgroundOpacity(opacity: CompiledExpression<FloatValue>) {
    TODO()
  }
}
