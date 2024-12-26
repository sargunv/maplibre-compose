package dev.sargunv.maplibrecompose.expressions.dsl

import dev.sargunv.maplibrecompose.expressions.ast.Expression
import dev.sargunv.maplibrecompose.expressions.ast.FunctionCall
import dev.sargunv.maplibrecompose.expressions.ast.Options
import dev.sargunv.maplibrecompose.expressions.value.FormattableValue
import dev.sargunv.maplibrecompose.expressions.value.FormattedValue
import dev.sargunv.maplibrecompose.expressions.value.StringValue
import dev.sargunv.maplibrecompose.expressions.value.TextUnitValue

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
