package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.expression.ColorValue
import dev.sargunv.maplibrecompose.core.expression.Expression
import dev.sargunv.maplibrecompose.core.expression.FloatValue
import dev.sargunv.maplibrecompose.core.expression.ImageValue
import dev.sargunv.maplibrecompose.core.expression.ResolvedValue

internal actual class BackgroundLayer actual constructor(id: String) : Layer() {

  override val impl: Nothing = TODO()

  actual fun setBackgroundColor(color: Expression<ColorValue>) {
    TODO()
  }

  actual fun setBackgroundPattern(pattern: Expression<ResolvedValue<ImageValue>>) {
    TODO()
  }

  actual fun setBackgroundOpacity(opacity: Expression<FloatValue>) {
    TODO()
  }
}
