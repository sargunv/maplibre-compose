package dev.sargunv.maplibrecompose.core.layer

import cocoapods.MapLibre.MLNSymbolStyleLayer
import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.core.util.toNSExpression
import dev.sargunv.maplibrecompose.core.util.toNSPredicate
import dev.sargunv.maplibrecompose.expression.BooleanValue
import dev.sargunv.maplibrecompose.expression.ColorValue
import dev.sargunv.maplibrecompose.expression.CompiledExpression
import dev.sargunv.maplibrecompose.expression.DpOffsetValue
import dev.sargunv.maplibrecompose.expression.DpPaddingValue
import dev.sargunv.maplibrecompose.expression.DpValue
import dev.sargunv.maplibrecompose.expression.FloatOffsetValue
import dev.sargunv.maplibrecompose.expression.FloatValue
import dev.sargunv.maplibrecompose.expression.FormattedValue
import dev.sargunv.maplibrecompose.expression.IconPitchAlignment
import dev.sargunv.maplibrecompose.expression.IconRotationAlignment
import dev.sargunv.maplibrecompose.expression.IconTextFit
import dev.sargunv.maplibrecompose.expression.ImageValue
import dev.sargunv.maplibrecompose.expression.ListValue
import dev.sargunv.maplibrecompose.expression.NullLiteral
import dev.sargunv.maplibrecompose.expression.StringValue
import dev.sargunv.maplibrecompose.expression.SymbolAnchor
import dev.sargunv.maplibrecompose.expression.SymbolPlacement
import dev.sargunv.maplibrecompose.expression.SymbolZOrder
import dev.sargunv.maplibrecompose.expression.TextJustify
import dev.sargunv.maplibrecompose.expression.TextPitchAlignment
import dev.sargunv.maplibrecompose.expression.TextRotationAlignment
import dev.sargunv.maplibrecompose.expression.TextTransform
import dev.sargunv.maplibrecompose.expression.TextVariableAnchorOffsetValue
import dev.sargunv.maplibrecompose.expression.TextWritingMode
import dev.sargunv.maplibrecompose.expression.TranslateAnchor

internal actual class SymbolLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {

  override val impl = MLNSymbolStyleLayer(id, source.impl)

  actual override var sourceLayer: String
    get() = impl.sourceLayerIdentifier!!
    set(value) {
      impl.sourceLayerIdentifier = value
    }

  actual override fun setFilter(filter: CompiledExpression<BooleanValue>) {
    impl.predicate = filter.toNSPredicate()
  }

  actual fun setSymbolPlacement(placement: CompiledExpression<SymbolPlacement>) {
    impl.symbolPlacement = placement.toNSExpression()
  }

  actual fun setSymbolSpacing(spacing: CompiledExpression<DpValue>) {
    impl.symbolSpacing = spacing.toNSExpression()
  }

  actual fun setSymbolAvoidEdges(avoidEdges: CompiledExpression<BooleanValue>) {
    impl.symbolAvoidsEdges = avoidEdges.toNSExpression()
  }

  actual fun setSymbolSortKey(sortKey: CompiledExpression<FloatValue>) {
    impl.symbolSortKey = sortKey.toNSExpression()
  }

  actual fun setSymbolZOrder(zOrder: CompiledExpression<SymbolZOrder>) {
    impl.symbolZOrder = zOrder.toNSExpression()
  }

  actual fun setIconAllowOverlap(allowOverlap: CompiledExpression<BooleanValue>) {
    impl.iconAllowsOverlap = allowOverlap.toNSExpression()
  }

  actual fun setIconOverlap(overlap: CompiledExpression<StringValue>) {
    // TODO: warn not implemented by MapLibre-native iOS yet
    // impl.iconOverlap = overlap.toNSExpression()
  }

  actual fun setIconIgnorePlacement(ignorePlacement: CompiledExpression<BooleanValue>) {
    impl.iconIgnoresPlacement = ignorePlacement.toNSExpression()
  }

  actual fun setIconOptional(optional: CompiledExpression<BooleanValue>) {
    impl.iconOptional = optional.toNSExpression()
  }

  actual fun setIconRotationAlignment(
    rotationAlignment: CompiledExpression<IconRotationAlignment>
  ) {
    impl.iconRotationAlignment = rotationAlignment.toNSExpression()
  }

  actual fun setIconSize(size: CompiledExpression<FloatValue>) {
    impl.iconScale = size.toNSExpression()
  }

  actual fun setIconTextFit(textFit: CompiledExpression<IconTextFit>) {
    impl.iconTextFit = textFit.toNSExpression()
  }

  actual fun setIconTextFitPadding(textFitPadding: CompiledExpression<DpPaddingValue>) {
    impl.iconTextFitPadding = textFitPadding.toNSExpression()
  }

  actual fun setIconImage(image: CompiledExpression<ImageValue>) {
    // TODO figure out how to unset an image
    if (image != NullLiteral) impl.iconImageName = image.toNSExpression()
  }

  actual fun setIconRotate(rotate: CompiledExpression<FloatValue>) {
    impl.iconRotation = rotate.toNSExpression()
  }

  actual fun setIconPadding(padding: CompiledExpression<DpValue>) {
    impl.iconPadding = padding.toNSExpression()
  }

  actual fun setIconKeepUpright(keepUpright: CompiledExpression<BooleanValue>) {
    impl.keepsIconUpright = keepUpright.toNSExpression()
  }

  actual fun setIconOffset(offset: CompiledExpression<DpOffsetValue>) {
    impl.iconOffset = offset.toNSExpression()
  }

  actual fun setIconAnchor(anchor: CompiledExpression<SymbolAnchor>) {
    impl.iconAnchor = anchor.toNSExpression()
  }

  actual fun setIconPitchAlignment(pitchAlignment: CompiledExpression<IconPitchAlignment>) {
    impl.iconPitchAlignment = pitchAlignment.toNSExpression()
  }

  actual fun setIconOpacity(opacity: CompiledExpression<FloatValue>) {
    impl.iconOpacity = opacity.toNSExpression()
  }

  actual fun setIconColor(color: CompiledExpression<ColorValue>) {
    impl.iconColor = color.toNSExpression()
  }

  actual fun setIconHaloColor(haloColor: CompiledExpression<ColorValue>) {
    impl.iconHaloColor = haloColor.toNSExpression()
  }

  actual fun setIconHaloWidth(haloWidth: CompiledExpression<DpValue>) {
    impl.iconHaloWidth = haloWidth.toNSExpression()
  }

  actual fun setIconHaloBlur(haloBlur: CompiledExpression<DpValue>) {
    impl.iconHaloBlur = haloBlur.toNSExpression()
  }

  actual fun setIconTranslate(translate: CompiledExpression<DpOffsetValue>) {
    impl.iconTranslation = translate.toNSExpression()
  }

  actual fun setIconTranslateAnchor(translateAnchor: CompiledExpression<TranslateAnchor>) {
    impl.iconTranslationAnchor = translateAnchor.toNSExpression()
  }

  actual fun setTextPitchAlignment(pitchAlignment: CompiledExpression<TextPitchAlignment>) {
    impl.textPitchAlignment = pitchAlignment.toNSExpression()
  }

  actual fun setTextRotationAlignment(
    rotationAlignment: CompiledExpression<TextRotationAlignment>
  ) {
    impl.textRotationAlignment = rotationAlignment.toNSExpression()
  }

  actual fun setTextField(field: CompiledExpression<FormattedValue>) {
    impl.text = field.toNSExpression()
  }

  actual fun setTextFont(font: CompiledExpression<ListValue<StringValue>>) {
    impl.textFontNames = font.toNSExpression()
  }

  actual fun setTextSize(size: CompiledExpression<DpValue>) {
    impl.textFontSize = size.toNSExpression()
  }

  actual fun setTextMaxWidth(maxWidth: CompiledExpression<FloatValue>) {
    impl.maximumTextWidth = maxWidth.toNSExpression()
  }

  actual fun setTextLineHeight(lineHeight: CompiledExpression<FloatValue>) {
    impl.textLineHeight = lineHeight.toNSExpression()
  }

  actual fun setTextLetterSpacing(letterSpacing: CompiledExpression<FloatValue>) {
    impl.textLetterSpacing = letterSpacing.toNSExpression()
  }

  actual fun setTextJustify(justify: CompiledExpression<TextJustify>) {
    impl.textJustification = justify.toNSExpression()
  }

  actual fun setTextRadialOffset(radialOffset: CompiledExpression<FloatValue>) {
    impl.textRadialOffset = radialOffset.toNSExpression()
  }

  actual fun setTextVariableAnchor(variableAnchor: CompiledExpression<ListValue<SymbolAnchor>>) {
    impl.textVariableAnchor = variableAnchor.toNSExpression()
  }

  actual fun setTextVariableAnchorOffset(
    variableAnchorOffset: CompiledExpression<TextVariableAnchorOffsetValue>
  ) {
    impl.textVariableAnchorOffset = variableAnchorOffset.toNSExpression()
  }

  actual fun setTextAnchor(anchor: CompiledExpression<SymbolAnchor>) {
    impl.textAnchor = anchor.toNSExpression()
  }

  actual fun setTextMaxAngle(maxAngle: CompiledExpression<FloatValue>) {
    impl.maximumTextAngle = maxAngle.toNSExpression()
  }

  actual fun setTextWritingMode(writingMode: CompiledExpression<ListValue<TextWritingMode>>) {
    impl.textWritingModes = writingMode.toNSExpression()
  }

  actual fun setTextRotate(rotate: CompiledExpression<FloatValue>) {
    impl.textRotation = rotate.toNSExpression()
  }

  actual fun setTextPadding(padding: CompiledExpression<DpValue>) {
    impl.textPadding = padding.toNSExpression()
  }

  actual fun setTextKeepUpright(keepUpright: CompiledExpression<BooleanValue>) {
    impl.keepsTextUpright = keepUpright.toNSExpression()
  }

  actual fun setTextTransform(transform: CompiledExpression<TextTransform>) {
    impl.textTransform = transform.toNSExpression()
  }

  actual fun setTextOffset(offset: CompiledExpression<FloatOffsetValue>) {
    impl.textOffset = offset.toNSExpression()
  }

  actual fun setTextAllowOverlap(allowOverlap: CompiledExpression<BooleanValue>) {
    impl.textAllowsOverlap = allowOverlap.toNSExpression()
  }

  actual fun setTextOverlap(overlap: CompiledExpression<StringValue>) {
    // not implemented by MapLibre-native iOS yet
    // impl.textOverlap = overlap.toNSExpression()
  }

  actual fun setTextIgnorePlacement(ignorePlacement: CompiledExpression<BooleanValue>) {
    impl.textIgnoresPlacement = ignorePlacement.toNSExpression()
  }

  actual fun setTextOptional(optional: CompiledExpression<BooleanValue>) {
    impl.textOptional = optional.toNSExpression()
  }

  actual fun setTextOpacity(opacity: CompiledExpression<FloatValue>) {
    impl.textOpacity = opacity.toNSExpression()
  }

  actual fun setTextColor(color: CompiledExpression<ColorValue>) {
    impl.textColor = color.toNSExpression()
  }

  actual fun setTextHaloColor(haloColor: CompiledExpression<ColorValue>) {
    impl.textHaloColor = haloColor.toNSExpression()
  }

  actual fun setTextHaloWidth(haloWidth: CompiledExpression<DpValue>) {
    impl.textHaloWidth = haloWidth.toNSExpression()
  }

  actual fun setTextHaloBlur(haloBlur: CompiledExpression<DpValue>) {
    impl.textHaloBlur = haloBlur.toNSExpression()
  }

  actual fun setTextTranslate(translate: CompiledExpression<DpOffsetValue>) {
    impl.textTranslation = translate.toNSExpression()
  }

  actual fun setTextTranslateAnchor(translateAnchor: CompiledExpression<TranslateAnchor>) {
    impl.textTranslationAnchor = translateAnchor.toNSExpression()
  }
}
