package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.expression.ast.CompiledExpression
import dev.sargunv.maplibrecompose.expression.value.BooleanValue
import dev.sargunv.maplibrecompose.expression.value.ColorValue
import dev.sargunv.maplibrecompose.expression.value.DpOffsetValue
import dev.sargunv.maplibrecompose.expression.value.FloatValue
import dev.sargunv.maplibrecompose.expression.value.ImageValue
import dev.sargunv.maplibrecompose.expression.value.TranslateAnchor

internal expect class FillLayer(id: String, source: Source) : FeatureLayer {
  override var sourceLayer: String

  override fun setFilter(filter: CompiledExpression<BooleanValue>)

  fun setFillSortKey(sortKey: CompiledExpression<FloatValue>)

  fun setFillAntialias(antialias: CompiledExpression<BooleanValue>)

  fun setFillOpacity(opacity: CompiledExpression<FloatValue>)

  fun setFillColor(color: CompiledExpression<ColorValue>)

  fun setFillOutlineColor(outlineColor: CompiledExpression<ColorValue>)

  fun setFillTranslate(translate: CompiledExpression<DpOffsetValue>)

  fun setFillTranslateAnchor(translateAnchor: CompiledExpression<TranslateAnchor>)

  fun setFillPattern(pattern: CompiledExpression<ImageValue>)
}
