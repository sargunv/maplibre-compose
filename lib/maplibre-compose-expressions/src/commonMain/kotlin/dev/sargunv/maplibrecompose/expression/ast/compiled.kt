package dev.sargunv.maplibrecompose.expression.ast

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import dev.sargunv.maplibrecompose.expression.ZeroPadding
import dev.sargunv.maplibrecompose.expression.value.BooleanValue
import dev.sargunv.maplibrecompose.expression.value.ColorValue
import dev.sargunv.maplibrecompose.expression.value.DpPaddingValue
import dev.sargunv.maplibrecompose.expression.value.ExpressionValue
import dev.sargunv.maplibrecompose.expression.value.FloatOffsetValue
import dev.sargunv.maplibrecompose.expression.value.FloatValue
import dev.sargunv.maplibrecompose.expression.value.ListValue
import dev.sargunv.maplibrecompose.expression.value.MapValue
import dev.sargunv.maplibrecompose.expression.value.StringValue

/** A [Literal] representing a `null` value. */
public data object NullLiteral : CompiledLiteral<Nothing, Nothing?> {
  override val value: Nothing? = null

  override fun visit(block: (Expression<*>) -> Unit): Unit = block(this)
}

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

/** A [Literal] representing a [Number] value. */
public data class FloatLiteral private constructor(override val value: Float) :
  CompiledLiteral<FloatValue, Float> {
  override fun visit(block: (Expression<*>) -> Unit): Unit = block(this)

  public companion object {
    private val cache = FloatCache(::FloatLiteral)

    public fun of(value: Float): FloatLiteral = cache[value]
  }
}

/** A [Literal] representing a [String] value. */
public data class StringLiteral private constructor(override val value: String) :
  CompiledLiteral<StringValue, String> {
  override fun visit(block: (Expression<*>) -> Unit): Unit = block(this)

  public companion object {
    private val empty = StringLiteral("")

    public fun of(value: String): StringLiteral =
      if (value.isEmpty()) empty else StringLiteral(value)
  }
}

/** A [Literal] representing a [Color] value. */
public data class ColorLiteral private constructor(override val value: Color) :
  CompiledLiteral<ColorValue, Color> {
  override fun visit(block: (Expression<*>) -> Unit): Unit = block(this)

  public companion object {
    private val black = ColorLiteral(Color.Black)
    private val white = ColorLiteral(Color.White)
    private val transparent = ColorLiteral(Color.Transparent)

    public fun of(value: Color): ColorLiteral =
      when (value) {
        Color.Black -> black
        Color.White -> white
        Color.Transparent -> transparent
        else -> ColorLiteral(value)
      }
  }
}

/** A [Literal] representing a JSON array with elements all [CompiledLiteral]. */
public data class CompiledListLiteral<T : ExpressionValue>
private constructor(override val value: List<CompiledLiteral<T, *>>) :
  CompiledLiteral<ListValue<T>, List<Literal<T, *>>> {
  override fun visit(block: (Expression<*>) -> Unit) {
    block(this)
    value.forEach { it.visit(block) }
  }

  public companion object {
    internal fun <T : ExpressionValue> of(
      value: List<CompiledLiteral<T, *>>
    ): CompiledListLiteral<T> = CompiledListLiteral(value)
  }
}

/** A [Literal] representing a JSON object with values all [CompiledLiteral]. */
public data class CompiledMapLiteral<V : ExpressionValue>
private constructor(override val value: Map<String, CompiledLiteral<V, *>>) :
  CompiledLiteral<MapValue<V>, Map<String, Literal<V, *>>> {
  override fun visit(block: (Expression<*>) -> Unit) {
    block(this)
    value.values.forEach { it.visit(block) }
  }

  public companion object {
    internal fun <T : ExpressionValue> of(
      value: Map<String, CompiledLiteral<T, *>>
    ): CompiledMapLiteral<T> = CompiledMapLiteral(value)
  }
}

/** A [Literal] representing a [Offset] value. */
public data class OffsetLiteral private constructor(override val value: Offset) :
  CompiledLiteral<FloatOffsetValue, Offset> {
  override fun visit(block: (Expression<*>) -> Unit): Unit = block(this)

  public companion object {
    private val zero = OffsetLiteral(Offset.Zero)

    public fun of(value: Offset): OffsetLiteral =
      if (value == Offset.Zero) zero else OffsetLiteral(value)
  }
}

/** A [Literal] representing a [PaddingValues] value. */
public data class DpPaddingLiteral private constructor(override val value: PaddingValues.Absolute) :
  CompiledLiteral<DpPaddingValue, PaddingValues.Absolute> {
  override fun visit(block: (Expression<*>) -> Unit): Unit = block(this)

  public companion object {
    private val zero = DpPaddingLiteral(ZeroPadding)

    public fun of(value: PaddingValues.Absolute): DpPaddingLiteral =
      if (value == ZeroPadding) zero else DpPaddingLiteral(value)
  }
}

/** A [Literal] representing an function call with args all of [CompiledExpression] */
public data class CompiledFunctionCall
private constructor(
  val name: String,
  val args: List<CompiledExpression<*>>,
  val isLiteralArg: (Int) -> Boolean,
) : CompiledExpression<ExpressionValue> {
  override fun visit(block: (Expression<*>) -> Unit) {
    block(this)
    args.forEach { it.visit(block) }
  }

  public companion object {
    public fun of(
      name: String,
      args: List<CompiledExpression<*>>,
      isLiteralArg: (Int) -> Boolean = { false },
    ): CompiledFunctionCall = CompiledFunctionCall(name, args, isLiteralArg)
  }
}

/** An [Expression] representing a JSON object with values all [CompiledExpression]. */
public data class CompiledOptions<T : ExpressionValue>
private constructor(val value: Map<String, CompiledExpression<T>>) :
  CompiledExpression<MapValue<T>> {
  override fun visit(block: (Expression<*>) -> Unit) {
    block(this)
    value.values.forEach { it.visit(block) }
  }

  public companion object {
    internal fun <T : ExpressionValue> of(
      value: Map<String, CompiledExpression<T>>
    ): CompiledOptions<T> = CompiledOptions(value)
  }
}
