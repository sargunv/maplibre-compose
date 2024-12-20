package dev.sargunv.maplibrecompose.compose.layer

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import dev.sargunv.maplibrecompose.compose.FeaturesClickHandler
import dev.sargunv.maplibrecompose.compose.MaplibreComposable
import dev.sargunv.maplibrecompose.core.expression.BooleanValue
import dev.sargunv.maplibrecompose.core.expression.ColorValue
import dev.sargunv.maplibrecompose.core.expression.Defaults
import dev.sargunv.maplibrecompose.core.expression.DpValue
import dev.sargunv.maplibrecompose.core.expression.Expression
import dev.sargunv.maplibrecompose.core.expression.ExpressionsDsl.const
import dev.sargunv.maplibrecompose.core.expression.ExpressionsDsl.heatmapDensity
import dev.sargunv.maplibrecompose.core.expression.ExpressionsDsl.nil
import dev.sargunv.maplibrecompose.core.expression.FloatValue
import dev.sargunv.maplibrecompose.core.layer.HeatmapLayer
import dev.sargunv.maplibrecompose.core.source.Source

/**
 * A heatmap layer draws points from the [sourceLayer] in the given [source] as a heatmap.
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
 *   levels. The
 *   [featureState][dev.sargunv.maplibrecompose.core.expression.ExpressionsDsl.featureState]
 *   expression is not supported in filter expressions.
 * @param visible Whether the layer should be displayed.
 * @param color Defines the color of each pixel based on its density value in a heatmap. Should be
 *   an expression that uses [heatmapDensity] as input.
 * @param opacity The global opacity at which the heatmap layer will be drawn.
 * @param radius Radius of influence of one heatmap point. Increasing the value makes the heatmap
 *   smoother, but less detailed.
 * @param weight A measure of how much an individual point contributes to the heatmap. A value of 10
 *   would be equivalent to having 10 points of weight 1 in the same spot. Especially useful when
 *   combined with clustering. A value in the range of `[0..infinity)`.
 * @param intensity Similar to [weight] but controls the intensity of the heatmap globally.
 *   Primarily used for adjusting the heatmap based on zoom level.
 * @param onClick Function to call when any feature in this layer has been clicked.
 * @param onLongClick Function to call when any feature in this layer has been long-clicked.
 */
@Composable
@MaplibreComposable
public fun HeatmapLayer(
  id: String,
  source: Source,
  sourceLayer: String = "",
  minZoom: Float = 0.0f,
  maxZoom: Float = 24.0f,
  filter: Expression<BooleanValue> = nil(),
  visible: Boolean = true,
  color: Expression<ColorValue> = Defaults.HeatmapColors,
  opacity: Expression<FloatValue> = const(1f),
  radius: Expression<DpValue> = const(30.dp),
  weight: Expression<FloatValue> = const(1f),
  intensity: Expression<FloatValue> = const(1f),
  onClick: FeaturesClickHandler? = null,
  onLongClick: FeaturesClickHandler? = null,
) {
  LayerNode(
    factory = { HeatmapLayer(id = id, source = source) },
    update = {
      set(sourceLayer) { layer.sourceLayer = it }
      set(minZoom) { layer.minZoom = it }
      set(maxZoom) { layer.maxZoom = it }
      set(filter) { layer.setFilter(it) }
      set(visible) { layer.visible = it }
      set(radius) { layer.setHeatmapRadius(it) }
      set(weight) { layer.setHeatmapWeight(it) }
      set(intensity) { layer.setHeatmapIntensity(it) }
      set(color) { layer.setHeatmapColor(it) }
      set(opacity) { layer.setHeatmapOpacity(it) }
    },
    onClick = onClick,
    onLongClick = onLongClick,
  )
}
