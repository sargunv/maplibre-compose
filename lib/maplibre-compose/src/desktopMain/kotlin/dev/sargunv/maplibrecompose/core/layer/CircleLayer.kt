package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.expression.ast.CompiledExpression
import dev.sargunv.maplibrecompose.expression.value.BooleanValue
import dev.sargunv.maplibrecompose.expression.value.CirclePitchAlignment
import dev.sargunv.maplibrecompose.expression.value.CirclePitchScale
import dev.sargunv.maplibrecompose.expression.value.ColorValue
import dev.sargunv.maplibrecompose.expression.value.DpOffsetValue
import dev.sargunv.maplibrecompose.expression.value.DpValue
import dev.sargunv.maplibrecompose.expression.value.FloatValue
import dev.sargunv.maplibrecompose.expression.value.TranslateAnchor

internal actual class CircleLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {
  override val impl = TODO()

  actual override var sourceLayer: String = TODO()

  actual override fun setFilter(filter: CompiledExpression<BooleanValue>) {
    TODO()
  }

  actual fun setCircleSortKey(sortKey: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setCircleRadius(radius: CompiledExpression<DpValue>) {
    TODO()
  }

  actual fun setCircleColor(color: CompiledExpression<ColorValue>) {
    TODO()
  }

  actual fun setCircleBlur(blur: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setCircleOpacity(opacity: CompiledExpression<FloatValue>) {
    TODO()
  }

  actual fun setCircleTranslate(translate: CompiledExpression<DpOffsetValue>) {
    TODO()
  }

  actual fun setCircleTranslateAnchor(translateAnchor: CompiledExpression<TranslateAnchor>) {
    TODO()
  }

  actual fun setCirclePitchScale(pitchScale: CompiledExpression<CirclePitchScale>) {
    TODO()
  }

  actual fun setCirclePitchAlignment(pitchAlignment: CompiledExpression<CirclePitchAlignment>) {
    TODO()
  }

  actual fun setCircleStrokeWidth(strokeWidth: CompiledExpression<DpValue>) {
    TODO()
  }

  actual fun setCircleStrokeColor(strokeColor: CompiledExpression<ColorValue>) {
    TODO()
  }

  actual fun setCircleStrokeOpacity(strokeOpacity: CompiledExpression<FloatValue>) {
    TODO()
  }
}
