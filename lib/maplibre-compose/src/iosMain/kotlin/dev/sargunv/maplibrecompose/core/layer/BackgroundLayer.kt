package dev.sargunv.maplibrecompose.core.layer

import cocoapods.MapLibre.MLNBackgroundStyleLayer
import dev.sargunv.maplibrecompose.core.util.toNSExpression
import dev.sargunv.maplibrecompose.expressions.ast.CompiledExpression
import dev.sargunv.maplibrecompose.expressions.ast.NullLiteral
import dev.sargunv.maplibrecompose.expressions.value.ColorValue
import dev.sargunv.maplibrecompose.expressions.value.FloatValue
import dev.sargunv.maplibrecompose.expressions.value.ImageValue

internal actual class BackgroundLayer actual constructor(id: String) : Layer() {
  override val impl = MLNBackgroundStyleLayer(id)

  actual fun setBackgroundColor(color: CompiledExpression<ColorValue>) {
    warnIfUnloaded("setBackgroundColor")
    if (!isUnloaded) impl.backgroundColor = color.toNSExpression()
  }

  actual fun setBackgroundPattern(pattern: CompiledExpression<ImageValue>) {
    warnIfUnloaded("setBackgroundPattern")
    // TODO: figure out how to unset a pattern in iOS
    if (pattern != NullLiteral && !isUnloaded) impl.backgroundPattern = pattern.toNSExpression()
  }

  actual fun setBackgroundOpacity(opacity: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setBackgroundOpacity")
    if (!isUnloaded) impl.backgroundOpacity = opacity.toNSExpression()
  }
}
