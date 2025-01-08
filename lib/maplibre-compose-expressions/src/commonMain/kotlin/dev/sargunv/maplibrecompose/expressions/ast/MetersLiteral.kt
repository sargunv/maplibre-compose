package dev.sargunv.maplibrecompose.expressions.ast

import dev.sargunv.maplibrecompose.expressions.ExpressionContext
import dev.sargunv.maplibrecompose.expressions.value.MetersValue
import io.github.kevincianfarini.alchemist.scalar.toLength
import io.github.kevincianfarini.alchemist.type.Length
import io.github.kevincianfarini.alchemist.unit.LengthUnit.International.Meter

/** A [Literal] representing a [Length] value. */
public data class MetersLiteral private constructor(override val value: Length) :
  Literal<MetersValue, Length> {

  override fun compile(context: ExpressionContext): CompiledLiteral<MetersValue, Float> =
    FloatLiteral.of(value.toDouble(Meter).toFloat()).cast()

  override fun visit(block: (Expression<*>) -> Unit): Unit = block(this)

  public companion object {
    private val cache = FloatCache { MetersLiteral(it.toDouble().toLength(Meter)) }

    public fun of(value: Length): MetersLiteral = cache[value.toDouble(Meter).toFloat()]
  }
}
