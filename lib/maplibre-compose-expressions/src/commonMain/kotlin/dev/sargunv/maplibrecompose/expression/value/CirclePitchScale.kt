package dev.sargunv.maplibrecompose.expression.value

import dev.sargunv.maplibrecompose.expression.ast.StringLiteral

/** Scaling behavior of circles when the map is pitched. */
public enum class CirclePitchScale(override val literal: StringLiteral) :
  EnumValue<CirclePitchScale> {
  /**
   * Circles are scaled according to their apparent distance to the camera, i.e. as if they are on
   * the map.
   */
  Map(StringLiteral.of("map")),

  /** Circles are not scaled, i.e. as if glued to the viewport. */
  Viewport(StringLiteral.of("viewport")),
}
