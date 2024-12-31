package dev.sargunv.maplibrecompose.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import co.touchlab.kermit.Logger
import dev.sargunv.composehtmlinterop.HtmlElement
import dev.sargunv.maplibrecompose.core.MaplibreMap
import dev.sargunv.maplibrejs.Map
import dev.sargunv.maplibrejs.MapOptions
import kotlinx.browser.document
import org.w3c.dom.HTMLElement

@Composable
internal actual fun ComposableMapView(
  modifier: Modifier,
  styleUri: String,
  update: (map: MaplibreMap) -> Unit,
  onReset: () -> Unit,
  logger: Logger?,
  callbacks: MaplibreMap.Callbacks,
) =
  WebMapView(
    modifier = modifier,
    styleUri = styleUri,
    update = update,
    onReset = onReset,
    logger = logger,
    callbacks = callbacks,
  )

@Composable
internal fun WebMapView(
  modifier: Modifier,
  styleUri: String,
  update: (map: MaplibreMap) -> Unit,
  onReset: () -> Unit,
  logger: Logger?,
  callbacks: MaplibreMap.Callbacks,
) {
  var maybeMap by remember { mutableStateOf<Map?>(null) }

  HtmlElement(
    modifier = modifier.onGloballyPositioned { maybeMap?.resize() },
    factory = {
      document.createElement("div").unsafeCast<HTMLElement>().apply {
        style.apply {
          width = "100%"
          height = "100%"
        }
      }
    },
    update = { element ->
      val map =
        maybeMap
          ?: Map(MapOptions(container = element, disableAttributionControl = true)).also {
            maybeMap = it
          }
      map.setStyle(styleUri)
    },
  )
}
