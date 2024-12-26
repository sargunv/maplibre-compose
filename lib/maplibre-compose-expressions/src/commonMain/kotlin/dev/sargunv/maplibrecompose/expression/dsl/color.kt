package dev.sargunv.maplibrecompose.expression.dsl

import dev.sargunv.maplibrecompose.expression.ast.Expression
import dev.sargunv.maplibrecompose.expression.ast.FunctionCall
import dev.sargunv.maplibrecompose.expression.value.ColorValue
import dev.sargunv.maplibrecompose.expression.value.FloatValue
import dev.sargunv.maplibrecompose.expression.value.IntValue
import dev.sargunv.maplibrecompose.expression.value.VectorValue

/**
 * Returns a four-element list, containing the color's red, green, blue, and alpha components, in
 * that order.
 */
public fun Expression<ColorValue>.toRgbaComponents(): Expression<VectorValue<Number>> =
  FunctionCall.of("to-rgba", this).cast()

/**
 * Creates a color value from [red], [green], and [blue] components, which must range between 0 and
 * 255, and optionally an [alpha] component which must range between 0 and 1.
 *
 * If any component is out of range, the expression is an error.
 */
public fun rgbColor(
  red: Expression<IntValue>,
  green: Expression<IntValue>,
  blue: Expression<IntValue>,
  alpha: Expression<FloatValue>? = null,
): Expression<ColorValue> =
  if (alpha != null) {
      FunctionCall.of("rgba", red, green, blue, alpha)
    } else {
      FunctionCall.of("rgb", red, green, blue)
    }
    .cast()
