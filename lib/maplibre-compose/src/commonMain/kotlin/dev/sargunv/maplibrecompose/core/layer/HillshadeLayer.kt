package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.expression.ast.CompiledExpression
import dev.sargunv.maplibrecompose.expression.value.ColorValue
import dev.sargunv.maplibrecompose.expression.value.FloatValue
import dev.sargunv.maplibrecompose.expression.value.IlluminationAnchor

internal expect class HillshadeLayer(id: String, source: Source) : Layer {
  val source: Source

  fun setHillshadeIlluminationDirection(direction: CompiledExpression<FloatValue>)

  fun setHillshadeIlluminationAnchor(anchor: CompiledExpression<IlluminationAnchor>)

  fun setHillshadeExaggeration(exaggeration: CompiledExpression<FloatValue>)

  fun setHillshadeShadowColor(shadowColor: CompiledExpression<ColorValue>)

  fun setHillshadeHighlightColor(highlightColor: CompiledExpression<ColorValue>)

  fun setHillshadeAccentColor(accentColor: CompiledExpression<ColorValue>)
}
