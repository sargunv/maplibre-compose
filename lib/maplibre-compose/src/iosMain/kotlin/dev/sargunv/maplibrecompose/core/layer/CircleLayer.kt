package dev.sargunv.maplibrecompose.core.layer

import cocoapods.MapLibre.MLNCircleStyleLayer
import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.core.util.toNSExpression
import dev.sargunv.maplibrecompose.core.util.toNSPredicate
import dev.sargunv.maplibrecompose.expressions.ast.CompiledExpression
import dev.sargunv.maplibrecompose.expressions.value.BooleanValue
import dev.sargunv.maplibrecompose.expressions.value.CirclePitchAlignment
import dev.sargunv.maplibrecompose.expressions.value.CirclePitchScale
import dev.sargunv.maplibrecompose.expressions.value.ColorValue
import dev.sargunv.maplibrecompose.expressions.value.DpOffsetValue
import dev.sargunv.maplibrecompose.expressions.value.DpValue
import dev.sargunv.maplibrecompose.expressions.value.FloatValue
import dev.sargunv.maplibrecompose.expressions.value.TranslateAnchor

internal actual class CircleLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {

  override val impl = MLNCircleStyleLayer(id, source.impl)

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

  actual fun setCircleSortKey(sortKey: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setCircleSortKey")
    if (!isUnloaded) impl.circleSortKey = sortKey.toNSExpression()
  }

  actual fun setCircleRadius(radius: CompiledExpression<DpValue>) {
    warnIfUnloaded("setCircleRadius")
    if (!isUnloaded) impl.circleRadius = radius.toNSExpression()
  }

  actual fun setCircleColor(color: CompiledExpression<ColorValue>) {
    warnIfUnloaded("setCircleColor")
    if (!isUnloaded) impl.circleColor = color.toNSExpression()
  }

  actual fun setCircleBlur(blur: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setCircleBlur")
    if (!isUnloaded) impl.circleBlur = blur.toNSExpression()
  }

  actual fun setCircleOpacity(opacity: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setCircleOpacity")
    if (!isUnloaded) impl.circleOpacity = opacity.toNSExpression()
  }

  actual fun setCircleTranslate(translate: CompiledExpression<DpOffsetValue>) {
    warnIfUnloaded("setCircleTranslate")
    if (!isUnloaded) impl.circleTranslation = translate.toNSExpression()
  }

  actual fun setCircleTranslateAnchor(translateAnchor: CompiledExpression<TranslateAnchor>) {
    warnIfUnloaded("setCircleTranslateAnchor")
    if (!isUnloaded) impl.circleTranslationAnchor = translateAnchor.toNSExpression()
  }

  actual fun setCirclePitchScale(pitchScale: CompiledExpression<CirclePitchScale>) {
    warnIfUnloaded("setCirclePitchScale")
    if (!isUnloaded) impl.circleScaleAlignment = pitchScale.toNSExpression()
  }

  actual fun setCirclePitchAlignment(pitchAlignment: CompiledExpression<CirclePitchAlignment>) {
    warnIfUnloaded("setCirclePitchAlignment")
    if (!isUnloaded) impl.circlePitchAlignment = pitchAlignment.toNSExpression()
  }

  actual fun setCircleStrokeWidth(strokeWidth: CompiledExpression<DpValue>) {
    warnIfUnloaded("setCircleStrokeWidth")
    if (!isUnloaded) impl.circleStrokeWidth = strokeWidth.toNSExpression()
  }

  actual fun setCircleStrokeColor(strokeColor: CompiledExpression<ColorValue>) {
    warnIfUnloaded("setCircleStrokeColor")
    if (!isUnloaded) impl.circleStrokeColor = strokeColor.toNSExpression()
  }

  actual fun setCircleStrokeOpacity(strokeOpacity: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setCircleStrokeOpacity")
    if (!isUnloaded) impl.circleStrokeOpacity = strokeOpacity.toNSExpression()
  }
}
