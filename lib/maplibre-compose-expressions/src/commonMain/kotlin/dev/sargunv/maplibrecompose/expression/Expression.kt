package dev.sargunv.maplibrecompose.expression

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import dev.sargunv.maplibrecompose.expression.ExpressionsDsl.interpolate
import dev.sargunv.maplibrecompose.expression.ExpressionsDsl.linear
import dev.sargunv.maplibrecompose.expression.ExpressionsDsl.offset
import dev.sargunv.maplibrecompose.expression.ExpressionsDsl.times
import dev.sargunv.maplibrecompose.expression.ast.FloatCache
import dev.sargunv.maplibrecompose.expression.ast.IntCache
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

// TODO move to package ast

// region super types

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

// endregion

// region compiled types

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

// endregion

// region high level types

/** A [Literal] representing an [Int] value. */
public data class IntLiteral private constructor(override val value: Int) : Literal<IntValue, Int> {

  override fun compile(context: ExpressionContext): CompiledLiteral<IntValue, Float> =
    FloatLiteral.of(value.toFloat()).cast()

  override fun visit(block: (Expression<*>) -> Unit): Unit = block(this)

  public companion object {
    private val cache = IntCache(::IntLiteral)

    public fun of(value: Int): IntLiteral = cache[value]
  }
}

/** A [Literal] representing a [Dp] value. */
public data class DpLiteral private constructor(override val value: Dp) : Literal<DpValue, Dp> {

  override fun compile(context: ExpressionContext): CompiledLiteral<DpValue, Float> =
    FloatLiteral.of(value.value).cast()

  override fun visit(block: (Expression<*>) -> Unit): Unit = block(this)

  public companion object {
    private val cache = FloatCache { DpLiteral(it.dp) }

    public fun of(value: Dp): DpLiteral = cache[value.value]
  }
}

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

/** A [Literal] representing an enum value of type [T]. */
public data class EnumLiteral<T : EnumValue<T>> private constructor(override val value: T) :
  Literal<T, T> {
  override fun compile(context: ExpressionContext): CompiledLiteral<T, String> =
    value.stringConst.cast()

  override fun visit(block: (Expression<*>) -> Unit): Unit = block(this)

  public companion object {
    internal fun <T : EnumValue<T>> of(value: T): EnumLiteral<T> = EnumLiteral(value)
  }
}

/** A [Literal] representing a JSON array. */
public data class ListLiteral<T : ExpressionValue>
private constructor(override val value: List<Literal<T, *>>) :
  Literal<ListValue<T>, List<Literal<T, *>>> {

  override fun compile(context: ExpressionContext): CompiledListLiteral<T> =
    CompiledListLiteral.of(value.map { it.compile(context) })

  override fun visit(block: (Expression<*>) -> Unit) {
    block(this)
    value.forEach { it.visit(block) }
  }

  public companion object {
    internal fun <T : ExpressionValue> of(value: List<Literal<T, *>>): ListLiteral<T> =
      ListLiteral(value)
  }
}

/** A [Literal] representing a JSON object. */
public data class MapLiteral<T : ExpressionValue>
private constructor(override val value: Map<String, Literal<T, *>>) :
  Literal<MapValue<T>, Map<String, Literal<T, *>>> {

  override fun compile(context: ExpressionContext): CompiledMapLiteral<T> {
    return CompiledMapLiteral.of(value.mapValues { it.value.compile(context) })
  }

  override fun visit(block: (Expression<*>) -> Unit) {
    block(this)
    value.values.forEach { it.visit(block) }
  }

  public companion object {
    internal fun <T : ExpressionValue> of(value: Map<String, Literal<T, *>>): MapLiteral<T> =
      MapLiteral(value)
  }
}

/** A [Literal] representing a [DpOffset] value. */
public data class DpOffsetLiteral private constructor(override val value: DpOffset) :
  Literal<DpOffsetValue, DpOffset> {
  override fun compile(context: ExpressionContext): CompiledLiteral<DpOffsetValue, Offset> =
    OffsetLiteral.of(Offset(value.x.value, value.y.value)).cast()

  override fun visit(block: (Expression<*>) -> Unit): Unit = block(this)

  public companion object {
    private val zero = DpOffsetLiteral(DpOffset.Zero)

    public fun of(value: DpOffset): DpOffsetLiteral =
      if (value == DpOffset.Zero) zero else DpOffsetLiteral(value)
  }
}

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

/**
 * A [Literal] representing a [Painter] value, which will be drawn to a bitmap and loaded as an
 * image into the style upon compilation.
 */
public data class PainterLiteral private constructor(override val value: Painter) :
  Literal<StringValue, Painter> {
  override fun compile(context: ExpressionContext): StringLiteral =
    StringLiteral.of(context.resolvePainter(value))

  override fun visit(block: (Expression<*>) -> Unit): Unit = block(this)

  public companion object {
    public fun of(value: Painter): PainterLiteral = PainterLiteral(value)
  }
}

/**
 * An [Expression] representing a [TextUnit] value in EM or SP, which may be transformed into
 * multiplication function call to convert to the needed units upon compilation.
 */
public data class TextUnitExpression
private constructor(val value: Expression<FloatValue>, val type: TextUnitType) :
  Expression<TextUnitValue> {
  override fun compile(context: ExpressionContext): CompiledExpression<TextUnitValue> {
    val scale =
      when (type) {
        TextUnitType.Sp -> context.spScale
        TextUnitType.Em -> context.emScale
        else -> error("Unrecognized TextUnitType: $type")
      }
    return (value * scale).compile(context).cast()
  }

  override fun visit(block: (Expression<*>) -> Unit) {
    block(this)
    value.visit(block)
  }

  public companion object {
    public fun of(value: TextUnit): TextUnitExpression {
      require(value.type != TextUnitType.Unspecified) { "TextUnit type must be specified" }
      return TextUnitExpression(FloatLiteral.of(value.value), value.type)
    }

    public fun of(value: Expression<FloatValue>, type: TextUnitType): TextUnitExpression {
      require(type != TextUnitType.Unspecified) { "TextUnit type must be specified" }
      return TextUnitExpression(value, type)
    }
  }
}

/**
 * An [Expression] representing a [TextUnitOffsetValue] in EM or SP, which may be transformed into
 * an interpolation function call to convert to the needed units upon compilation.
 */
public data class TextUnitOffsetExpression private constructor(val x: TextUnit, val y: TextUnit) :
  Expression<TextUnitOffsetValue> {
  override fun compile(context: ExpressionContext): CompiledExpression<TextUnitOffsetValue> {
    val scale =
      when (x.type) {
        TextUnitType.Sp -> context.spScale
        TextUnitType.Em -> context.emScale
        else -> error("Unrecognized TextUnitType: ${x.type}")
      }

    // some reasonably large number to bound the interpolation
    val maxScale = 1000f

    return interpolate(
        type = linear(),
        input = scale,
        0f to offset(0f, 0f),
        1f to offset(x.value, y.value),
        maxScale to offset(x.value * maxScale, y.value * maxScale),
      )
      .compile(context)
      .cast()
  }

  override fun visit(block: (Expression<*>) -> Unit): Unit = block(this)

  public companion object {
    public fun of(x: TextUnit, y: TextUnit): TextUnitOffsetExpression {
      require(x.isSpecified && y.isSpecified) { "TextUnit type must be specified" }
      require(x.type == y.type) { "X and Y text units must have the same type" }
      return TextUnitOffsetExpression(x, y)
    }
  }
}

/** An [Expression] representing a function call. */
public data class FunctionCall
private constructor(
  val name: String,
  val args: List<Expression<*>>,
  val isLiteralArg: (Int) -> Boolean,
) : Expression<ExpressionValue> {
  override fun compile(context: ExpressionContext): CompiledExpression<ExpressionValue> =
    CompiledFunctionCall.of(name, args.map { it.compile(context) }, isLiteralArg)

  override fun visit(block: (Expression<*>) -> Unit) {
    block(this)
    args.forEach { it.visit(block) }
  }

  public companion object {
    public fun of(
      name: String,
      vararg args: Expression<*>,
      isLiteralArg: (Int) -> Boolean = { false },
    ): FunctionCall = FunctionCall(name, args.asList(), isLiteralArg)
  }
}

/** An [Expression] representing a JSON object with values all [Expression]. */
public data class Options<T : ExpressionValue>
private constructor(val value: Map<String, Expression<T>>) : Expression<MapValue<T>> {

  override fun compile(context: ExpressionContext): CompiledOptions<T> =
    CompiledOptions.of(value.mapValues { it.value.compile(context) })

  override fun visit(block: (Expression<*>) -> Unit) {
    block(this)
    value.values.forEach { it.visit(block) }
  }

  public companion object {
    internal fun build(block: MutableMap<String, Expression<*>>.() -> Unit) =
      Options(mutableMapOf<String, Expression<*>>().apply(block).mapValues { it.value })
  }
}

// endregion
