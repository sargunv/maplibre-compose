package dev.sargunv.maplibrecompose.core

import androidx.compose.ui.graphics.ImageBitmap

public data class MapSnapshot(
  val bitmap: ImageBitmap,
  val attributions: List<String>,
  val isShowLogo: Boolean,
)
