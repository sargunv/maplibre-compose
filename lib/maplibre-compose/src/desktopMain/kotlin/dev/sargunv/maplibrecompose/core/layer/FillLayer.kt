package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.expression.BooleanValue
import dev.sargunv.maplibrecompose.core.expression.ColorValue
import dev.sargunv.maplibrecompose.core.expression.DpOffsetValue
import dev.sargunv.maplibrecompose.core.expression.EnumValue
import dev.sargunv.maplibrecompose.core.expression.Expression
import dev.sargunv.maplibrecompose.core.expression.FloatValue
import dev.sargunv.maplibrecompose.core.expression.ImageValue
import dev.sargunv.maplibrecompose.core.expression.ResolvedValue
import dev.sargunv.maplibrecompose.core.expression.TranslateAnchor
import dev.sargunv.maplibrecompose.core.source.Source

internal actual class FillLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {

  override val impl = TODO()

  actual override var sourceLayer: String = TODO()

  actual override fun setFilter(filter: Expression<BooleanValue>) {
    TODO()
  }

  actual fun setFillSortKey(sortKey: Expression<FloatValue>) {
    TODO()
  }

  actual fun setFillAntialias(antialias: Expression<BooleanValue>) {
    TODO()
  }

  actual fun setFillOpacity(opacity: Expression<FloatValue>) {
    TODO()
  }

  actual fun setFillColor(color: Expression<ColorValue>) {
    TODO()
  }

  actual fun setFillOutlineColor(outlineColor: Expression<ColorValue>) {
    TODO()
  }

  actual fun setFillTranslate(translate: Expression<DpOffsetValue>) {
    TODO()
  }

  actual fun setFillTranslateAnchor(translateAnchor: Expression<EnumValue<TranslateAnchor>>) {
    TODO()
  }

  actual fun setFillPattern(pattern: Expression<ResolvedValue<ImageValue>>) {
    TODO()
  }
}
