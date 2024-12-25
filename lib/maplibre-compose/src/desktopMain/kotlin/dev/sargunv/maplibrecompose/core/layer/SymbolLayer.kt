package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.expression.BooleanValue
import dev.sargunv.maplibrecompose.core.expression.ColorValue
import dev.sargunv.maplibrecompose.core.expression.DpOffsetValue
import dev.sargunv.maplibrecompose.core.expression.DpPaddingValue
import dev.sargunv.maplibrecompose.core.expression.DpValue
import dev.sargunv.maplibrecompose.core.expression.EnumValue
import dev.sargunv.maplibrecompose.core.expression.Expression
import dev.sargunv.maplibrecompose.core.expression.FloatOffsetValue
import dev.sargunv.maplibrecompose.core.expression.FloatValue
import dev.sargunv.maplibrecompose.core.expression.FormattedValue
import dev.sargunv.maplibrecompose.core.expression.IconPitchAlignment
import dev.sargunv.maplibrecompose.core.expression.IconRotationAlignment
import dev.sargunv.maplibrecompose.core.expression.IconTextFit
import dev.sargunv.maplibrecompose.core.expression.ImageValue
import dev.sargunv.maplibrecompose.core.expression.ListValue
import dev.sargunv.maplibrecompose.core.expression.ResolvedValue
import dev.sargunv.maplibrecompose.core.expression.StringValue
import dev.sargunv.maplibrecompose.core.expression.SymbolAnchor
import dev.sargunv.maplibrecompose.core.expression.SymbolPlacement
import dev.sargunv.maplibrecompose.core.expression.SymbolZOrder
import dev.sargunv.maplibrecompose.core.expression.TextJustify
import dev.sargunv.maplibrecompose.core.expression.TextPitchAlignment
import dev.sargunv.maplibrecompose.core.expression.TextRotationAlignment
import dev.sargunv.maplibrecompose.core.expression.TextTransform
import dev.sargunv.maplibrecompose.core.expression.TextVariableAnchorOffsetValue
import dev.sargunv.maplibrecompose.core.expression.TextWritingMode
import dev.sargunv.maplibrecompose.core.expression.TranslateAnchor
import dev.sargunv.maplibrecompose.core.source.Source

internal actual class SymbolLayer actual constructor(id: String, source: Source) :
  FeatureLayer(source) {
  override val impl = TODO()

  actual override var sourceLayer: String = TODO()

  actual override fun setFilter(filter: Expression<BooleanValue>) {
    TODO()
  }

  actual fun setSymbolPlacement(placement: Expression<EnumValue<SymbolPlacement>>) {
    TODO()
  }

  actual fun setSymbolSpacing(spacing: Expression<DpValue>) {
    TODO()
  }

  actual fun setSymbolAvoidEdges(avoidEdges: Expression<BooleanValue>) {
    TODO()
  }

  actual fun setSymbolSortKey(sortKey: Expression<FloatValue>) {
    TODO()
  }

  actual fun setSymbolZOrder(zOrder: Expression<EnumValue<SymbolZOrder>>) {
    TODO()
  }

  actual fun setIconAllowOverlap(allowOverlap: Expression<BooleanValue>) {
    TODO()
  }

  actual fun setIconOverlap(overlap: Expression<StringValue>) {
    TODO()
  }

  actual fun setIconIgnorePlacement(ignorePlacement: Expression<BooleanValue>) {
    TODO()
  }

  actual fun setIconOptional(optional: Expression<BooleanValue>) {
    TODO()
  }

  actual fun setIconRotationAlignment(
    rotationAlignment: Expression<EnumValue<IconRotationAlignment>>
  ) {
    TODO()
  }

  actual fun setIconSize(size: Expression<FloatValue>) {
    TODO()
  }

  actual fun setIconTextFit(textFit: Expression<EnumValue<IconTextFit>>) {
    TODO()
  }

  actual fun setIconTextFitPadding(textFitPadding: Expression<DpPaddingValue>) {
    TODO()
  }

  actual fun setIconImage(image: Expression<ResolvedValue<ImageValue>>) {
    TODO()
  }

  actual fun setIconRotate(rotate: Expression<FloatValue>) {
    TODO()
  }

  actual fun setIconPadding(padding: Expression<DpValue>) {
    TODO()
  }

  actual fun setIconKeepUpright(keepUpright: Expression<BooleanValue>) {
    TODO()
  }

  actual fun setIconOffset(offset: Expression<DpOffsetValue>) {
    TODO()
  }

  actual fun setIconAnchor(anchor: Expression<EnumValue<SymbolAnchor>>) {
    TODO()
  }

  actual fun setIconPitchAlignment(pitchAlignment: Expression<EnumValue<IconPitchAlignment>>) {
    TODO()
  }

  actual fun setIconOpacity(opacity: Expression<FloatValue>) {
    TODO()
  }

  actual fun setIconColor(color: Expression<ColorValue>) {
    TODO()
  }

  actual fun setIconHaloColor(haloColor: Expression<ColorValue>) {
    TODO()
  }

  actual fun setIconHaloWidth(haloWidth: Expression<DpValue>) {
    TODO()
  }

  actual fun setIconHaloBlur(haloBlur: Expression<DpValue>) {
    TODO()
  }

  actual fun setIconTranslate(translate: Expression<DpOffsetValue>) {
    TODO()
  }

  actual fun setIconTranslateAnchor(translateAnchor: Expression<EnumValue<TranslateAnchor>>) {
    TODO()
  }

  actual fun setTextPitchAlignment(pitchAlignment: Expression<EnumValue<TextPitchAlignment>>) {
    TODO()
  }

  actual fun setTextRotationAlignment(
    rotationAlignment: Expression<EnumValue<TextRotationAlignment>>
  ) {
    TODO()
  }

  actual fun setTextField(field: Expression<ResolvedValue<FormattedValue>>) {
    TODO()
  }

  actual fun setTextFont(font: Expression<ListValue<StringValue>>) {
    TODO()
  }

  actual fun setTextSize(size: Expression<DpValue>) {
    TODO()
  }

  actual fun setTextMaxWidth(maxWidth: Expression<FloatValue>) {
    TODO()
  }

  actual fun setTextLineHeight(lineHeight: Expression<FloatValue>) {
    TODO()
  }

  actual fun setTextLetterSpacing(letterSpacing: Expression<FloatValue>) {
    TODO()
  }

  actual fun setTextJustify(justify: Expression<EnumValue<TextJustify>>) {
    TODO()
  }

  actual fun setTextRadialOffset(radialOffset: Expression<FloatValue>) {
    TODO()
  }

  actual fun setTextVariableAnchor(variableAnchor: Expression<ListValue<EnumValue<SymbolAnchor>>>) {
    TODO()
  }

  actual fun setTextVariableAnchorOffset(
    variableAnchorOffset: Expression<TextVariableAnchorOffsetValue>
  ) {
    TODO()
  }

  actual fun setTextAnchor(anchor: Expression<EnumValue<SymbolAnchor>>) {
    TODO()
  }

  actual fun setTextMaxAngle(maxAngle: Expression<FloatValue>) {
    TODO()
  }

  actual fun setTextWritingMode(writingMode: Expression<ListValue<EnumValue<TextWritingMode>>>) {
    TODO()
  }

  actual fun setTextRotate(rotate: Expression<FloatValue>) {
    TODO()
  }

  actual fun setTextPadding(padding: Expression<DpValue>) {
    TODO()
  }

  actual fun setTextKeepUpright(keepUpright: Expression<BooleanValue>) {
    TODO()
  }

  actual fun setTextTransform(transform: Expression<EnumValue<TextTransform>>) {
    TODO()
  }

  actual fun setTextOffset(offset: Expression<FloatOffsetValue>) {
    TODO()
  }

  actual fun setTextAllowOverlap(allowOverlap: Expression<BooleanValue>) {
    TODO()
  }

  actual fun setTextOverlap(overlap: Expression<StringValue>) {
    TODO()
  }

  actual fun setTextIgnorePlacement(ignorePlacement: Expression<BooleanValue>) {
    TODO()
  }

  actual fun setTextOptional(optional: Expression<BooleanValue>) {
    TODO()
  }

  actual fun setTextOpacity(opacity: Expression<FloatValue>) {
    TODO()
  }

  actual fun setTextColor(color: Expression<ColorValue>) {
    TODO()
  }

  actual fun setTextHaloColor(haloColor: Expression<ColorValue>) {
    TODO()
  }

  actual fun setTextHaloWidth(haloWidth: Expression<DpValue>) {
    TODO()
  }

  actual fun setTextHaloBlur(haloBlur: Expression<DpValue>) {
    TODO()
  }

  actual fun setTextTranslate(translate: Expression<DpOffsetValue>) {
    TODO()
  }

  actual fun setTextTranslateAnchor(translateAnchor: Expression<EnumValue<TranslateAnchor>>) {
    TODO()
  }
}
