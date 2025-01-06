package dev.sargunv.maplibrecompose.core.source

import io.github.dellisd.spatialk.geojson.GeoJson

public actual class GeoJsonSource : Source {

  @Suppress("UNREACHABLE_CODE") override val impl: Nothing = TODO()

  public actual constructor(id: String, uri: String, options: GeoJsonOptions)

  public actual constructor(id: String, data: GeoJson, options: GeoJsonOptions)

  public actual fun setUri(uri: String) {
    TODO()
  }

  public actual fun setData(geoJson: GeoJson) {
    TODO()
  }
}
