package dev.sargunv.maplibrecompose.core.layer

import cocoapods.MapLibre.MLNHillshadeStyleLayer
import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.core.util.toNSExpression
import dev.sargunv.maplibrecompose.expressions.ast.CompiledExpression
import dev.sargunv.maplibrecompose.expressions.value.ColorValue
import dev.sargunv.maplibrecompose.expressions.value.FloatValue
import dev.sargunv.maplibrecompose.expressions.value.IlluminationAnchor

internal actual class HillshadeLayer actual constructor(id: String, actual val source: Source) :
  Layer() {

  override val impl = MLNHillshadeStyleLayer(id, source.impl)

  actual fun setHillshadeIlluminationDirection(direction: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setHillshadeIlluminationDirection")
    if (!isUnloaded) impl.hillshadeIlluminationDirection = direction.toNSExpression()
  }

  actual fun setHillshadeIlluminationAnchor(anchor: CompiledExpression<IlluminationAnchor>) {
    warnIfUnloaded("setHillshadeIlluminationAnchor")
    if (!isUnloaded) impl.hillshadeIlluminationAnchor = anchor.toNSExpression()
  }

  actual fun setHillshadeExaggeration(exaggeration: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setHillshadeExaggeration")
    if (!isUnloaded) impl.hillshadeExaggeration = exaggeration.toNSExpression()
  }

  actual fun setHillshadeShadowColor(shadowColor: CompiledExpression<ColorValue>) {
    warnIfUnloaded("setHillshadeShadowColor")
    if (!isUnloaded) impl.hillshadeShadowColor = shadowColor.toNSExpression()
  }

  actual fun setHillshadeHighlightColor(highlightColor: CompiledExpression<ColorValue>) {
    warnIfUnloaded("setHillshadeHighlightColor")
    if (!isUnloaded) impl.hillshadeHighlightColor = highlightColor.toNSExpression()
  }

  actual fun setHillshadeAccentColor(accentColor: CompiledExpression<ColorValue>) {
    warnIfUnloaded("setHillshadeAccentColor")
    if (!isUnloaded) impl.hillshadeAccentColor = accentColor.toNSExpression()
  }
}
