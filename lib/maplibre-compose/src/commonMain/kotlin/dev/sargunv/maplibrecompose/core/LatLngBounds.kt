package dev.sargunv.maplibrecompose.core

import io.github.dellisd.spatialk.geojson.Position

/**
 * A geographical area representing a latitude/longitude aligned rectangle.
 */
public data class LatLngBounds(
  /** Position corresponding to the northeast corner */
  val northEast: Position,
  /** Position corresponding to the southwest corner */
  val southWest: Position,
)
