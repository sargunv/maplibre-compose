package dev.sargunv.maplibrecompose.core

import androidx.compose.ui.unit.Dp
import io.github.dellisd.spatialk.geojson.BoundingBox

public interface MapSnapshotter {
  public fun snapshot(
    width: Dp,
    height: Dp,
    styleUri: String,
    region: BoundingBox?,
    cameraPosition: CameraPosition?,
    showLogo: Boolean,
    localIdeographFontFamily: String?,
    pixelRatio: Float = 1f,
    callback: (MapSnapshot) -> Unit,
    errorHandler: (String) -> Unit,
  )

  public fun cancel()
}
