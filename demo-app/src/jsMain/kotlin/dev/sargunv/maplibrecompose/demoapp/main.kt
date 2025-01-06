package dev.sargunv.maplibrecompose.demoapp

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
  onWasmReady { ComposeViewport(document.body!!) { DemoApp() } }
}

@Composable
actual fun getDefaultColorScheme(isDark: Boolean): ColorScheme {
  return if (isDark) darkColorScheme() else lightColorScheme()
}

actual object Platform {
  actual val isAndroid: Boolean = false
  actual val isIos: Boolean = false
  actual val isDesktop: Boolean = false
  actual val isWeb: Boolean = true
}
