package dev.sargunv.maplibrecompose.core.util

import androidx.compose.runtime.Composable

public actual object PlatformUtils {
  @Composable public actual fun getSystemRefreshRate(): Float = 60f // TODO
}
