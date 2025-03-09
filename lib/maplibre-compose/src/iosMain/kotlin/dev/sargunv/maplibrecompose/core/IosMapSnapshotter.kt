package dev.sargunv.maplibrecompose.core

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import cocoapods.MapLibre.MLNMapCamera
import cocoapods.MapLibre.MLNMapSnapshotOptions
import cocoapods.MapLibre.MLNMapSnapshotter
import dev.sargunv.maplibrecompose.core.util.toMLNCoordinateBounds
import dev.sargunv.maplibrecompose.core.util.toMLNMapCamera
import io.github.dellisd.spatialk.geojson.BoundingBox
import kotlin.coroutines.resume
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.CoreGraphics.CGSizeMake
import platform.Foundation.NSURL

internal class IosMapSnapshotter(private val density: Density) : MapSnapshotter {
  override suspend fun snapshot(
    width: Dp,
    height: Dp,
    styleUri: String,
    region: BoundingBox?,
    cameraPosition: CameraPosition?,
    showLogo: Boolean,
  ): ImageBitmap {
    with(density) {
      val size = CGSizeMake(width.roundToPx().toDouble(), height.roundToPx().toDouble())
      val options =
        MLNMapSnapshotOptions(
          styleURL = NSURL(string = styleUri),
          camera = cameraPosition?.toMLNMapCamera(size) ?: MLNMapCamera(),
          size = size,
        )
      region?.toMLNCoordinateBounds()?.let { options.coordinateBounds = it }
      options.showsLogo = showLogo

      val snapshotter = MLNMapSnapshotter(options)

      return suspendCancellableCoroutine { cont ->
        snapshotter.startWithCompletionHandler { snapshot, error ->
          if (snapshot != null) {
            cont.resume(snapshot.bitmap.asImageBitmap())
          } else {
            throw SnapshotException(error?.description ?: "Unknown error")
          }
        }
        cont.invokeOnCancellation { snapshotter.cancel() }
      }
    }
  }
}
