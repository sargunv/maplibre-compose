package dev.sargunv.maplibrecompose.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.FrameWindowScope

@Composable
public fun FrameWindowScope.MaplibreContext(content: @Composable () -> Unit) {
  // TODO provide this somehow to getSystemRefreshRate
  println("refreshRate: ${this.window.graphicsConfiguration.device.displayMode.refreshRate}")
  // TODO also load webview html here so we only load it once
  content()
}
