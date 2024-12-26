package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.source.Source
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

internal actual class LineLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {

  override val impl = TODO()

  actual override var sourceLayer: String = TODO()

  actual override fun setFilter(filter: CompiledExpression<BooleanValue>) {
    TODO()
  }

  actual fun setLineCap(cap: CompiledExpression<LineCap>) {
    TODO()
  }

  actual fun setLineJoin(join: CompiledExpression<LineJoin>) {
    TODO()
  }

  actual fun setLineMiterLimit(miterLimit: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setLineRoundLimit(roundLimit: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setLineSortKey(sortKey: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setLineOpacity(opacity: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setLineColor(color: CompiledExpression<ColorValue>) {
    TODO()
  }

  actual fun setLineTranslate(translate: CompiledExpression<DpOffsetValue>) {
    TODO()
  }

  actual fun setLineTranslateAnchor(translateAnchor: CompiledExpression<TranslateAnchor>) {
    TODO()
  }

  actual fun setLineWidth(width: CompiledExpression<DpValue>) {
    TODO()
  }

  actual fun setLineGapWidth(gapWidth: CompiledExpression<DpValue>) {
    TODO()
  }

  actual fun setLineOffset(offset: CompiledExpression<DpValue>) {
    TODO()
  }

  actual fun setLineBlur(blur: CompiledExpression<DpValue>) {
    TODO()
  }

  actual fun setLineDasharray(dasharray: CompiledExpression<VectorValue<Number>>) {
    TODO()
  }

  actual fun setLinePattern(pattern: CompiledExpression<ImageValue>) {
    TODO()
  }

  actual fun setLineGradient(gradient: CompiledExpression<ColorValue>) {
    TODO()
  }
}
