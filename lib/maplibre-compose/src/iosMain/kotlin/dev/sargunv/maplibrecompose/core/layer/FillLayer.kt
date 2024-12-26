package dev.sargunv.maplibrecompose.core.layer

import cocoapods.MapLibre.MLNFillStyleLayer
import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.core.util.toNSExpression
import dev.sargunv.maplibrecompose.core.util.toNSPredicate
import dev.sargunv.maplibrecompose.expression.BooleanValue
import dev.sargunv.maplibrecompose.expression.ColorValue
import dev.sargunv.maplibrecompose.expression.CompiledExpression
import dev.sargunv.maplibrecompose.expression.DpOffsetValue
import dev.sargunv.maplibrecompose.expression.FloatValue
import dev.sargunv.maplibrecompose.expression.ImageValue
import dev.sargunv.maplibrecompose.expression.NullLiteral
import dev.sargunv.maplibrecompose.expression.TranslateAnchor

internal actual class FillLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {

  override val impl = MLNFillStyleLayer(id, source.impl)

  actual override var sourceLayer: String
    get() = impl.sourceLayerIdentifier!!
    set(value) {
      impl.sourceLayerIdentifier = value
    }

  actual override fun setFilter(filter: CompiledExpression<BooleanValue>) {
    impl.predicate = filter.toNSPredicate()
  }

  actual fun setFillSortKey(sortKey: CompiledExpression<FloatValue>) {
    impl.fillSortKey = sortKey.toNSExpression()
  }

  actual fun setFillAntialias(antialias: CompiledExpression<BooleanValue>) {
    impl.fillAntialiased = antialias.toNSExpression()
  }

  actual fun setFillOpacity(opacity: CompiledExpression<FloatValue>) {
    impl.fillOpacity = opacity.toNSExpression()
  }

  actual fun setFillColor(color: CompiledExpression<ColorValue>) {
    impl.fillColor = color.toNSExpression()
  }

  actual fun setFillOutlineColor(outlineColor: CompiledExpression<ColorValue>) {
    impl.fillOutlineColor = outlineColor.toNSExpression()
  }

  actual fun setFillTranslate(translate: CompiledExpression<DpOffsetValue>) {
    impl.fillTranslation = translate.toNSExpression()
  }

  actual fun setFillTranslateAnchor(translateAnchor: CompiledExpression<TranslateAnchor>) {
    impl.fillTranslationAnchor = translateAnchor.toNSExpression()
  }

  actual fun setFillPattern(pattern: CompiledExpression<ImageValue>) {
    // TODO: figure out how to unset a pattern in iOS
    if (pattern != NullLiteral) {
      impl.fillPattern = pattern.toNSExpression()
    }
  }
}
