package dev.sargunv.maplibrecompose.expression.value

import dev.sargunv.maplibrecompose.expression.ast.StringLiteral

/** Specifies how to capitalize text, similar to the CSS text-transform property. */
public enum class TextTransform(override val literal: StringLiteral) : EnumValue<TextTransform> {
  /** The text is not altered. */
  None(StringLiteral.of("none")),

  /** Forces all letters to be displayed in uppercase. */
  Uppercase(StringLiteral.of("uppercase")),

  /** Forces all letters to be displayed in lowercase. */
  Lowercase(StringLiteral.of("lowercase")),
}
