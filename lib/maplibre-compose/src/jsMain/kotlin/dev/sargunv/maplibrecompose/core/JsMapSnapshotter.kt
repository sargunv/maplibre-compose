package dev.sargunv.maplibrecompose.core

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.Dp
import io.github.dellisd.spatialk.geojson.BoundingBox

public class JsMapSnapshotter : MapSnapshotter {
  override fun snapshot(
    width: Dp,
    height: Dp,
    styleUri: String,
    region: BoundingBox?,
    cameraPosition: CameraPosition?,
    showLogo: Boolean,
    callback: (ImageBitmap) -> Unit,
    errorHandler: (String) -> Unit,
  ) {}

  override fun cancel() {}
}
