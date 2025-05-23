package dev.sargunv.maplibrecompose.material3.controls

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.LinkAnnotation.Url
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import dev.sargunv.maplibrecompose.compose.CameraState
import dev.sargunv.maplibrecompose.compose.StyleState
import dev.sargunv.maplibrecompose.core.CameraMoveReason
import dev.sargunv.maplibrecompose.material3.generated.Res
import dev.sargunv.maplibrecompose.material3.generated.attribution
import dev.sargunv.maplibrecompose.material3.generated.info
import dev.sargunv.maplibrecompose.material3.reversed
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

/**
 * Info button from which an attribution popup text is expanded from. The attribution text retracts
 * once when the user first starts interacting with the map.
 *
 * @param cameraState Used to dismiss the attribution when the user interacts with the map.
 * @param styleState Used to get the attribution links to display.
 * @param modifier the Modifier to be applied to this layout node
 * @param iconColors Colors that will be used for the info button
 * @param iconAlignment Will be used to determine layout of the attribution icon and text. Supports
 *   the four corners.
 * @param textStyle Text style used for the attribution info
 * @param textLinkStyles Text link styles that should be used for the links in the attribution info
 * @param shape Shape of the attribution (applied to [Surface])
 * @param collapsedColor Color of the attribution when collapsed (applied to [Surface])
 * @param expandedColor Color of the attribution when expanded (applied to [Surface])
 * @param contentColor Content Color of the attribution (applied to [Surface])
 * @param tonalElevation Tonal Elevation of the attribution (applied to [Surface])
 * @param shadowElevation Shadow Elevation of the attribution (applied to [Surface])
 * @param border Border of the attribution (applied to [Surface])
 */
@Composable
public fun AttributionButton(
  cameraState: CameraState,
  styleState: StyleState,
  modifier: Modifier = Modifier,
  iconColors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
  iconAlignment: Alignment = Alignment.BottomEnd,
  textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
  textLinkStyles: TextLinkStyles? = null,
  shape: Shape = RoundedCornerShape(24.dp),
  expandedColor: Color = MaterialTheme.colorScheme.surface,
  collapsedColor: Color = MaterialTheme.colorScheme.surface.copy(alpha = 0f),
  contentColor: Color = contentColorFor(expandedColor),
  tonalElevation: Dp = 0.dp,
  shadowElevation: Dp = 0.dp,
  border: BorderStroke? = null,
) {
  val attributions = styleState.sources.flatMap { it.attributionLinks }
  if (attributions.isEmpty()) return

  val expanded = remember { MutableTransitionState(true) }
  var collapsedOnce by remember { mutableStateOf(false) }

  val surfaceColor by
    animateColorAsState(if (expanded.targetState) expandedColor else collapsedColor)

  // we align a fake point inside a 2x2 grid, to determine arrangement and alignment of contents
  val testAlignment =
    remember(iconAlignment) {
      iconAlignment.align(
        size = IntSize(1, 1),
        space = IntSize(2, 2),
        layoutDirection = LayoutDirection.Ltr,
      )
    }

  val reverseArrangement = testAlignment.x == 1
  val rowArrangement = if (reverseArrangement) Arrangement.End.reversed() else Arrangement.Start
  val iconVerticalAlignment = if (testAlignment.y == 0) Alignment.Top else Alignment.Bottom

  if (!collapsedOnce) {
    LaunchedEffect(cameraState.isCameraMoving, cameraState.moveReason) {
      if (cameraState.isCameraMoving && cameraState.moveReason == CameraMoveReason.GESTURE) {
        expanded.targetState = false
        collapsedOnce = true
      }
    }
  }

  Box(modifier) {
    Surface(
      shape = shape,
      color = surfaceColor,
      contentColor = contentColor,
      tonalElevation = tonalElevation,
      shadowElevation = shadowElevation,
      border = border,
    ) {
      Row(horizontalArrangement = rowArrangement, verticalAlignment = Alignment.CenterVertically) {
        IconButton(
          onClick = { expanded.targetState = !expanded.targetState },
          colors = iconColors,
          modifier = Modifier.align(iconVerticalAlignment),
        ) {
          Icon(
            imageVector = vectorResource(Res.drawable.info),
            contentDescription = stringResource(Res.string.attribution),
          )
        }

        AnimatedVisibility(
          visibleState = expanded,
          enter = expandHorizontally() + fadeIn(),
          exit = shrinkHorizontally() + fadeOut(),
        ) {
          ProvideTextStyle(value = textStyle) {
            FlowRow(
              modifier =
                Modifier.padding(
                  start = if (reverseArrangement) 16.dp else 0.dp,
                  end = if (reverseArrangement) 0.dp else 16.dp,
                  top = 8.dp,
                  bottom = 8.dp,
                ),
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
        }
      }
    }
  }
}
