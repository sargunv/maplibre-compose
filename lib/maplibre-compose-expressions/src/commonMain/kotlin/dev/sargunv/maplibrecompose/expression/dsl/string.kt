package dev.sargunv.maplibrecompose.expression.dsl

import dev.sargunv.maplibrecompose.expression.ast.Expression
import dev.sargunv.maplibrecompose.expression.ast.FunctionCall
import dev.sargunv.maplibrecompose.expression.value.BooleanValue
import dev.sargunv.maplibrecompose.expression.value.CollatorValue
import dev.sargunv.maplibrecompose.expression.value.IntValue
import dev.sargunv.maplibrecompose.expression.value.StringValue
import kotlin.jvm.JvmName

/** Returns whether this string contains the [substring]. */
@JvmName("containsString")
public fun Expression<StringValue>.contains(
  substring: Expression<StringValue>
): Expression<BooleanValue> = FunctionCall.of("in", substring, this).cast()

/**
 * Returns the first index at which the [substring] is located in this string, or `-1` if it cannot
 * be found. Accepts an optional [startIndex] from where to begin the search.
 */
@JvmName("indexOfString")
public fun Expression<StringValue>.indexOf(
  substring: Expression<StringValue>,
  startIndex: Expression<IntValue>? = null,
): Expression<IntValue> {
  val args = buildList {
    add(substring)
    add(this@indexOf)
    startIndex?.let { add(it) }
  }
  return FunctionCall.of("index-of", *args.toTypedArray<Expression<*>>()).cast()
}

/**
 * Returns a substring from this string from the [startIndex] (inclusive) to the end of the string
 * if [endIndex] is not specified or `null`, otherwise to [endIndex] (exclusive).
 *
 * A UTF-16 surrogate pair counts as a single position.
 */
public fun Expression<StringValue>.substring(
  startIndex: Expression<IntValue>,
  endIndex: Expression<IntValue>? = null,
): Expression<StringValue> {
  val args = buildList {
    add(this@substring)
    add(startIndex)
    endIndex?.let { add(it) }
  }
  return FunctionCall.of("slice", *args.toTypedArray<Expression<*>>()).cast()
}

/**
 * Gets the length of this string.
 *
 * A UTF-16 surrogate pair counts as a single position.
 */
@JvmName("lengthOfString")
public fun Expression<StringValue>.length(): Expression<IntValue> =
  FunctionCall.of("length", this).cast()

/**
 * Returns `true` if this string is expected to render legibly. Returns `false` if this string
 * contains sections that cannot be rendered without potential loss of meaning (e.g. Indic scripts
 * that require complex text shaping).
 */
public fun Expression<StringValue>.isScriptSupported(): Expression<BooleanValue> =
  FunctionCall.of("is-supported-script", this).cast()

/**
 * Returns this string converted to uppercase. Follows the Unicode Default Case Conversion algorithm
 * and the locale-insensitive case mappings in the Unicode Character Database.
 */
public fun Expression<StringValue>.uppercase(): Expression<StringValue> =
  FunctionCall.of("upcase", this).cast()

/**
 * Returns this string converted to lowercase. Follows the Unicode Default Case Conversion algorithm
 * and the locale-insensitive case mappings in the Unicode Character Database.
 */
public fun Expression<StringValue>.lowercase(): Expression<StringValue> =
  FunctionCall.of("downcase", this).cast()

/** Concatenates this string expression with [other]. */
@JvmName("concat")
public operator fun Expression<StringValue>.plus(
  other: Expression<StringValue>
): Expression<StringValue> = FunctionCall.of("concat", this, other).cast()

/**
 * Returns the IETF language tag of the locale being used by the provided [collator]. This can be
 * used to determine the default system locale, or to determine if a requested locale was
 * successfully loaded.
 */
public fun resolvedLocale(collator: Expression<CollatorValue>): Expression<StringValue> =
  FunctionCall.of("resolved-locale", collator).cast()
