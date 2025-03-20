package dev.sargunv.maplibrecompose.material3.controls

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.window.Popup
import dev.sargunv.maplibrecompose.compose.CameraState
import dev.sargunv.maplibrecompose.core.CameraMoveReason
import dev.sargunv.maplibrecompose.core.source.AttributionLink
import dev.sargunv.maplibrecompose.material3.Reverse
import dev.sargunv.maplibrecompose.material3.generated.Res
import dev.sargunv.maplibrecompose.material3.generated.attribution
import org.jetbrains.compose.resources.stringResource

/**
 * Info button from which an attribution popup text is expanded from. The attribution text retracts
 * once when the user first starts interacting with the map.
 *
 * @param lastCameraMoveReason The reason reason why the camera moved, last time it moved. See
 *   [CameraState.moveReason].
 * @param attributions List of attributions to show. See
 *   [Source.attributionLinks][dev.sargunv.maplibrecompose.core.source.Source.attributionLinks] via
 *   [StyleState.sources][dev.sargunv.maplibrecompose.compose.StyleState.sources]
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

  Box(modifier) {
    IconButton(onClick = { expanded = !expanded }, colors = colors) { InfoIcon() }

    if (expanded) {
      var alignLeft by remember { mutableStateOf(false) }
      var alignTop by remember { mutableStateOf(true) }
      val popupPositionProvider = SuperimposingPopupPositionProvider { left, top ->
        alignLeft = left
        alignTop = top
      }
      val arrangement = if (alignLeft) Arrangement.Absolute.Left else Arrangement.Absolute.Reverse
      val alignment = if (alignTop) Alignment.Top else Alignment.Bottom

      Popup(
        popupPositionProvider = popupPositionProvider,
        onDismissRequest = { expanded = false }
      ) {
        Surface(shape = RoundedCornerShape(24.dp)) {
          Row(horizontalArrangement = arrangement, verticalAlignment = Alignment.CenterVertically) {
            IconButton(
              onClick = { expanded = false },
              colors = colors,
              modifier = Modifier.align(alignment)
            ) { InfoIcon() }
            AttributionTexts(
              attributions = attributions,
              textStyle = textStyle,
              textLinkStyles = textLinkStyles,
              modifier = Modifier.padding(8.dp)
            )
            Spacer(Modifier.size(8.dp))
          }
        }
      }
    }
  }
}

@Composable
private fun InfoIcon(modifier: Modifier = Modifier) {
  Icon(
    imageVector = Icons.Outlined.Info,
    contentDescription = stringResource(Res.string.attribution),
    modifier = modifier
  )
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
    FlowRow(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
      attributions.forEach {
        Text(
          buildAnnotatedString {
            withLink(LinkAnnotation.Url(url = it.url, styles = textLinkStyles)) { append(it.title) }
          },
        )
      }
    }
  }
}
