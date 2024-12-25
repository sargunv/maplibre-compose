package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.expression.BooleanValue
import dev.sargunv.maplibrecompose.core.expression.CirclePitchAlignment
import dev.sargunv.maplibrecompose.core.expression.CirclePitchScale
import dev.sargunv.maplibrecompose.core.expression.ColorValue
import dev.sargunv.maplibrecompose.core.expression.DpOffsetValue
import dev.sargunv.maplibrecompose.core.expression.DpValue
import dev.sargunv.maplibrecompose.core.expression.EnumValue
import dev.sargunv.maplibrecompose.core.expression.Expression
import dev.sargunv.maplibrecompose.core.expression.FloatValue
import dev.sargunv.maplibrecompose.core.expression.TranslateAnchor
import dev.sargunv.maplibrecompose.core.source.Source

internal actual class CircleLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {
  override val impl = TODO()

  actual override var sourceLayer: String = TODO()

  actual override fun setFilter(filter: Expression<BooleanValue>) {
    TODO()
  }

  actual fun setCircleSortKey(sortKey: Expression<FloatValue>) {
    TODO()
  }

  actual fun setCircleRadius(radius: Expression<DpValue>) {
    TODO()
  }

  actual fun setCircleColor(color: Expression<ColorValue>) {
    TODO()
  }

  actual fun setCircleBlur(blur: Expression<FloatValue>) {
    TODO()
  }

  actual fun setCircleOpacity(opacity: Expression<FloatValue>) {
    TODO()
  }

  actual fun setCircleTranslate(translate: Expression<DpOffsetValue>) {
    TODO()
  }

  actual fun setCircleTranslateAnchor(translateAnchor: Expression<EnumValue<TranslateAnchor>>) {
    TODO()
  }

  actual fun setCirclePitchScale(pitchScale: Expression<EnumValue<CirclePitchScale>>) {
    TODO()
  }

  actual fun setCirclePitchAlignment(pitchAlignment: Expression<EnumValue<CirclePitchAlignment>>) {
    TODO()
  }

  actual fun setCircleStrokeWidth(strokeWidth: Expression<DpValue>) {
    TODO()
  }

  actual fun setCircleStrokeColor(strokeColor: Expression<ColorValue>) {
    TODO()
  }

  actual fun setCircleStrokeOpacity(strokeOpacity: Expression<FloatValue>) {
    TODO()
  }
}
