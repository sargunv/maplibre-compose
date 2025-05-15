package dev.sargunv.maplibrecompose.core.layer

import cocoapods.MapLibre.MLNLineStyleLayer
import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.core.util.toNSExpression
import dev.sargunv.maplibrecompose.core.util.toNSPredicate
import dev.sargunv.maplibrecompose.expressions.ast.CompiledExpression
import dev.sargunv.maplibrecompose.expressions.ast.NullLiteral
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

internal actual class LineLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {

  override val impl = MLNLineStyleLayer(id, source.impl)

  actual override var sourceLayer: String
    get() = impl.sourceLayerIdentifier!!
    set(value) {
      warnIfUnloaded("sourceLayerIdentifier")
      if (!isUnloaded) impl.sourceLayerIdentifier = value
    }

  actual override fun setFilter(filter: CompiledExpression<BooleanValue>) {
    warnIfUnloaded("setFilter")
    if (!isUnloaded) impl.predicate = filter.toNSPredicate()
  }

  actual fun setLineCap(cap: CompiledExpression<LineCap>) {
    warnIfUnloaded("setLineCap")
    if (!isUnloaded) impl.lineCap = cap.toNSExpression()
  }

  actual fun setLineJoin(join: CompiledExpression<LineJoin>) {
    warnIfUnloaded("setLineJoin")
    if (!isUnloaded) impl.lineJoin = join.toNSExpression()
  }

  actual fun setLineMiterLimit(miterLimit: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setLineMiterLimit")
    if (!isUnloaded) impl.lineMiterLimit = miterLimit.toNSExpression()
  }

  actual fun setLineRoundLimit(roundLimit: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setLineRoundLimit")
    if (!isUnloaded) impl.lineRoundLimit = roundLimit.toNSExpression()
  }

  actual fun setLineSortKey(sortKey: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setLineSortKey")
    if (!isUnloaded) impl.lineSortKey = sortKey.toNSExpression()
  }

  actual fun setLineOpacity(opacity: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setLineOpacity")
    if (!isUnloaded) impl.lineOpacity = opacity.toNSExpression()
  }

  actual fun setLineColor(color: CompiledExpression<ColorValue>) {
    warnIfUnloaded("setLineColor")
    if (!isUnloaded) impl.lineColor = color.toNSExpression()
  }

  actual fun setLineTranslate(translate: CompiledExpression<DpOffsetValue>) {
    warnIfUnloaded("setLineTranslate")
    if (!isUnloaded) impl.lineTranslation = translate.toNSExpression()
  }

  actual fun setLineTranslateAnchor(translateAnchor: CompiledExpression<TranslateAnchor>) {
    warnIfUnloaded("setLineTranslateAnchor")
    if (!isUnloaded) impl.lineTranslationAnchor = translateAnchor.toNSExpression()
  }

  actual fun setLineWidth(width: CompiledExpression<DpValue>) {
    warnIfUnloaded("setLineWidth")
    if (!isUnloaded) impl.lineWidth = width.toNSExpression()
  }

  actual fun setLineGapWidth(gapWidth: CompiledExpression<DpValue>) {
    warnIfUnloaded("setLineGapWidth")
    if (!isUnloaded) impl.lineGapWidth = gapWidth.toNSExpression()
  }

  actual fun setLineOffset(offset: CompiledExpression<DpValue>) {
    warnIfUnloaded("setLineOffset")
    if (!isUnloaded) impl.lineOffset = offset.toNSExpression()
  }

  actual fun setLineBlur(blur: CompiledExpression<DpValue>) {
    warnIfUnloaded("setLineBlur")
    if (!isUnloaded) impl.lineBlur = blur.toNSExpression()
  }

  actual fun setLineDasharray(dasharray: CompiledExpression<VectorValue<Number>>) {
    warnIfUnloaded("setLineDasharray")
    if (!isUnloaded) impl.lineDashPattern = dasharray.toNSExpression()
  }

  actual fun setLinePattern(pattern: CompiledExpression<ImageValue>) {
    warnIfUnloaded("setLinePattern")
    // TODO: figure out how to unset a pattern in iOS
    if (pattern != NullLiteral && !isUnloaded) impl.linePattern = pattern.toNSExpression()
  }

  actual fun setLineGradient(gradient: CompiledExpression<ColorValue>) {
    warnIfUnloaded("setLineGradient")
    if (!isUnloaded) impl.lineGradient = gradient.toNSExpression()
  }
}
