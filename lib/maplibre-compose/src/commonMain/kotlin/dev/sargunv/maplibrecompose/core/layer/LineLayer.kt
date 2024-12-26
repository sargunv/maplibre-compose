package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.expressions.ast.CompiledExpression
import dev.sargunv.maplibrecompose.expressions.value.BooleanValue
import dev.sargunv.maplibrecompose.expressions.value.ColorValue
import dev.sargunv.maplibrecompose.expressions.value.DpOffsetValue
import dev.sargunv.maplibrecompose.expressions.value.DpValue
import dev.sargunv.maplibrecompose.expressions.value.FloatValue
import dev.sargunv.maplibrecompose.expressions.value.ImageValue
import dev.sargunv.maplibrecompose.expressions.value.LineCap
import dev.sargunv.maplibrecompose.expressions.value.LineJoin
import dev.sargunv.maplibrecompose.expressions.value.TranslateAnchor
import dev.sargunv.maplibrecompose.expressions.value.VectorValue

internal expect class LineLayer(id: String, source: Source) : FeatureLayer {
  override var sourceLayer: String

  override fun setFilter(filter: CompiledExpression<BooleanValue>)

  fun setLineCap(cap: CompiledExpression<LineCap>)

  fun setLineJoin(join: CompiledExpression<LineJoin>)

  fun setLineMiterLimit(miterLimit: CompiledExpression<FloatValue>)

  fun setLineRoundLimit(roundLimit: CompiledExpression<FloatValue>)

  fun setLineSortKey(sortKey: CompiledExpression<FloatValue>)

  fun setLineOpacity(opacity: CompiledExpression<FloatValue>)

  fun setLineColor(color: CompiledExpression<ColorValue>)

  fun setLineTranslate(translate: CompiledExpression<DpOffsetValue>)

  fun setLineTranslateAnchor(translateAnchor: CompiledExpression<TranslateAnchor>)

  fun setLineWidth(width: CompiledExpression<DpValue>)

  fun setLineGapWidth(gapWidth: CompiledExpression<DpValue>)

  fun setLineOffset(offset: CompiledExpression<DpValue>)

  fun setLineBlur(blur: CompiledExpression<DpValue>)

  fun setLineDasharray(dasharray: CompiledExpression<VectorValue<Number>>)

  fun setLinePattern(pattern: CompiledExpression<ImageValue>)

  fun setLineGradient(gradient: CompiledExpression<ColorValue>)
}
