package dev.sargunv.maplibrecompose.expressions.ast

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import dev.sargunv.maplibrecompose.expressions.ExpressionContext
import dev.sargunv.maplibrecompose.expressions.dsl.times
import dev.sargunv.maplibrecompose.expressions.value.FloatValue
import dev.sargunv.maplibrecompose.expressions.value.TextUnitValue

/**
 * An [Expression] representing a [TextUnit] value in EM or SP, which may be transformed into
 * multiplication function call to convert to the needed units upon compilation.
 */
public data class TextUnitCalculation
private constructor(val value: Expression<FloatValue>, val type: TextUnitType) :
  Expression<TextUnitValue> {
  override fun compile(context: ExpressionContext): CompiledExpression<TextUnitValue> {
    val scale =
      when (type) {
        TextUnitType.Sp -> context.spScale
        TextUnitType.Em -> context.emScale
        else -> error("Unrecognized TextUnitType: $type")
      }
    return (value * scale).compile(context).cast()
  }

  override fun visit(block: (Expression<*>) -> Unit) {
    block(this)
    value.visit(block)
  }

  public companion object {
    public fun of(value: TextUnit): TextUnitCalculation {
      require(value.type != TextUnitType.Unspecified) { "TextUnit type must be specified" }
      return TextUnitCalculation(FloatLiteral.of(value.value), value.type)
    }

    public fun of(value: Expression<FloatValue>, type: TextUnitType): TextUnitCalculation {
      require(type != TextUnitType.Unspecified) { "TextUnit type must be specified" }
      return TextUnitCalculation(value, type)
    }
  }
}
