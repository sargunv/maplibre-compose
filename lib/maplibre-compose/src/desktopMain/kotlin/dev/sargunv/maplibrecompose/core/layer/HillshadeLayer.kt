package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.expression.ColorValue
import dev.sargunv.maplibrecompose.core.expression.EnumValue
import dev.sargunv.maplibrecompose.core.expression.Expression
import dev.sargunv.maplibrecompose.core.expression.FloatValue
import dev.sargunv.maplibrecompose.core.expression.IlluminationAnchor
import dev.sargunv.maplibrecompose.core.source.Source

internal actual class HillshadeLayer actual constructor(id: String, actual val source: Source) :
  Layer() {
  override val impl = TODO()

  actual fun setHillshadeIlluminationDirection(direction: Expression<FloatValue>) {
    TODO()
  }

  actual fun setHillshadeIlluminationAnchor(anchor: Expression<EnumValue<IlluminationAnchor>>) {
    TODO()
  }

  actual fun setHillshadeExaggeration(exaggeration: Expression<FloatValue>) {
    TODO()
  }

  actual fun setHillshadeShadowColor(shadowColor: Expression<ColorValue>) {
    TODO()
  }

  actual fun setHillshadeHighlightColor(highlightColor: Expression<ColorValue>) {
    TODO()
  }

  actual fun setHillshadeAccentColor(accentColor: Expression<ColorValue>) {
    TODO()
  }
}
