package dev.sargunv.maplibrecompose.material3.controls

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import dev.sargunv.maplibrecompose.core.source.AttributionLink
import dev.sargunv.maplibrecompose.material3.generated.Res
import dev.sargunv.maplibrecompose.material3.generated.attribution
import org.jetbrains.compose.resources.stringResource

/**
 * Info button from which an attribution text is expanded towards the start. The attribution text
 * should (optionally) retract when the user starts interacting with the map.
 *
 * @param attributions List of attributions to show
 * @param onClick Called when the info button is clicked. Should expand the attribution.
 * @param expanded Whether the attribution info is shown next to the info button. It should start
 *   out as expanded, then retract when the user interacts with the map
 * @param modifier the Modifier to be applied to this layout node
 * @param colors Colors that will be used for the info button
 * @param textStyle Text style used for the attribution info
 * @param textLinkStyles Text link styles that should be used for the links in the attribution info
 * */
@OptIn(ExperimentalLayoutApi::class)
@Composable
public fun AttributionButton(
  attributions: List<AttributionLink>,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  expanded: Boolean = true,
  colors: IconButtonColors = IconButtonDefaults.iconButtonColors(),
  textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
  textLinkStyles: TextLinkStyles? = null,
) {
  if (attributions.isEmpty()) return

  Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.End,
    verticalAlignment = Alignment.CenterVertically
  ) {
    AnimatedVisibility(expanded, modifier = Modifier.weight(1f, fill = false)) {
      Surface(shape = MaterialTheme.shapes.medium) {
        ProvideTextStyle(textStyle) {
          FlowRow(
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.End),
            verticalArrangement = Arrangement.Bottom,
          ) {
            attributions.forEach {
              val text = buildAnnotatedString {
                withLink(LinkAnnotation.Url(url = it.url, styles = textLinkStyles)) {
                  append(it.title)
                }
              }
              Text(text)
            }
          }
        }
      }
    }
    IconButton(onClick = onClick, modifier = Modifier.align(Alignment.Bottom), colors = colors) {
      Icon(
        imageVector = Icons.Outlined.Info,
        contentDescription = stringResource(Res.string.attribution)
      )
    }
  }
}
