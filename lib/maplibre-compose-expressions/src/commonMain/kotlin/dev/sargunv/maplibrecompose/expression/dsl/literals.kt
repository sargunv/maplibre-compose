package dev.sargunv.maplibrecompose.expression.dsl

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import dev.sargunv.maplibrecompose.expression.ast.BooleanLiteral
import dev.sargunv.maplibrecompose.expression.ast.ColorLiteral
import dev.sargunv.maplibrecompose.expression.ast.DpLiteral
import dev.sargunv.maplibrecompose.expression.ast.DpOffsetLiteral
import dev.sargunv.maplibrecompose.expression.ast.DpPaddingLiteral
import dev.sargunv.maplibrecompose.expression.ast.EnumLiteral
import dev.sargunv.maplibrecompose.expression.ast.Expression
import dev.sargunv.maplibrecompose.expression.ast.FloatLiteral
import dev.sargunv.maplibrecompose.expression.ast.IntLiteral
import dev.sargunv.maplibrecompose.expression.ast.ListLiteral
import dev.sargunv.maplibrecompose.expression.ast.Literal
import dev.sargunv.maplibrecompose.expression.ast.MillisecondsLiteral
import dev.sargunv.maplibrecompose.expression.ast.NullLiteral
import dev.sargunv.maplibrecompose.expression.ast.OffsetLiteral
import dev.sargunv.maplibrecompose.expression.ast.StringLiteral
import dev.sargunv.maplibrecompose.expression.ast.TextUnitExpression
import dev.sargunv.maplibrecompose.expression.ast.TextUnitOffsetExpression
import dev.sargunv.maplibrecompose.expression.value.EnumValue
import dev.sargunv.maplibrecompose.expression.value.ExpressionValue
import dev.sargunv.maplibrecompose.expression.value.StringValue
import dev.sargunv.maplibrecompose.expression.value.SymbolAnchor
import dev.sargunv.maplibrecompose.expression.value.TextUnitOffsetValue
import dev.sargunv.maplibrecompose.expression.value.TextVariableAnchorOffsetValue
import dev.sargunv.maplibrecompose.expression.value.VectorValue
import kotlin.jvm.JvmName
import kotlin.time.Duration

/** Creates a literal expression for a [String] value. */
public fun const(string: String): StringLiteral = StringLiteral.of(string)

/** Creates a literal expression for an enum value implementing [EnumValue]. */
public fun <T : EnumValue<T>> const(value: T): EnumLiteral<T> = EnumLiteral.of(value)

/** Creates a literal expression for a dimensionless [Float] value. */
public fun const(float: Float): FloatLiteral = FloatLiteral.of(float)

/** Creates a literal expression for an dimensionless [Int] value. */
public fun const(int: Int): IntLiteral = IntLiteral.of(int)

/** Creates a literal expression for a [Dp] value. */
public fun const(dp: Dp): DpLiteral = DpLiteral.of(dp)

/**
 * Creates a literal expression for a specified [TextUnit] value in SP or EM. It can be provided in
 * either unit, and will resolve to one at runtime depending on the property it is used in.
 */
public fun const(textUnit: TextUnit): TextUnitExpression = TextUnitExpression.of(textUnit)

/**
 * Creates a literal expression for a [Duration] value.
 *
 * The duration will be rounded to the nearest whole milliseconds.
 */
public fun const(duration: Duration): MillisecondsLiteral = MillisecondsLiteral.of(duration)

/** Creates a literal expression for a [Boolean] value. */
public fun const(bool: Boolean): BooleanLiteral = BooleanLiteral.of(bool)

/** Creates a literal expression for a [Color] value. */
public fun const(color: Color): ColorLiteral = ColorLiteral.of(color)

/** Creates a literal expression for an [Offset] value. */
public fun const(offset: Offset): OffsetLiteral = OffsetLiteral.of(offset)

/** Creates a literal expression for a [DpOffset] value. */
public fun const(dpOffset: DpOffset): DpOffsetLiteral = DpOffsetLiteral.of(dpOffset)

/** Creates a literal expression for a [PaddingValues.Absolute] value. */
public fun const(padding: PaddingValues.Absolute): DpPaddingLiteral = DpPaddingLiteral.of(padding)

/** Creates a literal expression for a list. */
public fun <T : ExpressionValue> const(list: List<Literal<T, *>>): ListLiteral<T> =
  ListLiteral.of(list)

/** Creates a literal expression for a list of strings. */
@JvmName("constStringList")
public fun const(list: List<String>): ListLiteral<StringValue> = const(list.map { const(it) })

/** Creates a literal expression for a list of numbers. */
@JvmName("constNumberList")
public fun const(list: List<Number>): Literal<VectorValue<Number>, *> =
  const(list.map { const(it.toFloat()) }).cast()

/**
 * Creates a literal expression for [TextVariableAnchorOffsetValue], used by
 * [SymbolLayer][dev.sargunv.maplibrecompose.compose.layer.SymbolLayer]'s `textVariableAnchorOffset`
 * parameter.
 *
 * The offset is measured in a multipler of the text size (EM). It's in [Offset] instead of [offset]
 * because of technical limitations in MapLibre.
 */
public fun textVariableAnchorOffset(
  vararg pairs: Pair<SymbolAnchor, Offset>
): Literal<TextVariableAnchorOffsetValue, List<*>> {
  val elements = buildList {
    pairs.forEach { (anchor, offset) ->
      add(anchor.literal)
      add(const(offset))
    }
  }
  return const(elements).cast()
}

/** Creates a literal expression for a 2D [Offset]. */
public fun offset(x: Float, y: Float): OffsetLiteral = OffsetLiteral.of(Offset(x, y))

/** Creates a literal expression for a 2D [DpOffset]. */
public fun offset(x: Dp, y: Dp): DpOffsetLiteral = DpOffsetLiteral.of(DpOffset(x, y))

/**
 * Creates a literal expression for a 2D [TextUnit] offset.
 *
 * Both [x] and [y] must have the same [TextUnitType].
 */
public fun offset(x: TextUnit, y: TextUnit): Expression<TextUnitOffsetValue> =
  TextUnitOffsetExpression.of(x, y)

/**
 * Creates a literal expression for a `null` value.
 *
 * For simplicity, the expression type system does not encode nullability, so the return value of
 * this function is assignable to any kind of expression.
 */
public fun <T : ExpressionValue> nil(): Expression<T> = NullLiteral.cast()
