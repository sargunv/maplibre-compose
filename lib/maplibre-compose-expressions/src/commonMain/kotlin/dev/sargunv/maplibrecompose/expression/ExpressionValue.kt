package dev.sargunv.maplibrecompose.expression

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.TextUnit
import kotlin.time.Duration

// TODO move to package value

/**
 * Represents a value that an [Expression] can resolve to. These types are never actually
 * instantiated at runtime; they're only used as type parameters to hint at the type of an
 * [Expression]
 */
public sealed interface ExpressionValue

/**
 * Represents an [Expression] that resolves to a true or false value. See [ExpressionsDsl.const].
 */
public sealed interface BooleanValue : ExpressionValue, EquatableValue

/**
 * Represents an [Expression] that resolves to a numeric quantity. Corresponds to numbers in the
 * JSON style spec. Use [ExpressionsDsl.const] to create a literal [NumberValue].
 *
 * @param U the unit type of the number. For dimensionless quantities, use [Number].
 */
public sealed interface NumberValue<U> :
  ExpressionValue,
  MatchableValue,
  InterpolateableValue<U>,
  ComparableValue<NumberValue<U>>,
  EquatableValue

/**
 * Represents an [Expression] that resolves to a dimensionless quantity. See [ExpressionsDsl.const].
 */
public typealias FloatValue = NumberValue<Number>

/**
 * Represents an [Expression] that resolves to an integer dimensionless quantity. See
 * [ExpressionsDsl.const].
 */
public sealed interface IntValue : NumberValue<Number>

/**
 * Represents an [Expression] that resolves to device-independent pixels ([Dp]). See
 * [ExpressionsDsl.const].
 */
public typealias DpValue = NumberValue<Dp>

/**
 * Represents an [Expression] that resolves to scalable pixels or em ([TextUnit]). See
 * [ExpressionsDsl.const].
 *
 * Which unit it resolves to is determined by the style property it's used in.
 */
public typealias TextUnitValue = NumberValue<TextUnit>

/**
 * Represents an [Expression] that resolves to an amount of time with millisecond precision
 * ([Duration]). See [ExpressionsDsl.const].
 */
public typealias MillisecondsValue = NumberValue<Duration>

/** Represents an [Expression] that resolves to a string value. See [ExpressionsDsl.const]. */
public sealed interface StringValue :
  ExpressionValue,
  MatchableValue,
  ComparableValue<StringValue>,
  EquatableValue,
  FormattableValue,
  FormattedValue

/**
 * Represents an [Expression] that resolves to an enum string. See [ExpressionsDsl.const].
 *
 * @param T The [EnumValue] descendent type that this value represents.
 */
public sealed interface EnumValue<out T> : StringValue {
  /** The string expression representing this enum value. You probably don't need this. */
  public val stringConst: StringLiteral
}

/** Represents an [Expression] that resolves to a [Color] value. See [ExpressionsDsl.const]. */
public sealed interface ColorValue : ExpressionValue, InterpolateableValue<ColorValue>

/**
 * Represents an [Expression] that resolves to a map value (corresponds to a JSON object). See
 * [ExpressionsDsl.const].
 */
public sealed interface MapValue<out T : ExpressionValue> : ExpressionValue

/**
 * Represents an [Expression] that resolves to a list value (corresponds to a JSON array). See
 * [ExpressionsDsl.const].
 */
public sealed interface ListValue<out T : ExpressionValue> : ExpressionValue

/**
 * Represents an [Expression] that resolves to a list value (corresponds to a JSON array) of
 * alternating types.
 */
public sealed interface AlternatingListValue<out T1 : ExpressionValue, out T2 : ExpressionValue> :
  ListValue<ExpressionValue>

/**
 * Represents an [Expression] that resolves to an alternating list of [SymbolAnchor] and
 * [FloatOffsetValue].
 *
 * See [SymbolLayer][dev.sargunv.maplibrecompose.compose.layer.SymbolLayer].
 */
public typealias TextVariableAnchorOffsetValue =
  AlternatingListValue<SymbolAnchor, FloatOffsetValue>

/**
 * Represents an [Expression] that resolves to a list of numbers.
 *
 * @param U the unit type of the number. For dimensionless quantities, use [Number].
 */
public sealed interface VectorValue<U> :
  ListValue<NumberValue<U>>, InterpolateableValue<VectorValue<U>>

/**
 * Represents an [Expression] that reoslves to a 2D vector in some unit.
 *
 * @param U the unit type of the offset. For dimensionless quantities, use [Number].
 */
public sealed interface OffsetValue<U> : VectorValue<U>

/**
 * Represents an [Expression] that resolves to a 2D floating point offset without a particular unit.
 * ([Offset]). See [ExpressionsDsl.offset].
 */
public typealias FloatOffsetValue = OffsetValue<Number>

/**
 * Represents an [Expression] that resolves to a 2D floating point offset in device-independent
 * pixels ([DpOffset]). See [ExpressionsDsl.offset].
 */
public typealias DpOffsetValue = OffsetValue<Dp>

/**
 * Represents an [Expression] that resolves to a 2D floating point offset in scalable pixels or em
 * ([TextUnit]). See [ExpressionsDsl.offset].
 */
public typealias TextUnitOffsetValue = OffsetValue<TextUnit>

/**
 * Represents an [Expression] that resolves to an absolute (layout direction unaware) padding
 * applied along the edges inside a box ([PaddingValues.Absolute]). See [ExpressionsDsl.const].
 */
public sealed interface DpPaddingValue : VectorValue<Dp>

/**
 * Represents an [Expression] that resolves to a collator object for use in locale-dependent
 * comparison operations. See [ExpressionsDsl.collator].
 */
public sealed interface CollatorValue : ExpressionValue

/** Represents an [Expression] that resolves to a formatted string. See [ExpressionsDsl.format]. */
public sealed interface FormattedValue : ExpressionValue

/** Represents an [Expression] that resolves to a geometry object. */
public sealed interface GeoJsonValue : ExpressionValue

/** Represents an [Expression] that resolves to an image. See [ExpressionsDsl.image] */
public sealed interface ImageValue : ExpressionValue, FormattableValue

/**
 * Represents an [Expression] that resolves to an interpolation type. See [ExpressionsDsl.linear],
 * [ExpressionsDsl.exponential], and [ExpressionsDsl.cubicBezier].
 */
public sealed interface InterpolationValue : ExpressionValue

/**
 * Represents and [Expression] that resolves to a value that can be an input to
 * [ExpressionsDsl.format].
 */
public sealed interface FormattableValue : ExpressionValue

/**
 * Represents an [Expression] that resolves to a value that can be compared for equality. See
 * [ExpressionsDsl.eq] and [ExpressionsDsl.neq].
 */
public sealed interface EquatableValue : ExpressionValue

/**
 * Union type for an [Expression] that resolves to a value that can be matched. See
 * [ExpressionsDsl.switch].
 */
public sealed interface MatchableValue : ExpressionValue

/**
 * Union type for an [Expression] that resolves to a value that can be ordered with other values of
 * its type. See [ExpressionsDsl.gt], [ExpressionsDsl.lt], [ExpressionsDsl.gte], and
 * [ExpressionsDsl.lte].
 *
 * @param T the type of the value that can be compared against for ordering.
 */
public sealed interface ComparableValue<T> : ExpressionValue

/**
 * Union type for an [Expression] that resolves to a value that can be interpolated. See
 * [ExpressionsDsl.interpolate].
 *
 * @param T the type of values that can be interpolated between.
 */
public sealed interface InterpolateableValue<T> : ExpressionValue

/** The type of value resolved from an expression, as returned by [ExpressionsDsl.type]. */
public enum class ExpressionType(override val stringConst: StringLiteral) :
  EnumValue<ExpressionType> {
  Number(StringLiteral.of("number")),
  String(StringLiteral.of("string")),
  Object(StringLiteral.of("object")),
  Boolean(StringLiteral.of("boolean")),
  Color(StringLiteral.of("color")),
  Array(StringLiteral.of("array")),
}

/** Type of a GeoJson feature, as returned by [ExpressionsDsl.Feature.type]. */
public enum class GeometryType(override val stringConst: StringLiteral) : EnumValue<GeometryType> {
  Point(StringLiteral.of("Point")),
  LineString(StringLiteral.of("LineString")),
  Polygon(StringLiteral.of("Polygon")),
  MultiPoint(StringLiteral.of("MultiPoint")),
  MultiLineString(StringLiteral.of("MultiLineString")),
  MultiPolygon(StringLiteral.of("MultiPolygon")),
}

/** Frame of reference for offsetting geometry. */
public enum class TranslateAnchor(override val stringConst: StringLiteral) :
  EnumValue<TranslateAnchor> {
  /** Offset is relative to the map */
  Map(StringLiteral.of("map")),

  /** Offset is relative to the viewport */
  Viewport(StringLiteral.of("viewport")),
}

/** Scaling behavior of circles when the map is pitched. */
public enum class CirclePitchScale(override val stringConst: StringLiteral) :
  EnumValue<CirclePitchScale> {
  /**
   * Circles are scaled according to their apparent distance to the camera, i.e. as if they are on
   * the map.
   */
  Map(StringLiteral.of("map")),

  /** Circles are not scaled, i.e. as if glued to the viewport. */
  Viewport(StringLiteral.of("viewport")),
}

/** Orientation of circles when the map is pitched. */
public enum class CirclePitchAlignment(override val stringConst: StringLiteral) :
  EnumValue<CirclePitchAlignment> {
  /** Circles are aligned to the plane of the map, i.e. flat on top of the map. */
  Map(StringLiteral.of("map")),

  /** Circles are aligned to the plane of the viewport, i.e. facing the camera. */
  Viewport(StringLiteral.of("viewport")),
}

/** Direction of light source when map is rotated. */
public enum class IlluminationAnchor(override val stringConst: StringLiteral) :
  EnumValue<IlluminationAnchor> {

  /** The hillshade illumination is relative to the north direction. */
  Map(StringLiteral.of("map")),

  /** The hillshade illumination is relative to the top of the viewport. */
  Viewport(StringLiteral.of("viewport")),
}

/** Display of joined lines */
public enum class LineJoin(override val stringConst: StringLiteral) : EnumValue<LineJoin> {
  /**
   * A join with a squared-off end which is drawn beyond the endpoint of the line at a distance of
   * one-half of the line's width.
   */
  Bevel(StringLiteral.of("bevel")),

  /**
   * A join with a rounded end which is drawn beyond the endpoint of the line at a radius of
   * one-half of the line's width and centered on the endpoint of the line.
   */
  Round(StringLiteral.of("round")),

  /**
   * A join with a sharp, angled corner which is drawn with the outer sides beyond the endpoint of
   * the path until they meet.
   */
  Miter(StringLiteral.of("miter")),
}

/** Display of line endings */
public enum class LineCap(override val stringConst: StringLiteral) : EnumValue<LineCap> {
  /** A cap with a squared-off end which is drawn to the exact endpoint of the line. */
  Butt(StringLiteral.of("butt")),

  /**
   * A cap with a rounded end which is drawn beyond the endpoint of the line at a radius of one-half
   * of the line's width and centered on the endpoint of the line.
   */
  Round(StringLiteral.of("round")),

  /**
   * A cap with a squared-off end which is drawn beyond the endpoint of the line at a distance of
   * one-half of the line's width.
   */
  Square(StringLiteral.of("square")),
}

/**
 * The resampling/interpolation method to use for overscaling, also known as texture magnification
 * filter
 */
public enum class RasterResampling(override val stringConst: StringLiteral) :
  EnumValue<RasterResampling> {
  /**
   * (Bi)linear filtering interpolates pixel values using the weighted average of the four closest
   * original source pixels creating a smooth but blurry look when overscaled
   */
  Linear(StringLiteral.of("linear")),

  /**
   * Nearest neighbor filtering interpolates pixel values using the nearest original source pixel
   * creating a sharp but pixelated look when overscaled
   */
  Nearest(StringLiteral.of("nearest")),
}

/** Symbol placement relative to its geometry. */
public enum class SymbolPlacement(override val stringConst: StringLiteral) :
  EnumValue<SymbolPlacement> {
  /** The label is placed at the point where the geometry is located. */
  Point(StringLiteral.of("point")),

  /**
   * The label is placed along the line of the geometry. Can only be used on LineString and Polygon
   * geometries.
   */
  Line(StringLiteral.of("line")),

  /**
   * The label is placed at the center of the line of the geometry. Can only be used on LineString
   * and Polygon geometries. Note that a single feature in a vector tile may contain multiple line
   * geometries.
   */
  LineCenter(StringLiteral.of("line-center")),
}

/**
 * Determines whether overlapping symbols in the same layer are rendered in the order that they
 * appear in the data source or by their y-position relative to the viewport. To control the order
 * and prioritization of symbols otherwise, use `sortKey`.
 */
public enum class SymbolZOrder(override val stringConst: StringLiteral) : EnumValue<SymbolZOrder> {
  /**
   * Sorts symbols by `sortKey` if set. Otherwise, sorts symbols by their y-position relative to the
   * viewport if `iconAllowOverlap` or `textAllowOverlap` is set to `true` or `iconIgnorePlacement`
   * or `textIgnorePlacement` is `false`.
   */
  Auto(StringLiteral.of("auto")),

  /**
   * Sorts symbols by their y-position relative to the viewport if `iconAllowOverlap` or
   * `textAllowOverlap` is set to `true` or `iconIgnorePlacement` or `textIgnorePlacement` is
   * `false`.
   */
  ViewportY(StringLiteral.of("viewport-y")),

  /**
   * Sorts symbols by `sortKey` if set. Otherwise, no sorting is applied; symbols are rendered in
   * the same order as the source data.
   */
  Source(StringLiteral.of("source")),
}

/** Part of the icon/text placed closest to the anchor. */
public enum class SymbolAnchor(override val stringConst: StringLiteral) : EnumValue<SymbolAnchor> {
  /** The center of the icon is placed closest to the anchor. */
  Center(StringLiteral.of("center")),

  /** The left side of the icon is placed closest to the anchor. */
  Left(StringLiteral.of("left")),

  /** The right side of the icon is placed closest to the anchor. */
  Right(StringLiteral.of("right")),

  /** The top of the icon is placed closest to the anchor. */
  Top(StringLiteral.of("top")),

  /** The bottom of the icon is placed closest to the anchor. */
  Bottom(StringLiteral.of("bottom")),

  /** The top left corner of the icon is placed closest to the anchor. */
  TopLeft(StringLiteral.of("top-left")),

  /** The top right corner of the icon is placed closest to the anchor. */
  TopRight(StringLiteral.of("top-right")),

  /** The bottom left corner of the icon is placed closest to the anchor. */
  BottomLeft(StringLiteral.of("bottom-left")),

  /** The bottom right corner of the icon is placed closest to the anchor. */
  BottomRight(StringLiteral.of("bottom-right")),
}

/** Controls whether to show an icon/text when it overlaps other symbols on the map. */
public enum class SymbolOverlap(override val stringConst: StringLiteral) :
  EnumValue<SymbolOverlap> {
  /** The icon/text will be hidden if it collides with any other previously drawn symbol. */
  Never(StringLiteral.of("never")),

  /** The icon/text will be visible even if it collides with any other previously drawn symbol. */
  Always(StringLiteral.of("always")),

  /**
   * If the icon/text collides with another previously drawn symbol, the overlap mode for that
   * symbol is checked. If the previous symbol was placed using never overlap mode, the new
   * icon/text is hidden. If the previous symbol was placed using always or cooperative overlap
   * mode, the new icon/text is visible.
   */
  Cooperative(StringLiteral.of("cooperative")),
}

/** In combination with [SymbolPlacement], determines the rotation behavior of icons. */
public enum class IconRotationAlignment(override val stringConst: StringLiteral) :
  EnumValue<IconRotationAlignment> {
  /**
   * For [SymbolPlacement.Point], aligns icons east-west. Otherwise, aligns icon x-axes with the
   * line.
   */
  Map(StringLiteral.of("map")),

  /**
   * Produces icons whose x-axes are aligned with the x-axis of the viewport, regardless of the
   * [SymbolPlacement].
   */
  Viewport(StringLiteral.of("viewport")),

  /**
   * For [SymbolPlacement.Point], this is equivalent to [IconRotationAlignment.Viewport]. Otherwise,
   * this is equivalent to [IconRotationAlignment.Map].
   */
  Auto(StringLiteral.of("auto")),
}

/** Scales the icon to fit around the associated text. */
public enum class IconTextFit(override val stringConst: StringLiteral) : EnumValue<IconTextFit> {
  /** The icon is displayed at its intrinsic aspect ratio. */
  None(StringLiteral.of("none")),

  /** The icon is scaled in the x-dimension to fit the width of the text. */
  Width(StringLiteral.of("width")),

  /** The icon is scaled in the y-dimension to fit the height of the text. */
  Height(StringLiteral.of("height")),

  /** The icon is scaled in both x- and y-dimensions. */
  Both(StringLiteral.of("both")),
}

/** Orientation of icon when map is pitched. */
public enum class IconPitchAlignment(override val stringConst: StringLiteral) :
  EnumValue<IconPitchAlignment> {
  /** The icon is aligned to the plane of the map. */
  Map(StringLiteral.of("map")),

  /** The icon is aligned to the plane of the viewport, i.e. as if glued to the screen */
  Viewport(StringLiteral.of("viewport")),

  /** Automatically matches the value of [IconRotationAlignment] */
  Auto(StringLiteral.of("auto")),
}

/** Orientation of text when map is pitched. */
public enum class TextPitchAlignment(override val stringConst: StringLiteral) :
  EnumValue<TextPitchAlignment> {
  /** The text is aligned to the plane of the map. */
  Map(StringLiteral.of("map")),

  /** The text is aligned to the plane of the viewport, i.e. as if glued to the screen */
  Viewport(StringLiteral.of("viewport")),

  /** Automatically matches the value of [TextRotationAlignment] */
  Auto(StringLiteral.of("auto")),
}

/**
 * In combination with [SymbolPlacement], determines the rotation behavior of the individual glyphs
 * forming the text.
 */
public enum class TextRotationAlignment(override val stringConst: StringLiteral) :
  EnumValue<TextRotationAlignment> {
  /**
   * For [SymbolPlacement.Point], aligns text east-west. Otherwise, aligns text x-axes with the
   * line.
   */
  Map(StringLiteral.of("map")),

  /**
   * Produces glyphs whose x-axes are aligned with the x-axis of the viewport, regardless of the
   * [SymbolPlacement].
   */
  Viewport(StringLiteral.of("viewport")),

  /**
   * For [SymbolPlacement.Point], this is equivalent to [TextRotationAlignment.Viewport]. Otherwise,
   * aligns glyphs to the x-axis of the viewport and places them along the line.
   *
   * **Note**: This value not supported on native platforms yet, see
   * [maplibre-native#250](https://github.com/maplibre/maplibre-native/issues/250)**
   */
  ViewportGlyph(StringLiteral.of("viewport-glyph")),

  /**
   * For [SymbolPlacement.Point], this is equivalent to [TextRotationAlignment.Viewport]. Otherwise,
   * this is equivalent to [TextRotationAlignment.Map].
   */
  Auto(StringLiteral.of("auto")),
}

/** How the text will be laid out. */
public enum class TextWritingMode(override val stringConst: StringLiteral) :
  EnumValue<TextWritingMode> {
  /**
   * If a text's language supports horizontal writing mode, symbols with point placement would be
   * laid out horizontally.
   */
  Horizontal(StringLiteral.of("horizontal")),

  /**
   * If a text's language supports vertical writing mode, symbols with point placement would be laid
   * out vertically.
   */
  Vertical(StringLiteral.of("vertical")),
}

/** Text justification options. */
public enum class TextJustify(override val stringConst: StringLiteral) : EnumValue<TextJustify> {
  /** The text is aligned towards the anchor position. */
  Auto(StringLiteral.of("auto")),

  /** The text is aligned to the left. */
  Left(StringLiteral.of("left")),

  /** The text is centered. */
  Center(StringLiteral.of("center")),

  /** The text is aligned to the right. */
  Right(StringLiteral.of("right")),
}

/** Specifies how to capitalize text, similar to the CSS text-transform property. */
public enum class TextTransform(override val stringConst: StringLiteral) :
  EnumValue<TextTransform> {
  /** The text is not altered. */
  None(StringLiteral.of("none")),

  /** Forces all letters to be displayed in uppercase. */
  Uppercase(StringLiteral.of("uppercase")),

  /** Forces all letters to be displayed in lowercase. */
  Lowercase(StringLiteral.of("lowercase")),
}
