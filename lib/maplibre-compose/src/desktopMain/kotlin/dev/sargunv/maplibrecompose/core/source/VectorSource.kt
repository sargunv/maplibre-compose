package dev.sargunv.maplibrecompose.core.source

import dev.sargunv.maplibrecompose.expressions.ast.CompiledExpression
import dev.sargunv.maplibrecompose.expressions.value.BooleanValue
import io.github.dellisd.spatialk.geojson.Feature

public actual class VectorSource actual constructor(id: String, uri: String) : Source() {
  override val impl: Nothing = TODO()

  public actual fun querySourceFeatures(
    sourceLayerIds: Set<String>,
    predicate: CompiledExpression<BooleanValue>?,
  ): List<Feature> {
    TODO()
  }
}
