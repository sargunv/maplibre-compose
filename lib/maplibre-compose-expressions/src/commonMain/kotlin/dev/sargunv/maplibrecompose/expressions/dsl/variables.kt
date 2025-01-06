package dev.sargunv.maplibrecompose.expressions.dsl

import dev.sargunv.maplibrecompose.expressions.ast.Expression
import dev.sargunv.maplibrecompose.expressions.ast.FunctionCall
import dev.sargunv.maplibrecompose.expressions.value.ExpressionValue
import kotlin.jvm.JvmInline

/**
 * Binds expression [value] to a [Variable] with the given [name], which can then be referenced
 * inside the block using [use]. For example:
 * ```kt
 * val result = withVariable("x", const(5)) { x ->
 *   x.use() + const(3)
 * }
 * ```
 */
public inline fun <V : ExpressionValue, R : ExpressionValue> withVariable(
  name: String,
  value: Expression<V>,
  block: (Variable<V>) -> Expression<R>,
): Expression<R> {
  return Variable<V>(name).let { it.bind(value, block(it)) }
}

/** References a [Variable] bound in [withVariable]. */
public fun <T : ExpressionValue> Variable<T>.use(): Expression<T> =
  FunctionCall.of("var", const(name)).cast()

/** Represents a variable bound with [withVariable]. Reference the bound expression with [use]. */
@JvmInline
public value class Variable<@Suppress("unused") T : ExpressionValue>
@PublishedApi
internal constructor(public val name: String)

@PublishedApi
internal fun <V : ExpressionValue, T : ExpressionValue> Variable<V>.bind(
  value: Expression<V>,
  expression: Expression<T>,
): Expression<T> = FunctionCall.of("let", const(name), value, expression).cast()
