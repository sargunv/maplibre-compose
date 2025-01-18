package dev.sargunv.maplibrecompose.core.source

import dev.sargunv.maplibrecompose.core.util.correctedAndroidUri
import dev.sargunv.maplibrecompose.core.util.toMLNExpression
import dev.sargunv.maplibrecompose.expressions.ast.CompiledExpression
import dev.sargunv.maplibrecompose.expressions.value.BooleanValue
import io.github.dellisd.spatialk.geojson.Feature
import org.maplibre.android.style.sources.VectorSource

public actual class VectorSource : Source {
  override val impl: VectorSource

  public constructor(source: VectorSource) {
    impl = source
  }

  public actual constructor(id: String, uri: String) {
    impl = VectorSource(id, uri.correctedAndroidUri())
  }

  public actual fun querySourceFeatures(
    sourceLayerIds: Set<String>,
    predicate: CompiledExpression<BooleanValue>?,
  ): List<Feature> {
    return impl
      .querySourceFeatures(sourceLayerIds.toTypedArray(), predicate?.toMLNExpression())
      .map { Feature.fromJson(it.toJson()) }
  }
}
