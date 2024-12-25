package dev.sargunv.maplibrecompose.demoapp

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.singleWindowApplication
import dev.sargunv.maplibrecompose.compose.MaplibreContext

// TODO This should enable support for blending Compose over Swing views
// but it's just crashing on launch
// https://github.com/JetBrains/compose-multiplatform-core/pull/915#issuecomment-1954155894
// System.setProperty("compose.interop.blending", "true")

// -8<- [start:main]
fun main() {
  singleWindowApplication { MaplibreContext { DemoApp() } }
}

// -8<- [end:main]

@Composable
actual fun getDefaultColorScheme(isDark: Boolean): ColorScheme {
  return if (isDark) darkColorScheme() else lightColorScheme()
}
