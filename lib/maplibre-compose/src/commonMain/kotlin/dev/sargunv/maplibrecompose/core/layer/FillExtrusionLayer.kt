package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.expression.ast.CompiledExpression
import dev.sargunv.maplibrecompose.expression.value.BooleanValue
import dev.sargunv.maplibrecompose.expression.value.ColorValue
import dev.sargunv.maplibrecompose.expression.value.DpOffsetValue
import dev.sargunv.maplibrecompose.expression.value.FloatValue
import dev.sargunv.maplibrecompose.expression.value.ImageValue
import dev.sargunv.maplibrecompose.expression.value.TranslateAnchor

internal expect class FillExtrusionLayer(id: String, source: Source) : FeatureLayer {
  override var sourceLayer: String

  override fun setFilter(filter: CompiledExpression<BooleanValue>)

  fun setFillExtrusionOpacity(opacity: CompiledExpression<FloatValue>)

  fun setFillExtrusionColor(color: CompiledExpression<ColorValue>)

  fun setFillExtrusionTranslate(translate: CompiledExpression<DpOffsetValue>)

  fun setFillExtrusionTranslateAnchor(anchor: CompiledExpression<TranslateAnchor>)

  fun setFillExtrusionPattern(pattern: CompiledExpression<ImageValue>)

  fun setFillExtrusionHeight(height: CompiledExpression<FloatValue>)

  fun setFillExtrusionBase(base: CompiledExpression<FloatValue>)

  fun setFillExtrusionVerticalGradient(verticalGradient: CompiledExpression<BooleanValue>)
}
