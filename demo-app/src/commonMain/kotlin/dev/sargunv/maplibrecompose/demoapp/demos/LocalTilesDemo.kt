package dev.sargunv.maplibrecompose.demoapp.demos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.sargunv.maplibrecompose.compose.MaplibreMap
import dev.sargunv.maplibrecompose.compose.layer.RasterLayer
import dev.sargunv.maplibrecompose.compose.rememberCameraState
import dev.sargunv.maplibrecompose.compose.rememberStyleState
import dev.sargunv.maplibrecompose.compose.source.rememberRasterSource
import dev.sargunv.maplibrecompose.core.CameraPosition
import dev.sargunv.maplibrecompose.core.source.TileSetOptions
import dev.sargunv.maplibrecompose.demoapp.Demo
import dev.sargunv.maplibrecompose.demoapp.DemoMapControls
import dev.sargunv.maplibrecompose.demoapp.DemoOrnamentSettings
import dev.sargunv.maplibrecompose.demoapp.DemoScaffold
import dev.sargunv.maplibrecompose.demoapp.generated.Res
import io.github.dellisd.spatialk.geojson.Position

private val NEW_YORK = Position(latitude = 40.744, longitude = -73.981)

object LocalTilesDemo : Demo {
  // TODO spin this into a full local tiles demo
  override val name = "Tile Set"
  override val description = "Configure a Tile Set programatically"

  @Composable
  override fun Component(navigateUp: () -> Unit) {
    DemoScaffold(this, navigateUp) {
      Column {
        val cameraState =
          rememberCameraState(CameraPosition(target = NEW_YORK, zoom = 15.0, tilt = 30.0))
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
                id = "osm-raster",
                tileSize = 256,
                tiles =
                  listOf(
                    "https://a.tile.openstreetmap.org/{z}/{x}/{y}.png",
                    "https://b.tile.openstreetmap.org/{z}/{x}/{y}.png",
                    "https://c.tile.openstreetmap.org/{z}/{x}/{y}.png",
                  ),
                options =
                  TileSetOptions(
                    attributionHtml =
                      "&copy; <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap</a> contributors"
                  ),
              )

            RasterLayer(id = "tiles", source = tiles)
          }
          DemoMapControls(cameraState, styleState)
        }
      }
    }
  }
}
