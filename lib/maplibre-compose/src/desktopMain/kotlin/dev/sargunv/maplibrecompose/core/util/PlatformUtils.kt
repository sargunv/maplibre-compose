package dev.sargunv.maplibrecompose.core.util

import androidx.compose.runtime.Composable
import dev.sargunv.maplibrecompose.compose.LocalMaplibreContext

public actual object PlatformUtils {
  @Composable
  public actual fun getSystemRefreshRate(): Float =
    LocalMaplibreContext.current.refreshRate.toFloat()
}
