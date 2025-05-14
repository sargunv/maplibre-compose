package dev.sargunv.maplibrecompose.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.viewinterop.AndroidView
import co.touchlab.kermit.Logger
import dev.sargunv.maplibrecompose.core.AndroidMap
import dev.sargunv.maplibrecompose.core.AndroidScaleBar
import dev.sargunv.maplibrecompose.core.MaplibreMap
import org.maplibre.android.MapLibre
import org.maplibre.android.gestures.AndroidGesturesManager
import org.maplibre.android.maps.MapLibreMapOptions
import org.maplibre.android.maps.MapView

@Composable
internal actual fun ComposableMapView(
  modifier: Modifier,
  styleUri: String,
  update: (map: MaplibreMap) -> Unit,
  onReset: () -> Unit,
  logger: Logger?,
  callbacks: MaplibreMap.Callbacks,
) {
  AndroidMapView(
    modifier = modifier,
    styleUri = styleUri,
    update = update,
    onReset = onReset,
    logger = logger,
    callbacks = callbacks,
  )
}

@Composable
internal fun AndroidMapView(
  modifier: Modifier,
  styleUri: String,
  update: (map: MaplibreMap) -> Unit,
  onReset: () -> Unit,
  logger: Logger?,
  callbacks: MaplibreMap.Callbacks,
) {
  val layoutDir = LocalLayoutDirection.current
  val density = LocalDensity.current
  val currentOnReset by rememberUpdatedState(onReset)

  var currentMapView by remember { mutableStateOf<MapView?>(null) }
  var currentMap by remember { mutableStateOf<AndroidMap?>(null) }

  MapViewLifecycleEffect(currentMapView)

  AndroidView(
    modifier = modifier,
    factory = { context ->
      MapLibre.getInstance(context)
      MapView(context, MapLibreMapOptions.createFromAttributes(context).textureMode(false)).also {
        mapView ->
        currentMapView = mapView
        mapView.getMapAsync { map ->
          map.setGesturesManager(AndroidGesturesManager(context), false, false)
          mapView.isClickable = false
          mapView.isLongClickable = false
          mapView.isFocusable = false
          mapView.setFocusableInTouchMode(false)
          mapView.requestDisallowInterceptTouchEvent(false)
          currentMap =
            AndroidMap(
              mapView = mapView,
              map = map,
              scaleBar = AndroidScaleBar(context, mapView, map),
              layoutDir = layoutDir,
              density = density,
              callbacks = callbacks,
              styleUri = styleUri,
              logger = logger,
            )

          currentMap?.let { update(it) }
        }
      }
    },
    update = { _ ->
      val map = currentMap ?: return@AndroidView
      map.layoutDir = layoutDir
      map.density = density
      map.callbacks = callbacks
      map.logger = logger
      map.setStyleUri(styleUri)
      update(map)
    },
    onReset = {
      currentOnReset()
      currentMap = null
      currentMapView = null
    },
  )
}
