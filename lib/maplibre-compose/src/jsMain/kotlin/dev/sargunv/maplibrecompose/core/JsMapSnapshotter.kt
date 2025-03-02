package dev.sargunv.maplibrecompose.core

import androidx.compose.ui.unit.Dp
import io.github.dellisd.spatialk.geojson.BoundingBox

internal class JsMapSnapshotter : MapSnapshotter {
  override suspend fun snapshot(
    width: Dp,
    height: Dp,
    styleUri: String,
    region: BoundingBox?,
    cameraPosition: CameraPosition?,
    showLogo: Boolean,
  ): SnapshotResponse {
    // missing feature in MapLibre GL JS
    return SnapshotResponse.Error("Not supported")
  }
}
