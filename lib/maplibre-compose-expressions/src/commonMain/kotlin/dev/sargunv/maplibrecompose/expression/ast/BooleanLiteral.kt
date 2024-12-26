package dev.sargunv.maplibrecompose.expression.ast

import dev.sargunv.maplibrecompose.expression.value.BooleanValue

/** A [Literal] representing a [Boolean] value. */
public data class BooleanLiteral private constructor(override val value: Boolean) :
  CompiledLiteral<BooleanValue, Boolean> {
  override fun visit(block: (Expression<*>) -> Unit): Unit = block(this)

  public companion object {
    private val True: BooleanLiteral = BooleanLiteral(true)
    private val False: BooleanLiteral = BooleanLiteral(false)

    public fun of(value: Boolean): BooleanLiteral = if (value) True else False
  }
}
