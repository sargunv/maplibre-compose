package dev.sargunv.maplibrecompose.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import co.touchlab.kermit.Logger
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewNavigator
import com.multiplatform.webview.web.rememberWebViewStateWithHTMLData
import dev.sargunv.maplibrecompose.core.MaplibreMap
import dev.sargunv.maplibrecompose.generated.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
internal actual fun ComposableMapView(
  modifier: Modifier,
  styleUri: String,
  update: (map: MaplibreMap) -> Unit,
  onReset: () -> Unit,
  logger: Logger?,
  callbacks: MaplibreMap.Callbacks,
) {
  var mapHtml by remember { mutableStateOf<String?>(null) }

  LaunchedEffect(mapHtml) {
    if (mapHtml != null) return@LaunchedEffect
    val js = Res.readBytes("files/kotlin-maplibre-js.js").toString(Charsets.UTF_8)
    mapHtml = makeHtml(js)
  }

  mapHtml?.let { html ->
    val state = rememberWebViewStateWithHTMLData(html)
    val navigator = rememberWebViewNavigator()

    WebView(modifier = modifier.fillMaxWidth(), state = state, navigator = navigator)

    LaunchedEffect(state.isLoading, styleUri) {
      if (!state.isLoading) {
        // TODO: prevent script injection
        navigator.evaluateJavaScript("globalThis['kotlin-maplibre-js'].setStyle('$styleUri')")
      }
    }
  }
}

internal fun makeHtml(js: String) =
  """
  <!DOCTYPE html>
  <html lang="en">
  <head>
    <title>Map</title>
    <meta charset='utf-8'>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel='stylesheet' href='https://unpkg.com/maplibre-gl@4.7.1/dist/maplibre-gl.css'/>
    <style>
      body { margin: 0; padding: 0; }
      html, body { height: 100%; }
    </style>
  </head>
  <body>
  <script>
    $js
  </script>
  </body>
  </html>
"""
    .trimIndent()
