package dev.sargunv.maplibrecompose.compose

import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import co.touchlab.kermit.Logger
import dev.sargunv.composehtmlinterop.HtmlElement
import dev.sargunv.maplibrecompose.core.JsMap
import dev.sargunv.maplibrecompose.core.MaplibreMap
import dev.sargunv.maplibrejs.AnimationOptions
import dev.sargunv.maplibrejs.Point
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
  var maybeMap by remember { mutableStateOf<JsMap?>(null) }

  val layoutDir = LocalLayoutDirection.current
  val density = LocalDensity.current

  val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
    val map = maybeMap ?: return@rememberTransformableState
    println("zoomChange: $zoomChange, offsetChange: $offsetChange, rotationChange: $rotationChange")
    with(density) {
      map.impl.panBy(
        Point(-offsetChange.x.toDp().value.toDouble(), -offsetChange.y.toDp().value.toDouble()),
        js("{animate: false}").unsafeCast<AnimationOptions>(),
      )
    }
  }

  HtmlElement(
    modifier = modifier.onGloballyPositioned { maybeMap?.resize() }.transformable(state = state),
    zIndex = "-1", // TODO figure out pointer interop
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
        maybeMap ?: JsMap(element, layoutDir, density, callbacks, logger).also { maybeMap = it }
      map.setStyleUri(styleUri)
      map.layoutDir = layoutDir
      map.density = density
      map.callbacks = callbacks
      map.logger = logger
      update(map)
    },
  )

  DisposableEffect(Unit) { onDispose { onReset() } }
}
