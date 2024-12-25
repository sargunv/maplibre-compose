package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.expression.BooleanValue
import dev.sargunv.maplibrecompose.core.expression.ColorValue
import dev.sargunv.maplibrecompose.core.expression.DpOffsetValue
import dev.sargunv.maplibrecompose.core.expression.DpValue
import dev.sargunv.maplibrecompose.core.expression.EnumValue
import dev.sargunv.maplibrecompose.core.expression.Expression
import dev.sargunv.maplibrecompose.core.expression.FloatValue
import dev.sargunv.maplibrecompose.core.expression.ImageValue
import dev.sargunv.maplibrecompose.core.expression.LineCap
import dev.sargunv.maplibrecompose.core.expression.LineJoin
import dev.sargunv.maplibrecompose.core.expression.ResolvedValue
import dev.sargunv.maplibrecompose.core.expression.TranslateAnchor
import dev.sargunv.maplibrecompose.core.expression.VectorValue
import dev.sargunv.maplibrecompose.core.source.Source

internal actual class LineLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {

  override val impl = TODO()

  actual override var sourceLayer: String = TODO()

  actual override fun setFilter(filter: Expression<BooleanValue>) {
    TODO()
  }

  actual fun setLineCap(cap: Expression<EnumValue<LineCap>>) {
    TODO()
  }

  actual fun setLineJoin(join: Expression<EnumValue<LineJoin>>) {
    TODO()
  }

  actual fun setLineMiterLimit(miterLimit: Expression<FloatValue>) {
    TODO()
  }

  actual fun setLineRoundLimit(roundLimit: Expression<FloatValue>) {
    TODO()
  }

  actual fun setLineSortKey(sortKey: Expression<FloatValue>) {
    TODO()
  }

  actual fun setLineOpacity(opacity: Expression<FloatValue>) {
    TODO()
  }

  actual fun setLineColor(color: Expression<ColorValue>) {
    TODO()
  }

  actual fun setLineTranslate(translate: Expression<DpOffsetValue>) {
    TODO()
  }

  actual fun setLineTranslateAnchor(translateAnchor: Expression<EnumValue<TranslateAnchor>>) {
    TODO()
  }

  actual fun setLineWidth(width: Expression<DpValue>) {
    TODO()
  }

  actual fun setLineGapWidth(gapWidth: Expression<DpValue>) {
    TODO()
  }

  actual fun setLineOffset(offset: Expression<DpValue>) {
    TODO()
  }

  actual fun setLineBlur(blur: Expression<DpValue>) {
    TODO()
  }

  actual fun setLineDasharray(dasharray: Expression<VectorValue<Number>>) {
    TODO()
  }

  actual fun setLinePattern(pattern: Expression<ResolvedValue<ImageValue>>) {
    TODO()
  }

  actual fun setLineGradient(gradient: Expression<ColorValue>) {
    TODO()
  }
}
