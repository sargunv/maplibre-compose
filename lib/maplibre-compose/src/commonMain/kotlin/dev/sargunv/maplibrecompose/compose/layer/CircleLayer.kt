package dev.sargunv.maplibrecompose.compose.layer

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import dev.sargunv.maplibrecompose.compose.FeaturesClickHandler
import dev.sargunv.maplibrecompose.compose.MaplibreComposable
import dev.sargunv.maplibrecompose.core.layer.CircleLayer
import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.expression.BooleanValue
import dev.sargunv.maplibrecompose.expression.CirclePitchAlignment
import dev.sargunv.maplibrecompose.expression.CirclePitchScale
import dev.sargunv.maplibrecompose.expression.ColorValue
import dev.sargunv.maplibrecompose.expression.DpOffsetValue
import dev.sargunv.maplibrecompose.expression.DpValue
import dev.sargunv.maplibrecompose.expression.Expression
import dev.sargunv.maplibrecompose.expression.ExpressionsDsl.const
import dev.sargunv.maplibrecompose.expression.ExpressionsDsl.nil
import dev.sargunv.maplibrecompose.expression.FloatValue
import dev.sargunv.maplibrecompose.expression.TranslateAnchor

/**
 * A circle layer draws points from the [sourceLayer] in the given [source] in the given style as a
 * circles. If nothing else is specified, these will be black dots of 5 dp radius.
 *
 * @param id Unique layer name.
 * @param source Vector data source for this layer.
 * @param sourceLayer Layer to use from the given vector tile [source].
 * @param minZoom The minimum zoom level for the layer. At zoom levels less than this, the layer
 *   will be hidden. A value in the range of `[0..24]`.
 * @param maxZoom The maximum zoom level for the layer. At zoom levels equal to or greater than
 *   this, the layer will be hidden. A value in the range of `[0..24]`.
 * @param filter An expression specifying conditions on source features. Only features that match
 *   the filter are displayed. Zoom expressions in filters are only evaluated at integer zoom
 *   levels. The [featureState][dev.sargunv.maplibrecompose.expression.ExpressionsDsl.Feature.state]
 *   expression is not supported in filter expressions.
 * @param visible Whether the layer should be displayed.
 * @param sortKey Sorts features within this layer in ascending order based on this value. Features
 *   with a higher sort key will appear above features with a lower sort key.
 * @param translate The geometry's offset relative to the [translateAnchor]. Negative numbers
 *   indicate left and up, respectively.
 * @param translateAnchor Frame of reference for offsetting geometry.
 *
 *   Ignored if [translate] is not set.
 *
 * @param opacity Circles opacity. A value in range `[0..1]`.
 * @param color Circles fill color.
 * @param blur Amount to blur the circle. A value of `1` blurs the circle such that only the
 *   centerpoint has full opacity.
 * @param radius Circles radius.
 * @param strokeOpacity Opacity of the circles' stroke.
 * @param strokeColor Circles' stroke color.
 * @param strokeWidth Thickness of the circles' stroke. Strokes are placed outside of the [radius].
 * @param pitchScale Scaling behavior of circles when the map is pitched.
 * @param pitchAlignment Orientation of circles when the map is pitched.
 * @param onClick Function to call when any feature in this layer has been clicked.
 * @param onLongClick Function to call when any feature in this layer has been long-clicked.
 */
@Composable
@MaplibreComposable
public fun CircleLayer(
  id: String,
  source: Source,
  sourceLayer: String = "",
  minZoom: Float = 0.0f,
  maxZoom: Float = 24.0f,
  filter: Expression<BooleanValue> = nil(),
  visible: Boolean = true,
  sortKey: Expression<FloatValue> = nil(),
  translate: Expression<DpOffsetValue> = const(DpOffset.Zero),
  translateAnchor: Expression<TranslateAnchor> = const(TranslateAnchor.Map),
  opacity: Expression<FloatValue> = const(1f),
  color: Expression<ColorValue> = const(Color.Black),
  blur: Expression<FloatValue> = const(0f),
  radius: Expression<DpValue> = const(5.dp),
  strokeOpacity: Expression<FloatValue> = const(1f),
  strokeColor: Expression<ColorValue> = const(Color.Black),
  strokeWidth: Expression<DpValue> = const(0.dp),
  pitchScale: Expression<CirclePitchScale> = const(CirclePitchScale.Map),
  pitchAlignment: Expression<CirclePitchAlignment> = const(CirclePitchAlignment.Viewport),
  onClick: FeaturesClickHandler? = null,
  onLongClick: FeaturesClickHandler? = null,
) {
  val compiler = rememberPropertyCompiler()

  val compiledFilter = compiler.invoke(filter)
  val compiledSortKey = compiler.invoke(sortKey)
  val compiledTranslate = compiler.invoke(translate)
  val compiledTranslateAnchor = compiler.invoke(translateAnchor)
  val compiledOpacity = compiler.invoke(opacity)
  val compiledColor = compiler.invoke(color)
  val compiledBlur = compiler.invoke(blur)
  val compiledRadius = compiler.invoke(radius)
  val compiledStrokeOpacity = compiler.invoke(strokeOpacity)
  val compiledStrokeColor = compiler.invoke(strokeColor)
  val compiledStrokeWidth = compiler.invoke(strokeWidth)
  val compiledPitchScale = compiler.invoke(pitchScale)
  val compiledPitchAlignment = compiler.invoke(pitchAlignment)

  LayerNode(
    factory = { CircleLayer(id = id, source = source) },
    update = {
      set(sourceLayer) { layer.sourceLayer = it }
      set(minZoom) { layer.minZoom = it }
      set(maxZoom) { layer.maxZoom = it }
      set(compiledFilter) { layer.setFilter(it) }
      set(visible) { layer.visible = it }
      set(compiledSortKey) { layer.setCircleSortKey(it) }
      set(compiledRadius) { layer.setCircleRadius(it) }
      set(compiledColor) { layer.setCircleColor(it) }
      set(compiledBlur) { layer.setCircleBlur(it) }
      set(compiledOpacity) { layer.setCircleOpacity(it) }
      set(compiledTranslate) { layer.setCircleTranslate(it) }
      set(compiledTranslateAnchor) { layer.setCircleTranslateAnchor(it) }
      set(compiledPitchScale) { layer.setCirclePitchScale(it) }
      set(compiledPitchAlignment) { layer.setCirclePitchAlignment(it) }
      set(compiledStrokeWidth) { layer.setCircleStrokeWidth(it) }
      set(compiledStrokeColor) { layer.setCircleStrokeColor(it) }
      set(compiledStrokeOpacity) { layer.setCircleStrokeOpacity(it) }
    },
    onClick = onClick,
    onLongClick = onLongClick,
  )
}
