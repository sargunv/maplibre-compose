package dev.sargunv.maplibrecompose.material3.controls

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import dev.sargunv.maplibrecompose.compose.CameraState
import dev.sargunv.maplibrecompose.core.CameraMoveReason
import dev.sargunv.maplibrecompose.core.source.AttributionLink
import dev.sargunv.maplibrecompose.material3.generated.Res
import dev.sargunv.maplibrecompose.material3.generated.attribution
import org.jetbrains.compose.resources.stringResource

/**
 * Info button from which an attribution text is expanded towards the start. The attribution text
 * retracts when the user starts interacting with the map.
 *
 * @param lastCameraMoveReason The reason reason why the camera moved, last time it moved. See
 *   [CameraState.moveReason]. (The attribution text will automatically retract when the map is
 *   moved by the user.)
 * @param attributions List of attributions to show. See
 *   [StyleState.queryAttributionLinks][dev.sargunv.maplibrecompose.compose.StyleState.queryAttributionLinks]
 * @param modifier the Modifier to be applied to this layout node
 * @param colors Colors that will be used for the info button
 * @param textStyle Text style used for the attribution info
 * @param textLinkStyles Text link styles that should be used for the links in the attribution info
 */
@Composable
public fun AttributionButton(
  lastCameraMoveReason: CameraMoveReason,
  attributions: List<AttributionLink>,
  modifier: Modifier = Modifier,
  colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
  textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
  textLinkStyles: TextLinkStyles? = null,
) {
  if (attributions.isEmpty()) return

  var expanded by remember { mutableStateOf(true) }

  LaunchedEffect(lastCameraMoveReason) {
    if (lastCameraMoveReason == CameraMoveReason.GESTURE) {
      expanded = false
    }
  }

  val surfaceColor = MaterialTheme.colorScheme.surface
  val contentColor = contentColorFor(surfaceColor)
  // rounded corner the size of the info button
  val cornerSize = 24.dp

  Box(modifier = modifier, contentAlignment = Alignment.BottomEnd) {
    // the background is separate from the attribution texts because it should, when visible, also
    // cover the info icon button
    AnimatedVisibility(expanded, modifier = Modifier.matchParentSize()) {
      Box(
        Modifier.matchParentSize()
          .padding(4.dp)
          .background(surfaceColor, RoundedCornerShape(cornerSize))
      )
    }
    CompositionLocalProvider(LocalContentColor provides contentColor) {
      Row(horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) {
        AnimatedVisibility(expanded, modifier = Modifier.weight(1f, fill = false)) {
          AttributionTexts(
            attributions = attributions,
            // make sure that the text always fits in the rounded corner background
            modifier = Modifier.padding(vertical = 4.dp).padding(start = cornerSize - 4.dp),
            textStyle = textStyle,
            textLinkStyles = textLinkStyles,
          )
        }
        InfoIconButton(
          onClick = { expanded = !expanded },
          colors = colors,
          modifier = Modifier.align(Alignment.Bottom),
        )
      }
    }
  }
}

@Composable
private fun InfoIconButton(
  onClick: () -> Unit,
  colors: IconButtonColors,
  modifier: Modifier = Modifier,
) {
  IconButton(onClick = onClick, modifier = modifier, colors = colors) {
    Icon(
      imageVector = Icons.Outlined.Info,
      contentDescription = stringResource(Res.string.attribution),
    )
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun AttributionTexts(
  attributions: List<AttributionLink>,
  textStyle: TextStyle,
  textLinkStyles: TextLinkStyles?,
  modifier: Modifier = Modifier,
) {
  ProvideTextStyle(textStyle) {
    FlowRow(
      modifier = modifier,
      horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.End),
      verticalArrangement = Arrangement.Bottom,
    ) {
      attributions.forEach {
        val text = buildAnnotatedString {
          withLink(LinkAnnotation.Url(url = it.url, styles = textLinkStyles)) { append(it.title) }
        }
        Text(text)
      }
    }
  }
}
