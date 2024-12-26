package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.expression.ast.CompiledExpression
import dev.sargunv.maplibrecompose.expression.value.BooleanValue
import dev.sargunv.maplibrecompose.expression.value.ColorValue
import dev.sargunv.maplibrecompose.expression.value.DpOffsetValue
import dev.sargunv.maplibrecompose.expression.value.FloatValue
import dev.sargunv.maplibrecompose.expression.value.ImageValue
import dev.sargunv.maplibrecompose.expression.value.TranslateAnchor

internal actual class FillExtrusionLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {
  override val impl = TODO()

  actual override var sourceLayer: String = TODO()

  actual override fun setFilter(filter: CompiledExpression<BooleanValue>) {
    TODO()
  }

  actual fun setFillExtrusionOpacity(opacity: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setFillExtrusionColor(color: CompiledExpression<ColorValue>) {
    TODO()
  }

  actual fun setFillExtrusionTranslate(translate: CompiledExpression<DpOffsetValue>) {
    TODO()
  }

  actual fun setFillExtrusionTranslateAnchor(anchor: CompiledExpression<TranslateAnchor>) {
    TODO()
  }

  actual fun setFillExtrusionPattern(pattern: CompiledExpression<ImageValue>) {
    TODO()
  }

  actual fun setFillExtrusionHeight(height: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setFillExtrusionBase(base: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setFillExtrusionVerticalGradient(verticalGradient: CompiledExpression<BooleanValue>) {
    TODO()
  }
}
