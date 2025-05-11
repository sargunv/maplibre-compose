package dev.sargunv.maplibrecompose.demoapp.demos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.sargunv.maplibrecompose.compose.MaplibreMap
import dev.sargunv.maplibrecompose.compose.layer.RasterLayer
import dev.sargunv.maplibrecompose.compose.rememberCameraState
import dev.sargunv.maplibrecompose.compose.rememberStyleState
import dev.sargunv.maplibrecompose.compose.source.rememberRasterSource
import dev.sargunv.maplibrecompose.core.source.TileSetOptions
import dev.sargunv.maplibrecompose.demoapp.Demo
import dev.sargunv.maplibrecompose.demoapp.DemoMapControls
import dev.sargunv.maplibrecompose.demoapp.DemoOrnamentSettings
import dev.sargunv.maplibrecompose.demoapp.DemoScaffold
import dev.sargunv.maplibrecompose.demoapp.format
import dev.sargunv.maplibrecompose.demoapp.generated.Res
import kotlin.math.roundToInt

object LocalTilesDemo : Demo {
  override val name = "Tile Set"
  override val description = "Configure a Tile Set programatically with local tiles"

  @Composable
  override fun Component(navigateUp: () -> Unit) {
    DemoScaffold(this, navigateUp) {
      Column {
        val cameraState = rememberCameraState()
        val styleState = rememberStyleState()

        Box(modifier = Modifier.Companion.weight(1f)) {
          MaplibreMap(
            styleUri = Res.getUri("files/styles/empty.json"),
            cameraState = cameraState,
            styleState = styleState,
            ornamentSettings = DemoOrnamentSettings(),
          ) {
            val tiles =
              rememberRasterSource(
                id = "fantasy-map",
                tileSize = 256,
                tiles =
                  // TODO this is not working on Android atm (works on iOS)
                  listOf(
                    Res.getUri("files/data/fantasy-map/0/0-0-fs8.png")
                      .replace("0/0-0", "{z}/{x}-{y}")
                  ),
                options = TileSetOptions(minZoom = 0, maxZoom = 4),
              )

            RasterLayer(id = "fantasy-map", source = tiles)
          }
          DemoMapControls(cameraState, styleState)
        }

        Row(modifier = Modifier.safeDrawingPadding().wrapContentSize(Alignment.Center)) {
          val pos = cameraState.position
          val scale = cameraState.metersPerDpAtTarget

          Cell("Latitude", pos.target.latitude.format(3), Modifier.weight(1.4f))
          Cell("Longitude", pos.target.longitude.format(3), Modifier.weight(1.4f))
          Cell("Zoom", pos.zoom.format(2), Modifier.weight(1f))
          Cell("Bearing", pos.bearing.format(2), Modifier.weight(1f))
          Cell("Tilt", pos.tilt.format(2), Modifier.weight(1f))
          Cell("Scale", "${scale.roundToInt()}m", Modifier.weight(1f))
        }
      }
    }
  }
}

@Composable
private fun Cell(title: String, value: String, modifier: Modifier = Modifier) {
  Column(modifier = modifier.padding(PaddingValues(4.dp)).wrapContentSize(Alignment.Center)) {
    Text(
      text = title,
      textAlign = TextAlign.Center,
      maxLines = 1,
      style = MaterialTheme.typography.labelSmall,
    )
    Text(
      text = value,
      textAlign = TextAlign.Center,
      maxLines = 1,
      style = MaterialTheme.typography.bodySmall,
    )
  }
}
