package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.core.util.toMLNExpression
import dev.sargunv.maplibrecompose.expression.ast.CompiledExpression
import dev.sargunv.maplibrecompose.expression.value.BooleanValue
import dev.sargunv.maplibrecompose.expression.value.ColorValue
import dev.sargunv.maplibrecompose.expression.value.DpOffsetValue
import dev.sargunv.maplibrecompose.expression.value.FloatValue
import dev.sargunv.maplibrecompose.expression.value.ImageValue
import dev.sargunv.maplibrecompose.expression.value.TranslateAnchor
import org.maplibre.android.style.expressions.Expression as MLNExpression
import org.maplibre.android.style.layers.FillLayer as MLNFillLayer
import org.maplibre.android.style.layers.PropertyFactory

internal actual class FillLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {
  override val impl = MLNFillLayer(id, source.id)

  actual override var sourceLayer: String by impl::sourceLayer

  actual override fun setFilter(filter: CompiledExpression<BooleanValue>) {
    impl.setFilter(filter.toMLNExpression() ?: MLNExpression.literal(true))
  }

  actual fun setFillSortKey(sortKey: CompiledExpression<FloatValue>) {
    impl.setProperties(PropertyFactory.fillSortKey(sortKey.toMLNExpression()))
  }

  actual fun setFillAntialias(antialias: CompiledExpression<BooleanValue>) {
    impl.setProperties(PropertyFactory.fillAntialias(antialias.toMLNExpression()))
  }

  actual fun setFillOpacity(opacity: CompiledExpression<FloatValue>) {
    impl.setProperties(PropertyFactory.fillOpacity(opacity.toMLNExpression()))
  }

  actual fun setFillColor(color: CompiledExpression<ColorValue>) {
    impl.setProperties(PropertyFactory.fillColor(color.toMLNExpression()))
  }

  actual fun setFillOutlineColor(outlineColor: CompiledExpression<ColorValue>) {
    impl.setProperties(PropertyFactory.fillOutlineColor(outlineColor.toMLNExpression()))
  }

  actual fun setFillTranslate(translate: CompiledExpression<DpOffsetValue>) {
    impl.setProperties(PropertyFactory.fillTranslate(translate.toMLNExpression()))
  }

  actual fun setFillTranslateAnchor(translateAnchor: CompiledExpression<TranslateAnchor>) {
    impl.setProperties(PropertyFactory.fillTranslateAnchor(translateAnchor.toMLNExpression()))
  }

  actual fun setFillPattern(pattern: CompiledExpression<ImageValue>) {
    impl.setProperties(PropertyFactory.fillPattern(pattern.toMLNExpression()))
  }
}
