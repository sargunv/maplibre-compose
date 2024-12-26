package dev.sargunv.maplibrecompose.expression.ast

import dev.sargunv.maplibrecompose.expression.ExpressionContext
import dev.sargunv.maplibrecompose.expression.value.ExpressionValue

/** An [Expression] that evaluates to a value of type [T]. */
public sealed interface Expression<out T : ExpressionValue> {
  /** Transform this expression into the equivalent [CompiledExpression]. */
  public fun compile(context: ExpressionContext): CompiledExpression<T>

  public fun visit(block: (Expression<*>) -> Unit)

  @Suppress("UNCHECKED_CAST")
  public fun <X : ExpressionValue> cast(): Expression<X> = this as Expression<X>
}

/**
 * An [Expression] reduced to only those data types supported by the MapLibre SDKs. This can be
 * thought of as an intermediate representation between the high level expression DSL and the
 * platform-specific encoding.
 */
public sealed interface CompiledExpression<out T : ExpressionValue> : Expression<T> {
  override fun compile(context: ExpressionContext): CompiledExpression<T> = this

  @Suppress("UNCHECKED_CAST")
  override fun <X : ExpressionValue> cast(): CompiledExpression<X> = this as CompiledExpression<X>
}

/** An [Expression] representing a constant literal value. */
public sealed interface Literal<out T : ExpressionValue, out L : Any?> : Expression<T> {

  public val value: L

  override fun compile(context: ExpressionContext): CompiledLiteral<T, *>

  @Suppress("UNCHECKED_CAST")
  override fun <X : ExpressionValue> cast(): Literal<X, L> = this as Literal<X, L>
}

/** An [Expression] representing a constant literal value of a type supported by MapLibre. */
public sealed interface CompiledLiteral<out T : ExpressionValue, out L : Any?> :
  Literal<T, L>, CompiledExpression<T> {

  override fun compile(context: ExpressionContext): CompiledLiteral<T, L> = this

  @Suppress("UNCHECKED_CAST")
  override fun <X : ExpressionValue> cast(): CompiledLiteral<X, L> = this as CompiledLiteral<X, L>
}
