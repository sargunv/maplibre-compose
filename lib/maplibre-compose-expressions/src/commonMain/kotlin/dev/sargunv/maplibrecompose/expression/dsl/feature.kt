package dev.sargunv.maplibrecompose.expression.dsl

import dev.sargunv.maplibrecompose.expression.ast.Expression
import dev.sargunv.maplibrecompose.expression.ast.FunctionCall
import dev.sargunv.maplibrecompose.expression.value.BooleanValue
import dev.sargunv.maplibrecompose.expression.value.ExpressionValue
import dev.sargunv.maplibrecompose.expression.value.FloatValue
import dev.sargunv.maplibrecompose.expression.value.GeometryType
import dev.sargunv.maplibrecompose.expression.value.MapValue
import dev.sargunv.maplibrecompose.expression.value.StringValue

/** Object to access feature-related data, see [feature] */
public object Feature {
  /**
   * Returns the value corresponding to the given [key] in the current feature's properties or
   * `null` if it is not present.
   */
  public fun get(key: Expression<StringValue>): Expression<*> = FunctionCall.of("get", key)

  /** Tests for the presence of a property value [key] in the current feature's properties. */
  public fun has(key: Expression<StringValue>): Expression<BooleanValue> =
    FunctionCall.of("has", key).cast()

  /**
   * Gets the feature properties object. Note that in some cases, it may be more efficient to use
   * [get]`("property_name")` directly.
   */
  public fun properties(): Expression<MapValue<*>> = FunctionCall.of("properties").cast()

  /**
   * **Note: Not supported on native platforms. See
   * [maplibre-native#1698](https://github.com/maplibre/maplibre-native/issues/1698)**
   *
   * Retrieves a property value from the current feature's state. Returns `null` if the requested
   * property is not present on the feature's state.
   *
   * A feature's state is not part of the GeoJSON or vector tile data, and must be set
   * programmatically on each feature.
   *
   * When `source.promoteId` is not provided, features are identified by their `id` attribute, which
   * must be an integer or a string that can be cast to an integer. When `source.promoteId` is
   * provided, features are identified by their `promoteId` property, which may be a number, string,
   * or any primitive data type. Note that [state] can only be used with layer properties that
   * support data-driven styling.
   */
  // TODO: document which layer properties support feature state expressions on which platforms
  public fun <T : ExpressionValue> state(key: Expression<StringValue>): Expression<T> =
    FunctionCall.of("feature-state", key).cast()

  /** Gets the feature's geometry type. */
  public fun type(): Expression<GeometryType> = FunctionCall.of("geometry-type").cast()

  /** Gets the feature's id, if it has one. */
  public fun <T : ExpressionValue> id(): Expression<T> = FunctionCall.of("id").cast()

  /**
   * Gets the progress along a gradient line. Can only be used in the `gradient` property of a line
   * layer, see [LineLayer][dev.sargunv.maplibrecompose.compose.layer.LineLayer].
   */
  public fun lineProgress(value: Expression<FloatValue>): Expression<FloatValue> =
    FunctionCall.of("line-progress", value).cast()

  /**
   * Gets the value of a cluster property accumulated so far. Can only be used in the
   * `clusterProperties` option of a clustered GeoJSON source, see
   * [GeoJsonOptions][dev.sargunv.maplibrecompose.core.source.GeoJsonOptions].
   */
  public fun <T : ExpressionValue> accumulated(key: Expression<StringValue>): Expression<T> =
    FunctionCall.of("accumulated", key).cast()
}

/** Accesses to feature-related data */
public val feature: Feature = Feature
