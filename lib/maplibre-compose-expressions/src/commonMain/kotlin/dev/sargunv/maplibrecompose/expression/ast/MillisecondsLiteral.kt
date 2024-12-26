package dev.sargunv.maplibrecompose.expression.ast

import dev.sargunv.maplibrecompose.expression.ExpressionContext
import dev.sargunv.maplibrecompose.expression.value.MillisecondsValue
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

/** A [Literal] representing a [Duration] value. */
public data class MillisecondsLiteral private constructor(override val value: Duration) :
  Literal<MillisecondsValue, Duration> {

  override fun compile(context: ExpressionContext): CompiledLiteral<MillisecondsValue, Float> =
    FloatLiteral.of(value.inWholeMilliseconds.toFloat()).cast()

  override fun visit(block: (Expression<*>) -> Unit): Unit = block(this)

  public companion object {
    private val cache = IntCache { MillisecondsLiteral(it.milliseconds) }

    public fun of(value: Duration): MillisecondsLiteral = cache[value.inWholeMilliseconds.toInt()]
  }
}
