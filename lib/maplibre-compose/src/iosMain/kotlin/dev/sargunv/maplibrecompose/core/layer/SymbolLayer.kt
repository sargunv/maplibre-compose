package dev.sargunv.maplibrecompose.core.layer

import cocoapods.MapLibre.MLNSymbolStyleLayer
import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.core.util.toNSExpression
import dev.sargunv.maplibrecompose.core.util.toNSPredicate
import dev.sargunv.maplibrecompose.expressions.ast.CompiledExpression
import dev.sargunv.maplibrecompose.expressions.ast.NullLiteral
import dev.sargunv.maplibrecompose.expressions.value.BooleanValue
import dev.sargunv.maplibrecompose.expressions.value.ColorValue
import dev.sargunv.maplibrecompose.expressions.value.DpOffsetValue
import dev.sargunv.maplibrecompose.expressions.value.DpPaddingValue
import dev.sargunv.maplibrecompose.expressions.value.DpValue
import dev.sargunv.maplibrecompose.expressions.value.FloatOffsetValue
import dev.sargunv.maplibrecompose.expressions.value.FloatValue
import dev.sargunv.maplibrecompose.expressions.value.FormattedValue
import dev.sargunv.maplibrecompose.expressions.value.IconPitchAlignment
import dev.sargunv.maplibrecompose.expressions.value.IconRotationAlignment
import dev.sargunv.maplibrecompose.expressions.value.IconTextFit
import dev.sargunv.maplibrecompose.expressions.value.ImageValue
import dev.sargunv.maplibrecompose.expressions.value.ListValue
import dev.sargunv.maplibrecompose.expressions.value.StringValue
import dev.sargunv.maplibrecompose.expressions.value.SymbolAnchor
import dev.sargunv.maplibrecompose.expressions.value.SymbolOverlap
import dev.sargunv.maplibrecompose.expressions.value.SymbolPlacement
import dev.sargunv.maplibrecompose.expressions.value.SymbolZOrder
import dev.sargunv.maplibrecompose.expressions.value.TextJustify
import dev.sargunv.maplibrecompose.expressions.value.TextPitchAlignment
import dev.sargunv.maplibrecompose.expressions.value.TextRotationAlignment
import dev.sargunv.maplibrecompose.expressions.value.TextTransform
import dev.sargunv.maplibrecompose.expressions.value.TextVariableAnchorOffsetValue
import dev.sargunv.maplibrecompose.expressions.value.TextWritingMode
import dev.sargunv.maplibrecompose.expressions.value.TranslateAnchor

internal actual class SymbolLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {

  override val impl = MLNSymbolStyleLayer(id, source.impl)

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

  actual fun setSymbolPlacement(placement: CompiledExpression<SymbolPlacement>) {
    warnIfUnloaded("setSymbolPlacement")
    if (!isUnloaded) impl.symbolPlacement = placement.toNSExpression()
  }

  actual fun setSymbolSpacing(spacing: CompiledExpression<DpValue>) {
    warnIfUnloaded("setSymbolSpacing")
    if (!isUnloaded) impl.symbolSpacing = spacing.toNSExpression()
  }

  actual fun setSymbolAvoidEdges(avoidEdges: CompiledExpression<BooleanValue>) {
    warnIfUnloaded("setSymbolAvoidEdges")
    if (!isUnloaded) impl.symbolAvoidsEdges = avoidEdges.toNSExpression()
  }

  actual fun setSymbolSortKey(sortKey: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setSymbolSortKey")
    if (!isUnloaded) impl.symbolSortKey = sortKey.toNSExpression()
  }

  actual fun setSymbolZOrder(zOrder: CompiledExpression<SymbolZOrder>) {
    warnIfUnloaded("setSymbolZOrder")
    if (!isUnloaded) impl.symbolZOrder = zOrder.toNSExpression()
  }

  actual fun setIconAllowOverlap(allowOverlap: CompiledExpression<BooleanValue>) {
    warnIfUnloaded("setIconAllowOverlap")
    if (!isUnloaded) impl.iconAllowsOverlap = allowOverlap.toNSExpression()
  }

  actual fun setIconOverlap(overlap: CompiledExpression<StringValue>) {
    // warnIfUnloaded("setIconOverlap")
    // TODO: warn not implemented by MapLibre-native iOS yet
    // if (!isUnloaded) impl.iconOverlap = overlap.toNSExpression()
  }

  actual fun setIconIgnorePlacement(ignorePlacement: CompiledExpression<BooleanValue>) {
    warnIfUnloaded("setIconIgnorePlacement")
    if (!isUnloaded) impl.iconIgnoresPlacement = ignorePlacement.toNSExpression()
  }

  actual fun setIconOptional(optional: CompiledExpression<BooleanValue>) {
    warnIfUnloaded("setIconOptional")
    if (!isUnloaded) impl.iconOptional = optional.toNSExpression()
  }

  actual fun setIconRotationAlignment(
    rotationAlignment: CompiledExpression<IconRotationAlignment>
  ) {
    warnIfUnloaded("setIconRotationAlignment")
    if (!isUnloaded) impl.iconRotationAlignment = rotationAlignment.toNSExpression()
  }

  actual fun setIconSize(size: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setIconSize")
    if (!isUnloaded) impl.iconScale = size.toNSExpression()
  }

  actual fun setIconTextFit(textFit: CompiledExpression<IconTextFit>) {
    warnIfUnloaded("setIconTextFit")
    if (!isUnloaded) impl.iconTextFit = textFit.toNSExpression()
  }

  actual fun setIconTextFitPadding(textFitPadding: CompiledExpression<DpPaddingValue>) {
    warnIfUnloaded("setIconTextFitPadding")
    if (!isUnloaded) impl.iconTextFitPadding = textFitPadding.toNSExpression()
  }

  actual fun setIconImage(image: CompiledExpression<ImageValue>) {
    warnIfUnloaded("setIconImage")
    // TODO figure out how to unset an image
    if (image != NullLiteral && !isUnloaded) impl.iconImageName = image.toNSExpression()
  }

  actual fun setIconRotate(rotate: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setIconRotate")
    if (!isUnloaded) impl.iconRotation = rotate.toNSExpression()
  }

  actual fun setIconPadding(padding: CompiledExpression<DpValue>) {
    warnIfUnloaded("setIconPadding")
    if (!isUnloaded) impl.iconPadding = padding.toNSExpression()
  }

  actual fun setIconKeepUpright(keepUpright: CompiledExpression<BooleanValue>) {
    warnIfUnloaded("setIconKeepUpright")
    if (!isUnloaded) impl.keepsIconUpright = keepUpright.toNSExpression()
  }

  actual fun setIconOffset(offset: CompiledExpression<DpOffsetValue>) {
    warnIfUnloaded("setIconOffset")
    if (!isUnloaded) impl.iconOffset = offset.toNSExpression()
  }

  actual fun setIconAnchor(anchor: CompiledExpression<SymbolAnchor>) {
    warnIfUnloaded("setIconAnchor")
    if (!isUnloaded) impl.iconAnchor = anchor.toNSExpression()
  }

  actual fun setIconPitchAlignment(pitchAlignment: CompiledExpression<IconPitchAlignment>) {
    warnIfUnloaded("setIconPitchAlignment")
    if (!isUnloaded) impl.iconPitchAlignment = pitchAlignment.toNSExpression()
  }

  actual fun setIconOpacity(opacity: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setIconOpacity")
    if (!isUnloaded) impl.iconOpacity = opacity.toNSExpression()
  }

  actual fun setIconColor(color: CompiledExpression<ColorValue>) {
    warnIfUnloaded("setIconColor")
    if (!isUnloaded) impl.iconColor = color.toNSExpression()
  }

  actual fun setIconHaloColor(haloColor: CompiledExpression<ColorValue>) {
    warnIfUnloaded("setIconHaloColor")
    if (!isUnloaded) impl.iconHaloColor = haloColor.toNSExpression()
  }

  actual fun setIconHaloWidth(haloWidth: CompiledExpression<DpValue>) {
    warnIfUnloaded("setIconHaloWidth")
    if (!isUnloaded) impl.iconHaloWidth = haloWidth.toNSExpression()
  }

  actual fun setIconHaloBlur(haloBlur: CompiledExpression<DpValue>) {
    warnIfUnloaded("setIconHaloBlur")
    if (!isUnloaded) impl.iconHaloBlur = haloBlur.toNSExpression()
  }

  actual fun setIconTranslate(translate: CompiledExpression<DpOffsetValue>) {
    warnIfUnloaded("setIconTranslate")
    if (!isUnloaded) impl.iconTranslation = translate.toNSExpression()
  }

  actual fun setIconTranslateAnchor(translateAnchor: CompiledExpression<TranslateAnchor>) {
    warnIfUnloaded("setIconTranslateAnchor")
    if (!isUnloaded) impl.iconTranslationAnchor = translateAnchor.toNSExpression()
  }

  actual fun setTextPitchAlignment(pitchAlignment: CompiledExpression<TextPitchAlignment>) {
    warnIfUnloaded("setTextPitchAlignment")
    if (!isUnloaded) impl.textPitchAlignment = pitchAlignment.toNSExpression()
  }

  actual fun setTextRotationAlignment(
    rotationAlignment: CompiledExpression<TextRotationAlignment>
  ) {
    warnIfUnloaded("setTextRotationAlignment")
    if (!isUnloaded) impl.textRotationAlignment = rotationAlignment.toNSExpression()
  }

  actual fun setTextField(field: CompiledExpression<FormattedValue>) {
    warnIfUnloaded("setTextField")
    if (!isUnloaded) impl.text = field.toNSExpression()
  }

  actual fun setTextFont(font: CompiledExpression<ListValue<StringValue>>) {
    warnIfUnloaded("setTextFont")
    if (!isUnloaded) impl.textFontNames = font.toNSExpression()
  }

  actual fun setTextSize(size: CompiledExpression<DpValue>) {
    warnIfUnloaded("setTextSize")
    if (!isUnloaded) impl.textFontSize = size.toNSExpression()
  }

  actual fun setTextMaxWidth(maxWidth: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setTextMaxWidth")
    if (!isUnloaded) impl.maximumTextWidth = maxWidth.toNSExpression()
  }

  actual fun setTextLineHeight(lineHeight: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setTextLineHeight")
    if (!isUnloaded) impl.textLineHeight = lineHeight.toNSExpression()
  }

  actual fun setTextLetterSpacing(letterSpacing: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setTextLetterSpacing")
    if (!isUnloaded) impl.textLetterSpacing = letterSpacing.toNSExpression()
  }

  actual fun setTextJustify(justify: CompiledExpression<TextJustify>) {
    warnIfUnloaded("setTextJustify")
    if (!isUnloaded) impl.textJustification = justify.toNSExpression()
  }

  actual fun setTextRadialOffset(radialOffset: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setTextRadialOffset")
    if (!isUnloaded) impl.textRadialOffset = radialOffset.toNSExpression()
  }

  actual fun setTextVariableAnchor(variableAnchor: CompiledExpression<ListValue<SymbolAnchor>>) {
    warnIfUnloaded("setTextVariableAnchor")
    if (!isUnloaded) impl.textVariableAnchor = variableAnchor.toNSExpression()
  }

  actual fun setTextVariableAnchorOffset(
    variableAnchorOffset: CompiledExpression<TextVariableAnchorOffsetValue>
  ) {
    warnIfUnloaded("setTextVariableAnchorOffset")
    if (!isUnloaded) impl.textVariableAnchorOffset = variableAnchorOffset.toNSExpression()
  }

  actual fun setTextAnchor(anchor: CompiledExpression<SymbolAnchor>) {
    warnIfUnloaded("setTextAnchor")
    if (!isUnloaded) impl.textAnchor = anchor.toNSExpression()
  }

  actual fun setTextMaxAngle(maxAngle: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setTextMaxAngle")
    if (!isUnloaded) impl.maximumTextAngle = maxAngle.toNSExpression()
  }

  actual fun setTextWritingMode(writingMode: CompiledExpression<ListValue<TextWritingMode>>) {
    warnIfUnloaded("setTextWritingMode")
    if (!isUnloaded) impl.textWritingModes = writingMode.toNSExpression()
  }

  actual fun setTextRotate(rotate: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setTextRotate")
    if (!isUnloaded) impl.textRotation = rotate.toNSExpression()
  }

  actual fun setTextPadding(padding: CompiledExpression<DpValue>) {
    warnIfUnloaded("setTextPadding")
    if (!isUnloaded) impl.textPadding = padding.toNSExpression()
  }

  actual fun setTextKeepUpright(keepUpright: CompiledExpression<BooleanValue>) {
    warnIfUnloaded("setTextKeepUpright")
    if (!isUnloaded) impl.keepsTextUpright = keepUpright.toNSExpression()
  }

  actual fun setTextTransform(transform: CompiledExpression<TextTransform>) {
    warnIfUnloaded("setTextTransform")
    if (!isUnloaded) impl.textTransform = transform.toNSExpression()
  }

  actual fun setTextOffset(offset: CompiledExpression<FloatOffsetValue>) {
    warnIfUnloaded("setTextOffset")
    if (!isUnloaded) impl.textOffset = offset.toNSExpression()
  }

  actual fun setTextAllowOverlap(allowOverlap: CompiledExpression<BooleanValue>) {
    warnIfUnloaded("setTextAllowOverlap")
    if (!isUnloaded) impl.textAllowsOverlap = allowOverlap.toNSExpression()
  }

  actual fun setTextOverlap(overlap: CompiledExpression<SymbolOverlap>) {
    // warnIfUnloaded("setTextOverlap")
    // not implemented by MapLibre-native iOS yet
    // if (!isUnloaded) impl.textOverlap = overlap.toNSExpression()
  }

  actual fun setTextIgnorePlacement(ignorePlacement: CompiledExpression<BooleanValue>) {
    warnIfUnloaded("setTextIgnorePlacement")
    if (!isUnloaded) impl.textIgnoresPlacement = ignorePlacement.toNSExpression()
  }

  actual fun setTextOptional(optional: CompiledExpression<BooleanValue>) {
    warnIfUnloaded("setTextOptional")
    if (!isUnloaded) impl.textOptional = optional.toNSExpression()
  }

  actual fun setTextOpacity(opacity: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setTextOpacity")
    if (!isUnloaded) impl.textOpacity = opacity.toNSExpression()
  }

  actual fun setTextColor(color: CompiledExpression<ColorValue>) {
    warnIfUnloaded("setTextColor")
    if (!isUnloaded) impl.textColor = color.toNSExpression()
  }

  actual fun setTextHaloColor(haloColor: CompiledExpression<ColorValue>) {
    warnIfUnloaded("setTextHaloColor")
    if (!isUnloaded) impl.textHaloColor = haloColor.toNSExpression()
  }

  actual fun setTextHaloWidth(haloWidth: CompiledExpression<DpValue>) {
    warnIfUnloaded("setTextHaloWidth")
    if (!isUnloaded) impl.textHaloWidth = haloWidth.toNSExpression()
  }

  actual fun setTextHaloBlur(haloBlur: CompiledExpression<DpValue>) {
    warnIfUnloaded("setTextHaloBlur")
    if (!isUnloaded) impl.textHaloBlur = haloBlur.toNSExpression()
  }

  actual fun setTextTranslate(translate: CompiledExpression<DpOffsetValue>) {
    warnIfUnloaded("setTextTranslate")
    if (!isUnloaded) impl.textTranslation = translate.toNSExpression()
  }

  actual fun setTextTranslateAnchor(translateAnchor: CompiledExpression<TranslateAnchor>) {
    warnIfUnloaded("setTextTranslateAnchor")
    if (!isUnloaded) impl.textTranslationAnchor = translateAnchor.toNSExpression()
  }
}
