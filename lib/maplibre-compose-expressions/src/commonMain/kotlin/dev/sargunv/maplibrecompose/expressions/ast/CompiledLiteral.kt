package dev.sargunv.maplibrecompose.expressions.ast

import dev.sargunv.maplibrecompose.expressions.ExpressionContext
import dev.sargunv.maplibrecompose.expressions.value.ExpressionValue

/** An [Expression] representing a constant literal value of a type supported by MapLibre. */
public sealed interface CompiledLiteral<out T : ExpressionValue, out L : Any?> :
  Literal<T, L>, CompiledExpression<T> {

  override fun compile(context: ExpressionContext): CompiledLiteral<T, L> = this

  @Suppress("UNCHECKED_CAST")
  override fun <X : ExpressionValue> cast(): CompiledLiteral<X, L> = this as CompiledLiteral<X, L>
}
