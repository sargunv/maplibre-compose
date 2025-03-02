package dev.sargunv.maplibrecompose.core

import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import cocoapods.MapLibre.MLNMapCamera
import cocoapods.MapLibre.MLNMapSnapshotOptions
import cocoapods.MapLibre.MLNMapSnapshotter
import dev.sargunv.maplibrecompose.core.util.toImageBitmap
import dev.sargunv.maplibrecompose.core.util.toMLNCoordinateBounds
import dev.sargunv.maplibrecompose.core.util.toMLNMapCamera
import io.github.dellisd.spatialk.geojson.BoundingBox
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.CoreGraphics.CGSizeMake
import platform.Foundation.NSURL
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.resume

internal class IosMapSnapshotter(private val density: Density) : MapSnapshotter {
  override suspend fun snapshot(
    width: Dp,
    height: Dp,
    styleUri: String,
    region: BoundingBox?,
    cameraPosition: CameraPosition?,
    showLogo: Boolean,
  ): SnapshotResponse {
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

      return try {
        suspendCancellableCoroutine { cont ->
          snapshotter?.startWithCompletionHandler { snapshot, error ->
            if (snapshot != null) {
              cont.resume(SnapshotResponse.Success(snapshot.image.toImageBitmap()))
            } else {
              cont.resume(SnapshotResponse.Error(error?.description ?: "Unknown error"))
            }
          }
        }
      } catch (e: CancellationException) {
        snapshotter.cancel()
        SnapshotResponse.Cancelled
      }
    }
  }
}
