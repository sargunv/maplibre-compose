package dev.sargunv.maplibrecompose.expression.ast

import dev.sargunv.maplibrecompose.expression.ExpressionContext
import dev.sargunv.maplibrecompose.expression.value.ExpressionValue

/** An [Expression] representing a constant literal value. */
public sealed interface Literal<out T : ExpressionValue, out L : Any?> : Expression<T> {

  public val value: L

  override fun compile(context: ExpressionContext): CompiledLiteral<T, *>

  @Suppress("UNCHECKED_CAST")
  override fun <X : ExpressionValue> cast(): Literal<X, L> = this as Literal<X, L>
}
