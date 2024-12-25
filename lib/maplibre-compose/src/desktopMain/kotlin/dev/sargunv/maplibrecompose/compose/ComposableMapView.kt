package dev.sargunv.maplibrecompose.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import co.touchlab.kermit.Logger
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewNavigator
import com.multiplatform.webview.web.rememberWebViewStateWithHTMLData
import dev.sargunv.maplibrecompose.core.MaplibreMap
import org.intellij.lang.annotations.Language

@Composable
internal actual fun ComposableMapView(
  modifier: Modifier,
  styleUri: String,
  update: (map: MaplibreMap) -> Unit,
  onReset: () -> Unit,
  logger: Logger?,
  callbacks: MaplibreMap.Callbacks,
) {
  val state = rememberWebViewStateWithHTMLData(data = html)
  val navigator = rememberWebViewNavigator()

  WebView(modifier = modifier.fillMaxWidth(), state = state, navigator = navigator)

  LaunchedEffect(state.isLoading, styleUri) {
    if (!state.isLoading) {
      // TODO: prevent script injection
      navigator.evaluateJavaScript("window.maplibre_map.setStyle('$styleUri')")
    }
  }
}

@Language("HTML")
private const val html =
  """
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Map</title>
  <meta charset='utf-8'>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel='stylesheet' href='https://unpkg.com/maplibre-gl@4.7.1/dist/maplibre-gl.css' />
  <script src='https://unpkg.com/maplibre-gl@4.7.1/dist/maplibre-gl.js'></script>
  <style>
    body { margin: 0; padding: 0; }
    html, body, #map { height: 100%; }
  </style>
</head>
<body>
  <div id="map"></div>
  <script>
    window.maplibre_map = new maplibregl.Map({
      container: 'map'
    });
  </script>
</body>
</html>
"""
