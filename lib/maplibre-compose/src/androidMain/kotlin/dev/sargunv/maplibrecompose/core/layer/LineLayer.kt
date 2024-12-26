package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.core.util.toMLNExpression
import dev.sargunv.maplibrecompose.expression.ast.CompiledExpression
import dev.sargunv.maplibrecompose.expression.value.BooleanValue
import dev.sargunv.maplibrecompose.expression.value.ColorValue
import dev.sargunv.maplibrecompose.expression.value.DpOffsetValue
import dev.sargunv.maplibrecompose.expression.value.DpValue
import dev.sargunv.maplibrecompose.expression.value.FloatValue
import dev.sargunv.maplibrecompose.expression.value.ImageValue
import dev.sargunv.maplibrecompose.expression.value.LineCap
import dev.sargunv.maplibrecompose.expression.value.LineJoin
import dev.sargunv.maplibrecompose.expression.value.TranslateAnchor
import dev.sargunv.maplibrecompose.expression.value.VectorValue
import org.maplibre.android.style.expressions.Expression as MLNExpression
import org.maplibre.android.style.layers.LineLayer as MLNLineLayer
import org.maplibre.android.style.layers.PropertyFactory

internal actual class LineLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {

  override val impl = MLNLineLayer(id, source.id)

  actual override var sourceLayer: String by impl::sourceLayer

  actual override fun setFilter(filter: CompiledExpression<BooleanValue>) {
    impl.setFilter(filter.toMLNExpression() ?: MLNExpression.literal(true))
  }

  actual fun setLineCap(cap: CompiledExpression<LineCap>) {
    impl.setProperties(PropertyFactory.lineCap(cap.toMLNExpression()))
  }

  actual fun setLineJoin(join: CompiledExpression<LineJoin>) {
    impl.setProperties(PropertyFactory.lineJoin(join.toMLNExpression()))
  }

  actual fun setLineMiterLimit(miterLimit: CompiledExpression<FloatValue>) {
    impl.setProperties(PropertyFactory.lineMiterLimit(miterLimit.toMLNExpression()))
  }

  actual fun setLineRoundLimit(roundLimit: CompiledExpression<FloatValue>) {
    impl.setProperties(PropertyFactory.lineRoundLimit(roundLimit.toMLNExpression()))
  }

  actual fun setLineSortKey(sortKey: CompiledExpression<FloatValue>) {
    impl.setProperties(PropertyFactory.lineSortKey(sortKey.toMLNExpression()))
  }

  actual fun setLineOpacity(opacity: CompiledExpression<FloatValue>) {
    impl.setProperties(PropertyFactory.lineOpacity(opacity.toMLNExpression()))
  }

  actual fun setLineColor(color: CompiledExpression<ColorValue>) {
    impl.setProperties(PropertyFactory.lineColor(color.toMLNExpression()))
  }

  actual fun setLineTranslate(translate: CompiledExpression<DpOffsetValue>) {
    impl.setProperties(PropertyFactory.lineTranslate(translate.toMLNExpression()))
  }

  actual fun setLineTranslateAnchor(translateAnchor: CompiledExpression<TranslateAnchor>) {
    impl.setProperties(PropertyFactory.lineTranslateAnchor(translateAnchor.toMLNExpression()))
  }

  actual fun setLineWidth(width: CompiledExpression<DpValue>) {
    impl.setProperties(PropertyFactory.lineWidth(width.toMLNExpression()))
  }

  actual fun setLineGapWidth(gapWidth: CompiledExpression<DpValue>) {
    impl.setProperties(PropertyFactory.lineGapWidth(gapWidth.toMLNExpression()))
  }

  actual fun setLineOffset(offset: CompiledExpression<DpValue>) {
    impl.setProperties(PropertyFactory.lineOffset(offset.toMLNExpression()))
  }

  actual fun setLineBlur(blur: CompiledExpression<DpValue>) {
    impl.setProperties(PropertyFactory.lineBlur(blur.toMLNExpression()))
  }

  actual fun setLineDasharray(dasharray: CompiledExpression<VectorValue<Number>>) {
    impl.setProperties(PropertyFactory.lineDasharray(dasharray.toMLNExpression()))
  }

  actual fun setLinePattern(pattern: CompiledExpression<ImageValue>) {
    impl.setProperties(PropertyFactory.linePattern(pattern.toMLNExpression()))
  }

  actual fun setLineGradient(gradient: CompiledExpression<ColorValue>) {
    impl.setProperties(PropertyFactory.lineGradient(gradient.toMLNExpression()))
  }
}
