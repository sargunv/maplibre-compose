package dev.sargunv.maplibrecompose.demoapp.demos

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import dev.sargunv.maplibrecompose.compose.CameraState
import dev.sargunv.maplibrecompose.compose.MaplibreMap
import dev.sargunv.maplibrecompose.compose.rememberCameraState
import dev.sargunv.maplibrecompose.compose.rememberStyleState
import dev.sargunv.maplibrecompose.demoapp.DEFAULT_STYLE
import dev.sargunv.maplibrecompose.demoapp.Demo
import dev.sargunv.maplibrecompose.demoapp.DemoMapControls
import dev.sargunv.maplibrecompose.demoapp.DemoOrnamentSettings
import dev.sargunv.maplibrecompose.demoapp.DemoScaffold

object SnapshotterDemo : Demo {
  override val name = "Snapshotter"
  override val description = "Take a snapshot of the map"

  @Composable
  override fun Component(navigateUp: () -> Unit) {
    DemoScaffold(this, navigateUp) {
      Column {
        val isLoading = remember { mutableStateOf(false) }
        val snapshot = remember { mutableStateOf<ImageBitmap?>(null) }
        val cameraState = rememberCameraState()
        val styleState = rememberStyleState()

        Box(modifier = Modifier.weight(1f)) {
          MaplibreMap(
            styleUri = DEFAULT_STYLE,
            cameraState = cameraState,
            styleState = styleState,
            ornamentSettings = DemoOrnamentSettings(),
          )
          DemoMapControls(cameraState, styleState)

          if (isLoading.value) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
          }
        }

        SnapshotterControls(cameraState, isLoading, snapshot)

        snapshot.value?.let {
          SnapshotDialog(snapshot = it, onDismissRequest = { snapshot.value = null })
        }
      }
    }
  }

  @Composable
  private fun SnapshotterControls(
    cameraState: CameraState,
    isLoading: MutableState<Boolean>,
    snapshot: MutableState<ImageBitmap?>,
  ) {
    Row(
      modifier = Modifier.padding(16.dp).fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
      Button(
        onClick = {
          isLoading.value = true
          cameraState.snapshot(
            width = 500.dp,
            height = 500.dp,
            styleUri = DEFAULT_STYLE,
            cameraPosition = cameraState.position,
            callback = {
              isLoading.value = false
              snapshot.value = it.bitmap
            }
          )
        }
      ) {
        Text("Take snapshot")
      }
    }
  }

  @Composable
  fun SnapshotDialog(snapshot: ImageBitmap, onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
      Card(modifier = Modifier.padding(16.dp), shape = RoundedCornerShape(16.dp)) {
        Image(
          modifier = Modifier.padding(16.dp),
          bitmap = snapshot,
          contentDescription = "Snapshot",
        )
      }
    }
  }
}
