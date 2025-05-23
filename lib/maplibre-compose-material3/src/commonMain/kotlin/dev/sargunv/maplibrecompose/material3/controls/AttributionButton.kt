package dev.sargunv.maplibrecompose.material3.controls

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.LinkAnnotation.Url
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import dev.sargunv.maplibrecompose.compose.CameraState
import dev.sargunv.maplibrecompose.core.source.AttributionLink
import dev.sargunv.maplibrecompose.material3.generated.Res
import dev.sargunv.maplibrecompose.material3.generated.attribution
import dev.sargunv.maplibrecompose.material3.generated.info
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

/**
 * Info button from which an attribution popup text is expanded from. The attribution text retracts
 * once when the user first starts interacting with the map.
 *
 * @param isCameraMoving Whether the camera is moving. See [CameraState.isCameraMoving].
 * @param attributions List of attributions to show. See
 *   [Source.attributionLinks][dev.sargunv.maplibrecompose.core.source.Source.attributionLinks] via
 *   [StyleState.sources][dev.sargunv.maplibrecompose.compose.StyleState.sources]
 * @param modifier the Modifier to be applied to this layout node
 * @param iconColors Colors that will be used for the info button
 * @param textStyle Text style used for the attribution info
 * @param textLinkStyles Text link styles that should be used for the links in the attribution info
 * @param popupEndPadding Padding that will be applied on the end of the popup (i.e. if the popup is
 *   aligned to the start, padding will be applied on the end, but if the popup is aligned to the
 *   end, padding will be applied to the start)
 * @param popupShape Shape of the popup (applied to [Surface])
 * @param popupColor Color of the popup (applied to [Surface])
 * @param popupContentColor Content Color of the popup (applied to [Surface])
 * @param popupTonalElevation Tonal Elevation of the popup (applied to [Surface])
 * @param popupShadowElevation Shadow Elevation of the popup (applied to [Surface])
 * @param popupBorder Border of the popup (applied to [Surface])
 */
@Composable
public fun AttributionButton(
  isCameraMoving: Boolean,
  attributions: List<AttributionLink>,
  modifier: Modifier = Modifier,
  iconColors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
  textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
  textLinkStyles: TextLinkStyles? = null,
  popupEndPadding: Dp = 0.dp,
  popupShape: Shape = RoundedCornerShape(24.dp),
  popupColor: Color = MaterialTheme.colorScheme.surface,
  popupContentColor: Color = contentColorFor(popupColor),
  popupTonalElevation: Dp = 0.dp,
  popupShadowElevation: Dp = 0.dp,
  popupBorder: BorderStroke? = null,
) {
  if (attributions.isEmpty()) return

  val expanded = remember { MutableTransitionState(true) }
  val density = LocalDensity.current
  val collapsedHeight = with(density) { 36.dp.roundToPx() }

  val surfaceColor by
    animateColorAsState(if (expanded.targetState) popupColor else popupColor.copy(alpha = 0f))

  // close popup on moving the map
  LaunchedEffect(isCameraMoving) {
    if (isCameraMoving) {
      expanded.targetState = false
    }
  }

  Box(modifier) {
    Surface(
      shape = popupShape,
      color = surfaceColor,
      contentColor = popupContentColor,
      tonalElevation = popupTonalElevation,
      shadowElevation = popupShadowElevation,
      border = popupBorder,
    ) {
      Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        IconButton(
          onClick = { expanded.targetState = !expanded.targetState },
          colors = iconColors,
          modifier = Modifier.align(Alignment.Bottom),
        ) {
          Icon(
            imageVector = vectorResource(Res.drawable.info),
            contentDescription = stringResource(Res.string.attribution),
          )
        }

        AnimatedVisibility(
          visibleState = expanded,
          enter = expandIn(initialSize = { IntSize(0, collapsedHeight) }),
          exit = shrinkOut(targetSize = { IntSize(0, collapsedHeight) }),
        ) {
          Row {
            ProvideTextStyle(value = textStyle) {
              FlowRow(
                modifier = Modifier.padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
              ) {
                attributions.distinct().forEach {
                  val attributionString = buildAnnotatedString {
                    val link = Url(url = it.url, styles = textLinkStyles)
                    withLink(link) { this.append(it.title) }
                  }
                  Text(text = attributionString)
                }
              }
            }
            Spacer(Modifier.width(16.dp))
          }
        }
      }
    }
  }
}
