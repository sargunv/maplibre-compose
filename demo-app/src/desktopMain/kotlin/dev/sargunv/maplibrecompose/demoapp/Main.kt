package dev.sargunv.maplibrecompose.demoapp

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.singleWindowApplication
import dev.sargunv.maplibrecompose.compose.MaplibreContextProvider

// -8<- [start:main]
fun main() {
  // https://youtrack.jetbrains.com/issue/CMP-7352/
  // System.setProperty("compose.interop.blending", "true")
  // System.setProperty("compose.swing.render.on.graphics", "true") // TODO only on Linux
  singleWindowApplication { MaplibreContextProvider { DemoApp() } }
}

// -8<- [end:main]

@Composable
actual fun getDefaultColorScheme(isDark: Boolean): ColorScheme {
  return if (isDark) darkColorScheme() else lightColorScheme()
}

actual object Platform {
  actual val isAndroid: Boolean = false
  actual val isIos: Boolean = false
  actual val isDesktop: Boolean = true
  actual val isWeb: Boolean = false
}
