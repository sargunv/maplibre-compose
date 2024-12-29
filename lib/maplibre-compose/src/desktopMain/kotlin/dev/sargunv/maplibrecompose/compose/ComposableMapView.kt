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
    mapHtml = Res.readBytes("files/maplibre-compose-webview.html").toString(Charsets.UTF_8)
  }

  mapHtml?.let { html ->
    val state = rememberWebViewStateWithHTMLData(html)
    val navigator = rememberWebViewNavigator()

    WebView(modifier = modifier.fillMaxWidth(), state = state, navigator = navigator)

    LaunchedEffect(state.isLoading, styleUri) {
      if (!state.isLoading) {
        // TODO: prevent script injection
        navigator.evaluateJavaScript("globalThis['maplibre-compose-webview'].setStyle('$styleUri')")
      }
    }
  }
}
