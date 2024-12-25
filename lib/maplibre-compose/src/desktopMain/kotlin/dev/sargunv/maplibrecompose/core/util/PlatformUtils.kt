package dev.sargunv.maplibrecompose.core.util

import androidx.compose.runtime.Composable

public actual object PlatformUtils {
  @Composable
  public actual fun getSystemRefreshRate(): Float {
    // TODO get it from FrameWindowScope at the top of the app
    return 60f
  }
}
