package dev.sargunv.maplibrecompose.core.source

import dev.sargunv.maplibrecompose.core.util.correctedAndroidUri
import dev.sargunv.maplibrecompose.core.util.toMLNExpression
import dev.sargunv.maplibrecompose.expressions.ExpressionContext
import dev.sargunv.maplibrecompose.expressions.ast.Expression
import dev.sargunv.maplibrecompose.expressions.dsl.const
import dev.sargunv.maplibrecompose.expressions.value.BooleanValue
import io.github.dellisd.spatialk.geojson.Feature
import org.maplibre.android.style.sources.VectorSource as MLNVectorSource

public actual class VectorSource : Source {
  override val impl: MLNVectorSource

  internal constructor(source: MLNVectorSource) {
    impl = source
  }

  public actual constructor(id: String, uri: String) {
    impl = MLNVectorSource(id, uri.correctedAndroidUri())
  }

  public actual fun querySourceFeatures(
    sourceLayerIds: Set<String>,
    predicate: Expression<BooleanValue>,
  ): List<Feature> {
    val predicateOrNull =
      predicate.takeUnless { it == const(true) }?.compile(ExpressionContext.None)
    return impl
      .querySourceFeatures(sourceLayerIds.toTypedArray(), predicateOrNull?.toMLNExpression())
      .map { Feature.fromJson(it.toJson()) }
  }
}
