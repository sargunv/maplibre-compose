package dev.sargunv.maplibrecompose.core.source

import cocoapods.MapLibre.MLNVectorTileSource
import dev.sargunv.maplibrecompose.expressions.ast.Expression
import dev.sargunv.maplibrecompose.expressions.value.BooleanValue
import io.github.dellisd.spatialk.geojson.Feature
import platform.Foundation.NSURL

public actual class VectorSource actual constructor(id: String, uri: String) : Source() {
  override val impl: MLNVectorTileSource = MLNVectorTileSource(id, NSURL(string = uri))

  public actual fun querySourceFeatures(
    sourceLayerIds: Set<String>,
    predicate: Expression<BooleanValue>,
  ): List<Feature> {
    TODO()
  }
}
