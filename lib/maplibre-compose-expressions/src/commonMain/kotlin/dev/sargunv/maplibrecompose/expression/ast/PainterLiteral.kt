package dev.sargunv.maplibrecompose.expression.ast

import androidx.compose.ui.graphics.painter.Painter
import dev.sargunv.maplibrecompose.expression.ExpressionContext
import dev.sargunv.maplibrecompose.expression.value.StringValue

/**
 * A [Literal] representing a [Painter] value, which will be drawn to a bitmap and loaded as an
 * image into the style upon compilation.
 */
public data class PainterLiteral private constructor(override val value: Painter) :
  Literal<StringValue, Painter> {
  override fun compile(context: ExpressionContext): StringLiteral =
    StringLiteral.of(context.resolvePainter(value))

  override fun visit(block: (Expression<*>) -> Unit): Unit = block(this)

  public companion object {
    public fun of(value: Painter): PainterLiteral = PainterLiteral(value)
  }
}
