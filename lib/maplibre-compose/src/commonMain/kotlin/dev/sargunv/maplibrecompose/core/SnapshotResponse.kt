package dev.sargunv.maplibrecompose.core

import androidx.compose.ui.graphics.ImageBitmap

public sealed class SnapshotResponse {
  public data class Success(val image: ImageBitmap) : SnapshotResponse()

  public data class Error(val error: String) : SnapshotResponse()

  public data object Cancelled : SnapshotResponse()
}
