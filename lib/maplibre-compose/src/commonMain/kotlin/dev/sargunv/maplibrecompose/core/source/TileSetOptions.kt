package dev.sargunv.maplibrecompose.core.source

import androidx.compose.runtime.Immutable
import io.github.dellisd.spatialk.geojson.BoundingBox

@Immutable
public data class TileSetOptions(
  val minZoom: Int = 0,
  val maxZoom: Int = 18,
  val tileCoordinateSystem: TileCoordinateSystem = TileCoordinateSystem.XYZ,
  val boundingBox: BoundingBox? = null,
  val attributionHtml: String? = null,
)
