package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.core.util.toMLNExpression
import dev.sargunv.maplibrecompose.expression.ColorValue
import dev.sargunv.maplibrecompose.expression.CompiledExpression
import dev.sargunv.maplibrecompose.expression.FloatValue
import dev.sargunv.maplibrecompose.expression.IlluminationAnchor
import org.maplibre.android.style.layers.HillshadeLayer as MLNHillshadeLayer
import org.maplibre.android.style.layers.PropertyFactory

internal actual class HillshadeLayer actual constructor(id: String, actual val source: Source) :
  Layer() {
  override val impl = MLNHillshadeLayer(id, source.id)

  actual fun setHillshadeIlluminationDirection(direction: CompiledExpression<FloatValue>) {
    impl.setProperties(PropertyFactory.hillshadeIlluminationDirection(direction.toMLNExpression()))
  }

  actual fun setHillshadeIlluminationAnchor(anchor: CompiledExpression<IlluminationAnchor>) {
    impl.setProperties(PropertyFactory.hillshadeIlluminationAnchor(anchor.toMLNExpression()))
  }

  actual fun setHillshadeExaggeration(exaggeration: CompiledExpression<FloatValue>) {
    impl.setProperties(PropertyFactory.hillshadeExaggeration(exaggeration.toMLNExpression()))
  }

  actual fun setHillshadeShadowColor(shadowColor: CompiledExpression<ColorValue>) {
    impl.setProperties(PropertyFactory.hillshadeShadowColor(shadowColor.toMLNExpression()))
  }

  actual fun setHillshadeHighlightColor(highlightColor: CompiledExpression<ColorValue>) {
    impl.setProperties(PropertyFactory.hillshadeHighlightColor(highlightColor.toMLNExpression()))
  }

  actual fun setHillshadeAccentColor(accentColor: CompiledExpression<ColorValue>) {
    impl.setProperties(PropertyFactory.hillshadeAccentColor(accentColor.toMLNExpression()))
  }
}
