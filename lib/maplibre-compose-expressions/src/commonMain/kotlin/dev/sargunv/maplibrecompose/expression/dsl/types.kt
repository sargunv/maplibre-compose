package dev.sargunv.maplibrecompose.expression.dsl

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.TextUnitType
import dev.sargunv.maplibrecompose.expression.ast.BitmapLiteral
import dev.sargunv.maplibrecompose.expression.ast.Expression
import dev.sargunv.maplibrecompose.expression.ast.FunctionCall
import dev.sargunv.maplibrecompose.expression.ast.Options
import dev.sargunv.maplibrecompose.expression.ast.PainterLiteral
import dev.sargunv.maplibrecompose.expression.ast.TextUnitCalculation
import dev.sargunv.maplibrecompose.expression.value.BooleanValue
import dev.sargunv.maplibrecompose.expression.value.CollatorValue
import dev.sargunv.maplibrecompose.expression.value.ColorValue
import dev.sargunv.maplibrecompose.expression.value.DpOffsetValue
import dev.sargunv.maplibrecompose.expression.value.DpPaddingValue
import dev.sargunv.maplibrecompose.expression.value.DpValue
import dev.sargunv.maplibrecompose.expression.value.EnumValue
import dev.sargunv.maplibrecompose.expression.value.ExpressionType
import dev.sargunv.maplibrecompose.expression.value.FloatOffsetValue
import dev.sargunv.maplibrecompose.expression.value.FloatValue
import dev.sargunv.maplibrecompose.expression.value.FormattableValue
import dev.sargunv.maplibrecompose.expression.value.FormattedValue
import dev.sargunv.maplibrecompose.expression.value.ImageValue
import dev.sargunv.maplibrecompose.expression.value.IntValue
import dev.sargunv.maplibrecompose.expression.value.ListValue
import dev.sargunv.maplibrecompose.expression.value.MapValue
import dev.sargunv.maplibrecompose.expression.value.MillisecondsValue
import dev.sargunv.maplibrecompose.expression.value.NumberValue
import dev.sargunv.maplibrecompose.expression.value.StringValue
import dev.sargunv.maplibrecompose.expression.value.TextUnitValue
import kotlin.enums.enumEntries

/** Returns a string describing the type of this expression. */
public fun Expression<*>.type(): Expression<ExpressionType> = FunctionCall.of("typeof", this).cast()

/**
 * Asserts that this is a list (optionally with a specific item [type] and [length]).
 *
 * If, when the input expression is evaluated, it is not of the asserted type, then this assertion
 * will cause the whole expression to be aborted.
 */
public fun Expression<*>.asList(
  type: Expression<ExpressionType> = nil(),
  length: Expression<IntValue> = nil(),
): Expression<ListValue<*>> = FunctionCall.of("array", this, type, length).cast()

/**
 * Asserts that this is a list of numbers, optionally with a specific [length].
 *
 * If, when the input expression is evaluated, it is not of the asserted type, then this assertion
 * will cause the whole expression to be aborted.
 */
public fun <U, V : NumberValue<U>> Expression<*>.asVector(
  length: Expression<IntValue> = nil()
): Expression<V> = asList(const(ExpressionType.Number), length).cast()

/**
 * Asserts that this is a list of numbers of length 2.
 *
 * If, when the input expression is evaluated, it is not of the asserted type, then this assertion
 * will cause the whole expression to be aborted.
 */
public fun Expression<*>.asOffset(): Expression<FloatOffsetValue> =
  asList(const(ExpressionType.Number), const(2)).cast()

/**
 * Asserts that this is a list of numbers of length 2.
 *
 * If, when the input expression is evaluated, it is not of the asserted type, then this assertion
 * will cause the whole expression to be aborted.
 */
public fun Expression<*>.asDpOffset(): Expression<DpOffsetValue> =
  asList(const(ExpressionType.Number), const(2)).cast()

/**
 * Asserts that this is a list of numbers of length 4.
 *
 * If, when the input expression is evaluated, it is not of the asserted type, then this assertion
 * will cause the whole expression to be aborted.
 */
public fun Expression<*>.asPadding(): Expression<DpPaddingValue> =
  asList(const(ExpressionType.Number), const(2)).cast()

/**
 * Asserts that this value is a string.
 *
 * In case this expression is not a string, each of the [fallbacks] is evaluated in order until a
 * string is obtained. If none of the inputs are strings, the expression is an error.
 */
public fun Expression<*>.asString(vararg fallbacks: Expression<*>): Expression<StringValue> =
  FunctionCall.of("string", this, *fallbacks).cast()

/**
 * Asserts that this value is an entry of the enum specified by [T].
 *
 * In case this expression is not an entry of the enum, each of the [fallbacks] is evaluated in
 * order until a match is obtained. If none of the inputs match, the expression is an error.
 */
public inline fun <reified T> Expression<*>.asEnum(
  vararg fallbacks: Expression<*>
): Expression<T> where T : Enum<T>, T : EnumValue<T> {
  val entries = const(enumEntries<T>().map { it.name })
  return switch(
      condition(entries.contains(this), this),
      *fallbacks.map { condition(entries.contains(it), it) }.toTypedArray(),
      fallback = nil(), // should always error .asString(), which is what we want as per kdoc
    )
    .asString()
    .cast()
}

/**
 * Asserts that this value is a number.
 *
 * In case this expression is not a number, each of the [fallbacks] is evaluated in order until a
 * number is obtained. If none of the inputs are numbers, the expression is an error.
 */
public fun Expression<*>.asNumber(vararg fallbacks: Expression<*>): Expression<FloatValue> =
  FunctionCall.of("number", this, *fallbacks).cast()

/**
 * Asserts that this value is a boolean.
 *
 * In case this expression is not a boolean, each of the [fallbacks] is evaluated in order until a
 * boolean is obtained. If none of the inputs are booleans, the expression is an error.
 */
public fun Expression<*>.asBoolean(vararg fallbacks: Expression<*>): Expression<BooleanValue> =
  FunctionCall.of("boolean", this, *fallbacks).cast()

/**
 * Asserts that this value is a map.
 *
 * In case this expression is not a map, each of the [fallbacks] is evaluated in order until a map
 * is obtained. If none of the inputs are maps, the expression is an error.
 */
public fun Expression<*>.asMap(vararg fallbacks: Expression<*>): Expression<MapValue<*>> =
  FunctionCall.of("object", this, *fallbacks).cast()

/**
 * Returns a collator for use in locale-dependent comparison operations. The [caseSensitive] and
 * [diacriticSensitive] options default to `false`. The [locale] argument specifies the IETF
 * language tag of the locale to use. If none is provided, the default locale is used. If the
 * requested locale is not available, the collator will use a system-defined fallback locale. Use
 * [resolvedLocale] to test the results of locale fallback behavior.
 */
public fun collator(
  caseSensitive: Expression<BooleanValue>? = null,
  diacriticSensitive: Expression<BooleanValue>? = null,
  locale: Expression<StringValue>? = null,
): Expression<CollatorValue> =
  FunctionCall.of(
      "collator",
      Options.build(
        fun MutableMap<String, Expression<*>>.() {
          caseSensitive?.let { put("case-sensitive", it) }
          diacriticSensitive?.let { put("diacritic-sensitive", it) }
          locale?.let { put("locale", it) }
        }
      ),
    )
    .cast()

/**
 * Returns a formatted string for displaying mixed-format text in the `textField` property (see
 * [SymbolLayer][dev.sargunv.maplibrecompose.compose.layer.SymbolLayer]). The input may contain a
 * string literal or expression, including an [image] expression.
 *
 * Example:
 * ```
 * format(
 *   span(
 *     feature.get("name").asString().substring(const(0), const(1)).uppercase(),
 *     textScale = const(1.5f),
 *   ),
 *   span(feature.get("name").asString().substring(const(1)))
 * )
 * ```
 *
 * Capitalizes the first letter of the features' property "name" and formats it to be extra-large,
 * the rest of the name is written normally.
 */
public fun format(vararg spans: FormatSpan): Expression<FormattedValue> =
  FunctionCall.of(
      "format",
      *spans.foldToArgs { span ->
        add(span.value)
        add(span.options)
      },
    )
    .cast()

/** Configures a span of text in a [format] expression. */
public fun span(
  value: Expression<StringValue>,
  textFont: Expression<StringValue>? = null,
  textColor: Expression<StringValue>? = null,
  textSize: Expression<TextUnitValue>? = null,
): FormatSpan =
  FormatSpan(value = value, textFont = textFont, textColor = textColor, textSize = textSize)

/** Configures an image in a [format] expression. */
public fun span(value: Expression<FormattableValue>): FormatSpan = FormatSpan(value = value)

/** Represents a component of a [format] expression. See [span]. */
public data class FormatSpan
internal constructor(
  val value: Expression<FormattableValue>,
  val textFont: Expression<StringValue>? = null,
  val textColor: Expression<StringValue>? = null,
  val textSize: Expression<TextUnitValue>? = null,
) {
  internal val options
    get() =
      Options.build(
        fun MutableMap<String, Expression<*>>.() {
          textFont?.let { put("text-font", it) }
          textColor?.let { put("text-color", it) }
          textSize?.let { put("font-scale", it) }
        }
      )
}

/**
 * Returns an image type for use in `iconImage` (see
 * [SymbolLayer][dev.sargunv.maplibrecompose.compose.layer.SymbolLayer]), `pattern` entries (see
 * [BackgroundLayer][dev.sargunv.maplibrecompose.compose.layer.BackgroundLayer],
 * [FillLayer][dev.sargunv.maplibrecompose.compose.layer.FillLayer],
 * [FillExtrusionLayer][dev.sargunv.maplibrecompose.compose.layer.FillExtrusionLayer],
 * [LineLayer][dev.sargunv.maplibrecompose.compose.layer.LineLayer]) and as a section in the
 * [format] expression.
 *
 * If set, the image argument will check that the requested image exists in the style and will
 * return either the resolved image name or `null`, depending on whether or not the image is
 * currently in the style. This validation process is synchronous and requires the image to have
 * been added to the style before requesting it in the image argument.
 */
public fun image(value: Expression<StringValue>): Expression<ImageValue> =
  FunctionCall.of("image", value).cast()

/**
 * Returns an image type for use in `iconImage` (see
 * [SymbolLayer][dev.sargunv.maplibrecompose.compose.layer.SymbolLayer]), `pattern` entries (see
 * [BackgroundLayer][dev.sargunv.maplibrecompose.compose.layer.BackgroundLayer],
 * [FillLayer][dev.sargunv.maplibrecompose.compose.layer.FillLayer],
 * [FillExtrusionLayer][dev.sargunv.maplibrecompose.compose.layer.FillExtrusionLayer],
 * [LineLayer][dev.sargunv.maplibrecompose.compose.layer.LineLayer]) and as a section in the
 * [format] expression.
 *
 * The [ImageBitmap] will be registered with the style when it's referenced by a layer, and
 * unregistered from the style if it's no longer referenced by any layer. An ID referencing the
 * bitmap will be generated automatically and inserted into the expression.
 */
public fun image(value: ImageBitmap): Expression<ImageValue> =
  FunctionCall.of("image", BitmapLiteral.of(value)).cast()

/**
 * Returns an image type for use in `iconImage` (see
 * [SymbolLayer][dev.sargunv.maplibrecompose.compose.layer.SymbolLayer]), `pattern` entries (see
 * [BackgroundLayer][dev.sargunv.maplibrecompose.compose.layer.BackgroundLayer],
 * [FillLayer][dev.sargunv.maplibrecompose.compose.layer.FillLayer],
 * [FillExtrusionLayer][dev.sargunv.maplibrecompose.compose.layer.FillExtrusionLayer],
 * [LineLayer][dev.sargunv.maplibrecompose.compose.layer.LineLayer]) and as a section in the
 * [format] expression.
 *
 * The [Painter] will be drawn to an [ImageBitmap] and registered with the style when it's
 * referenced by a layer, and unregistered from the style if it's no longer referenced by any layer.
 * An ID referencing the bitmap will be generated automatically and inserted into the expression.
 *
 * The bitmap will be created with the intrinsic size of the painter, or 16x16 DP if the painter
 * does not have an intrinsic size.
 */
public fun image(value: Painter): Expression<ImageValue> =
  FunctionCall.of("image", PainterLiteral.of(value)).cast()

/**
 * Converts this number into a string representation using the provided formatting rules.
 *
 * @param locale BCP 47 language tag for which locale to use
 * @param currency an ISO 4217 code to use for currency-style formatting
 * @param minFractionDigits minimum fractional digits to include
 * @param maxFractionDigits maximum fractional digits to include
 */
public fun Expression<NumberValue<*>>.formatToString(
  locale: Expression<StringValue>? = null,
  currency: Expression<StringValue>? = null,
  minFractionDigits: Expression<IntValue>? = null,
  maxFractionDigits: Expression<IntValue>? = null,
): Expression<StringValue> =
  FunctionCall.of(
      "number-format",
      this,
      Options.build(
        fun MutableMap<String, Expression<*>>.() {
          locale?.let { put("locale", it) }
          currency?.let { put("currency", it) }
          minFractionDigits?.let { put("min-fraction-digits", it) }
          maxFractionDigits?.let { put("max-fraction-digits", it) }
        }
      ),
    )
    .cast()

/**
 * Converts this expression to a string.
 *
 * If this is ...
 * - `null`, the result is `""`
 * - a boolean, the result is `"true"` or `"false"`
 * - a number, it is converted to a string as specified by the "NumberToString" algorithm of the
 *   ECMAScript Language Specification.
 * - a color, it is converted to a string of the form `"rgba(r,g,b,a)"`, where `r`, `g`, and `b` are
 *   numerals ranging from 0 to 255, and `a` ranges from 0 to 1.
 *
 * Otherwise, the input is converted to a string in the format specified by the JSON.stringify
 * function of the ECMAScript Language Specification.
 */
public fun Expression<*>.convertToString(): Expression<StringValue> =
  FunctionCall.of("to-string", this).cast()

/**
 * Converts this expression to a number.
 *
 * If this expression is `null` or `false`, the result is `0`. If this is `true`, the result is `1`.
 * If the input is a string, it is converted to a number as specified by the "ToNumber Applied to
 * the String Type" algorithm of the ECMAScript Language Specification.
 *
 * In case this expression cannot be converted to a number, each of the [fallbacks] is evaluated in
 * order until the first successful conversion is obtained. If none of the inputs can be converted,
 * the expression is an error.
 */
public fun Expression<*>.convertToNumber(vararg fallbacks: Expression<*>): Expression<FloatValue> =
  FunctionCall.of("to-number", this, *fallbacks).cast()

/**
 * Converts this expression to a boolean expression.
 *
 * The result is `false` when then this is an empty string, `0`, `false`,`null` or `NaN`; otherwise
 * it is `true`.
 */
public fun Expression<*>.convertToBoolean(): Expression<BooleanValue> =
  FunctionCall.of("to-boolean", this).cast()

/**
 * Converts this expression to a color expression.
 *
 * In case this expression cannot be converted to a color, each of the [fallbacks] is evaluated in
 * order until the first successful conversion is obtained. If none of the inputs can be converted,
 * the expression is an error.
 */
public fun Expression<*>.convertToColor(vararg fallbacks: Expression<*>): Expression<ColorValue> =
  FunctionCall.of("to-color", this, *fallbacks).cast()

/** Converts a numeric [Expression] to a [DpValue] expression. */
public val Expression<FloatValue>.dp: Expression<DpValue>
  get() = this.cast()

/** Converts a numeric [Expression] in milliseconds to a [MillisecondsValue] expression. */
public val Expression<FloatValue>.milliseconds: Expression<MillisecondsValue>
  get() = this.cast()

/** Converts a numeric [Expression] in seconds to a [MillisecondsValue] expression. */
public val Expression<FloatValue>.seconds: Expression<MillisecondsValue>
  get() = (this * const(1000f)).cast()

/** Converts a numeric [Expression] to an [TextUnitValue] expression in SP. */
public val Expression<FloatValue>.sp: Expression<TextUnitValue>
  get() = TextUnitCalculation.of(this, TextUnitType.Sp)

/** Converts a numeric [Expression] to an [TextUnitValue] expression in EM */
public val Expression<FloatValue>.em: Expression<TextUnitValue>
  get() = TextUnitCalculation.of(this, TextUnitType.Em)
