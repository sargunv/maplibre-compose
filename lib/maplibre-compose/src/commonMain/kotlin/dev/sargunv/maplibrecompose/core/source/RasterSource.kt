package dev.sargunv.maplibrecompose.core.source

/**
 * A map data source of tiled map pictures.
 *
 * @param id Unique identifier for this source
 * @param uri URI pointing to a JSON file that conforms to the
 *   [TileJSON specification](https://github.com/mapbox/tilejson-spec/)
 * @param tileSize width and height (measured in points) of each tiled image in the raster tile
 *   source
 */
public expect class RasterSource : Source {
  public constructor(id: String, uri: String, tileSize: Int = DEFAULT_RASTER_TILE_SIZE)

  public constructor(
    id: String,
    tiles: List<String>,
    options: TileSetOptions = TileSetOptions(),
    tileSize: Int = DEFAULT_RASTER_TILE_SIZE,
  )
}

public const val DEFAULT_RASTER_TILE_SIZE: Int = 512
