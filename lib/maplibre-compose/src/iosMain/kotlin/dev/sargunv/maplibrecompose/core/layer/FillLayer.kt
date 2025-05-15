package dev.sargunv.maplibrecompose.core.layer

import cocoapods.MapLibre.MLNFillStyleLayer
import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.core.util.toNSExpression
import dev.sargunv.maplibrecompose.core.util.toNSPredicate
import dev.sargunv.maplibrecompose.expressions.ast.CompiledExpression
import dev.sargunv.maplibrecompose.expressions.ast.NullLiteral
import dev.sargunv.maplibrecompose.expressions.value.BooleanValue
import dev.sargunv.maplibrecompose.expressions.value.ColorValue
import dev.sargunv.maplibrecompose.expressions.value.DpOffsetValue
import dev.sargunv.maplibrecompose.expressions.value.FloatValue
import dev.sargunv.maplibrecompose.expressions.value.ImageValue
import dev.sargunv.maplibrecompose.expressions.value.TranslateAnchor

internal actual class FillLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {

  override val impl = MLNFillStyleLayer(id, source.impl)

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

  actual fun setFillSortKey(sortKey: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setFillSortKey")
    if (!isUnloaded) impl.fillSortKey = sortKey.toNSExpression()
  }

  actual fun setFillAntialias(antialias: CompiledExpression<BooleanValue>) {
    warnIfUnloaded("setFillAntialias")
    if (!isUnloaded) impl.fillAntialiased = antialias.toNSExpression()
  }

  actual fun setFillOpacity(opacity: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setFillOpacity")
    if (!isUnloaded) impl.fillOpacity = opacity.toNSExpression()
  }

  actual fun setFillColor(color: CompiledExpression<ColorValue>) {
    warnIfUnloaded("setFillColor")
    if (!isUnloaded) impl.fillColor = color.toNSExpression()
  }

  actual fun setFillOutlineColor(outlineColor: CompiledExpression<ColorValue>) {
    warnIfUnloaded("setFillOutlineColor")
    if (!isUnloaded) impl.fillOutlineColor = outlineColor.toNSExpression()
  }

  actual fun setFillTranslate(translate: CompiledExpression<DpOffsetValue>) {
    warnIfUnloaded("setFillTranslate")
    if (!isUnloaded) impl.fillTranslation = translate.toNSExpression()
  }

  actual fun setFillTranslateAnchor(translateAnchor: CompiledExpression<TranslateAnchor>) {
    warnIfUnloaded("setFillTranslateAnchor")
    if (!isUnloaded) impl.fillTranslationAnchor = translateAnchor.toNSExpression()
  }

  actual fun setFillPattern(pattern: CompiledExpression<ImageValue>) {
    warnIfUnloaded("setFillPattern")
    // TODO: figure out how to unset a pattern in iOS
    if (pattern != NullLiteral && !isUnloaded) impl.fillPattern = pattern.toNSExpression()
  }
}
