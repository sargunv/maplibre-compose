package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.source.Source
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

internal expect class SymbolLayer(id: String, source: Source) : FeatureLayer {
  override var sourceLayer: String

  override fun setFilter(filter: CompiledExpression<BooleanValue>)

  fun setSymbolPlacement(placement: CompiledExpression<SymbolPlacement>)

  fun setSymbolSpacing(spacing: CompiledExpression<DpValue>)

  fun setSymbolAvoidEdges(avoidEdges: CompiledExpression<BooleanValue>)

  fun setSymbolSortKey(sortKey: CompiledExpression<FloatValue>)

  fun setSymbolZOrder(zOrder: CompiledExpression<SymbolZOrder>)

  fun setIconAllowOverlap(allowOverlap: CompiledExpression<BooleanValue>)

  fun setIconOverlap(overlap: CompiledExpression<StringValue>)

  fun setIconIgnorePlacement(ignorePlacement: CompiledExpression<BooleanValue>)

  fun setIconOptional(optional: CompiledExpression<BooleanValue>)

  fun setIconRotationAlignment(rotationAlignment: CompiledExpression<IconRotationAlignment>)

  fun setIconSize(size: CompiledExpression<FloatValue>)

  fun setIconTextFit(textFit: CompiledExpression<IconTextFit>)

  fun setIconTextFitPadding(textFitPadding: CompiledExpression<DpPaddingValue>)

  fun setIconImage(image: CompiledExpression<ImageValue>)

  fun setIconRotate(rotate: CompiledExpression<FloatValue>)

  fun setIconPadding(padding: CompiledExpression<DpValue>)

  fun setIconKeepUpright(keepUpright: CompiledExpression<BooleanValue>)

  fun setIconOffset(offset: CompiledExpression<DpOffsetValue>)

  fun setIconAnchor(anchor: CompiledExpression<SymbolAnchor>)

  fun setIconPitchAlignment(pitchAlignment: CompiledExpression<IconPitchAlignment>)

  fun setIconOpacity(opacity: CompiledExpression<FloatValue>)

  fun setIconColor(color: CompiledExpression<ColorValue>)

  fun setIconHaloColor(haloColor: CompiledExpression<ColorValue>)

  fun setIconHaloWidth(haloWidth: CompiledExpression<DpValue>)

  fun setIconHaloBlur(haloBlur: CompiledExpression<DpValue>)

  fun setIconTranslate(translate: CompiledExpression<DpOffsetValue>)

  fun setIconTranslateAnchor(translateAnchor: CompiledExpression<TranslateAnchor>)

  fun setTextPitchAlignment(pitchAlignment: CompiledExpression<TextPitchAlignment>)

  fun setTextRotationAlignment(rotationAlignment: CompiledExpression<TextRotationAlignment>)

  fun setTextField(field: CompiledExpression<FormattedValue>)

  fun setTextFont(font: CompiledExpression<ListValue<StringValue>>)

  fun setTextSize(size: CompiledExpression<DpValue>)

  fun setTextMaxWidth(maxWidth: CompiledExpression<FloatValue>)

  fun setTextLineHeight(lineHeight: CompiledExpression<FloatValue>)

  fun setTextLetterSpacing(letterSpacing: CompiledExpression<FloatValue>)

  fun setTextJustify(justify: CompiledExpression<TextJustify>)

  fun setTextRadialOffset(radialOffset: CompiledExpression<FloatValue>)

  fun setTextVariableAnchor(variableAnchor: CompiledExpression<ListValue<SymbolAnchor>>)

  fun setTextVariableAnchorOffset(
    variableAnchorOffset: CompiledExpression<TextVariableAnchorOffsetValue>
  )

  fun setTextAnchor(anchor: CompiledExpression<SymbolAnchor>)

  fun setTextMaxAngle(maxAngle: CompiledExpression<FloatValue>)

  fun setTextWritingMode(writingMode: CompiledExpression<ListValue<TextWritingMode>>)

  fun setTextRotate(rotate: CompiledExpression<FloatValue>)

  fun setTextPadding(padding: CompiledExpression<DpValue>)

  fun setTextKeepUpright(keepUpright: CompiledExpression<BooleanValue>)

  fun setTextTransform(transform: CompiledExpression<TextTransform>)

  fun setTextOffset(offset: CompiledExpression<FloatOffsetValue>)

  fun setTextAllowOverlap(allowOverlap: CompiledExpression<BooleanValue>)

  fun setTextOverlap(overlap: CompiledExpression<StringValue>)

  fun setTextIgnorePlacement(ignorePlacement: CompiledExpression<BooleanValue>)

  fun setTextOptional(optional: CompiledExpression<BooleanValue>)

  fun setTextOpacity(opacity: CompiledExpression<FloatValue>)

  fun setTextColor(color: CompiledExpression<ColorValue>)

  fun setTextHaloColor(haloColor: CompiledExpression<ColorValue>)

  fun setTextHaloWidth(haloWidth: CompiledExpression<DpValue>)

  fun setTextHaloBlur(haloBlur: CompiledExpression<DpValue>)

  fun setTextTranslate(translate: CompiledExpression<DpOffsetValue>)

  fun setTextTranslateAnchor(translateAnchor: CompiledExpression<TranslateAnchor>)
}
