package dev.sargunv.maplibrecompose.core

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.Dp
import io.github.dellisd.spatialk.geojson.BoundingBox

internal interface MapSnapshotter {
  fun snapshot(
    width: Dp,
    height: Dp,
    styleUri: String,
    region: BoundingBox?,
    cameraPosition: CameraPosition?,
    showLogo: Boolean,
    callback: (ImageBitmap) -> Unit,
    errorHandler: (String) -> Unit,
  )

  fun cancel()
}
