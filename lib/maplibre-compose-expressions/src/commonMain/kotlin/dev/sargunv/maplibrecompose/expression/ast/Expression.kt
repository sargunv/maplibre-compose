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
