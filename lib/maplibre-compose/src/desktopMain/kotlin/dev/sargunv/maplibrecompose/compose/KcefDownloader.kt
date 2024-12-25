package dev.sargunv.maplibrecompose.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dev.datlag.kcef.KCEF
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
internal fun KcefDownloader(content: @Composable () -> Unit) {
  var initialized by remember { mutableStateOf(false) }

  LaunchedEffect(Unit) {
    withContext(Dispatchers.IO) {
      KCEF.init({
        // TODO https://github.com/harawata/appdirs
        installDir(File("kcef-bundle"))
      })
    }
    initialized = true
  }

  if (initialized) content() else Box(modifier = Modifier.fillMaxSize())

  DisposableEffect(Unit) { onDispose { KCEF.disposeBlocking() } }
}
