package dev.sargunv.maplibrecompose.compose.source

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key as composeKey
import dev.sargunv.maplibrecompose.core.source.Defaults
import dev.sargunv.maplibrecompose.core.source.RasterSource
import dev.sargunv.maplibrecompose.core.source.TileSetOptions

/**
 * Remember a new [RasterSource] with the given [id] and [tileSize] from the given [uri].
 *
 * @throws IllegalArgumentException if a layer with the given [id] already exists.
 */
@Composable
public fun rememberRasterSource(
  id: String,
  uri: String,
  tileSize: Int = Defaults.RASTER_TILE_SIZE,
): RasterSource =
  composeKey(id, uri, tileSize) {
    rememberUserSource(
      factory = { RasterSource(id = id, uri = uri, tileSize = tileSize) },
      update = {},
    )
  }

@Composable
public fun rememberRasterSource(
  id: String,
  tiles: List<String>,
  options: TileSetOptions = TileSetOptions(),
  tileSize: Int = Defaults.RASTER_TILE_SIZE,
): RasterSource =
  composeKey(id, tiles, options, tileSize) {
    rememberUserSource(
      factory = { RasterSource(id = id, tiles = tiles, options = options, tileSize = tileSize) },
      update = {},
    )
  }
