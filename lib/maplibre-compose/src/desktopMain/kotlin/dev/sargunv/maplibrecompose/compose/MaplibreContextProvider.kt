package dev.sargunv.maplibrecompose.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.FrameWindowScope

@Composable
public fun FrameWindowScope.MaplibreContextProvider(content: @Composable () -> Unit) {
  val refreshRate = window.graphicsConfiguration.device.displayMode.refreshRate

  val context = remember(refreshRate) { MaplibreContext(refreshRate) }

  CompositionLocalProvider(LocalMaplibreContext provides context) { content() }
}

internal data class MaplibreContext(val refreshRate: Int)

internal val LocalMaplibreContext =
  compositionLocalOf<MaplibreContext> {
    error("No MaplibreContext provided; wrap your app with MaplibreContextProvider")
  }
