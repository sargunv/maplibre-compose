package dev.sargunv.maplibrecompose.core

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import cocoapods.MapLibre.MLNMapCamera
import cocoapods.MapLibre.MLNMapSnapshotOptions
import cocoapods.MapLibre.MLNMapSnapshotter
import dev.sargunv.maplibrecompose.core.util.toImageBitmap
import dev.sargunv.maplibrecompose.core.util.toMLNCoordinateBounds
import dev.sargunv.maplibrecompose.core.util.toMLNMapCamera
import io.github.dellisd.spatialk.geojson.BoundingBox
import platform.CoreGraphics.CGSizeMake
import platform.Foundation.NSURL

internal class IosMapSnapshotter(private val density: Density) : MapSnapshotter {
  private var impl: MLNMapSnapshotter? = null
  private var isStarted: Boolean = false

  override fun snapshot(
    width: Dp,
    height: Dp,
    styleUri: String,
    region: BoundingBox?,
    cameraPosition: CameraPosition?,
    showLogo: Boolean,
    callback: (ImageBitmap) -> Unit,
    errorHandler: (String) -> Unit,
  ) {
    with(density) {
      if (isStarted) return

      isStarted = true

      val size = CGSizeMake(width.roundToPx().toDouble(), height.roundToPx().toDouble())
      val options =
        MLNMapSnapshotOptions(
          styleURL = NSURL(string = styleUri),
          camera = cameraPosition?.toMLNMapCamera(size) ?: MLNMapCamera(),
          size = size,
        )
      region?.toMLNCoordinateBounds()?.let { options.coordinateBounds = it }
      options.showsLogo = showLogo

      impl = MLNMapSnapshotter(options)

      impl?.startWithCompletionHandler { snapshot, error ->
        snapshot?.let { callback(snapshot.image.toImageBitmap()) }

        error?.description?.let { errorHandler(it) }

        isStarted = false
      }
    }
  }

  override fun cancel() {
    impl?.cancel()
    isStarted = false
  }
}
