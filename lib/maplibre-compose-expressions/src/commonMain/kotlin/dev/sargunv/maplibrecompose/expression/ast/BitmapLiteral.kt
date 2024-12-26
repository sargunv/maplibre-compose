package dev.sargunv.maplibrecompose.expression.ast

import androidx.compose.ui.graphics.ImageBitmap
import dev.sargunv.maplibrecompose.expression.ExpressionContext
import dev.sargunv.maplibrecompose.expression.value.StringValue

/**
 * A [Literal] representing an [ImageBitmap] value, which will be loaded as an image into the style
 * upon compilation.
 */
public data class BitmapLiteral private constructor(override val value: ImageBitmap) :
  Literal<StringValue, ImageBitmap> {
  override fun compile(context: ExpressionContext): StringLiteral =
    StringLiteral.of(context.resolveBitmap(value))

  override fun visit(block: (Expression<*>) -> Unit): Unit = block(this)

  public companion object {
    public fun of(value: ImageBitmap): BitmapLiteral = BitmapLiteral(value)
  }
}
