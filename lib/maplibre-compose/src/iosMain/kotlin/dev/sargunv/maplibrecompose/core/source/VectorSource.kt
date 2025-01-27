package dev.sargunv.maplibrecompose.core.source

import cocoapods.MapLibre.MLNVectorTileSource
import dev.sargunv.maplibrecompose.expressions.ast.Expression
import dev.sargunv.maplibrecompose.expressions.value.BooleanValue
import io.github.dellisd.spatialk.geojson.Feature
import platform.Foundation.NSURL

public actual class VectorSource : Source {
  override val impl: MLNVectorTileSource

  internal constructor(source: MLNVectorTileSource) {
    impl = source
  }

  public actual constructor(id: String, uri: String) : super() {
    this.impl = MLNVectorTileSource(id, NSURL(string = uri))
  }

  public actual fun querySourceFeatures(
    sourceLayerIds: Set<String>,
    predicate: Expression<BooleanValue>,
  ): List<Feature> {
    TODO()
  }
}
