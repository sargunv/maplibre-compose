@file:JsModule("maplibre-gl")

package dev.sargunv.maplibrejs

import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLElement

/** [Map](https://maplibre.org/maplibre-gl-js/docs/API/classes/Map/) */
public external class Map public constructor(options: MapOptions) {
  public var repaint: Boolean
  public var showCollisionBoxes: Boolean
  public var showOverdrawInspector: Boolean
  public var showPadding: Boolean
  public var showTileBoundaries: Boolean
  public val version: String

  public val doubleClickZoom: DoubleClickZoomHandler
  public val dragPan: DragPanHandler
  public val dragRotate: DragRotateHandler
  public val keyboard: KeyboardHandler
  public val scrollZoom: ScrollZoomHandler
  public val touchPitch: TwoFingersTouchPitchHandler
  public val touchZoomRotate: TwoFingersTouchZoomRotateHandler

  public fun setStyle(style: String)

  public fun remove()

  public fun getBearing(): Double

  public fun getCenter(): LngLat

  public fun getPitch(): Double

  public fun getZoom(): Double

  public fun setBearing(bearing: Double)

  public fun setCenter(center: LngLat)

  public fun setPitch(pitch: Double)

  public fun setZoom(zoom: Double)

  public fun setMaxZoom(max: Double)

  public fun setMinZoom(min: Double)

  public fun setMaxPitch(max: Double)

  public fun setMinPitch(min: Double)

  public fun jumpTo(options: JumpToOptions)

  public fun flyTo(options: FlyToOptions)

  public fun addControl(control: IControl, position: String)

  public fun removeControl(control: IControl)

  public fun triggerRepaint()

  public fun getCanvasContainer(): HTMLElement

  public fun getCanvas(): HTMLCanvasElement

  public fun resize()
}

/**
 * [DoubleClickZoomHandler](https://maplibre.org/maplibre-gl-js/docs/API/classes/DoubleClickZoomHandler/)
 */
public external class DoubleClickZoomHandler {
  public fun disable()

  public fun enable()

  public fun isEnabled(): Boolean

  public fun isActive(): Boolean
}

/** [DragPanHandler](https://maplibre.org/maplibre-gl-js/docs/API/classes/DragPanHandler/) */
public external class DragPanHandler {
  public fun disable()

  public fun enable()

  public fun isEnabled(): Boolean

  public fun isActive(): Boolean
}

/** [DragRotateHandler](https://maplibre.org/maplibre-gl-js/docs/API/classes/DragRotateHandler/) */
public external class DragRotateHandler {
  public fun disable()

  public fun enable()

  public fun isEnabled(): Boolean

  public fun isActive(): Boolean
}

/** [KeyboardHandler](https://maplibre.org/maplibre-gl-js/docs/API/classes/KeyboardHandler/) */
public external class KeyboardHandler {
  public fun disable()

  public fun disableRotation()

  public fun enable()

  public fun enableRotation()

  public fun isEnabled(): Boolean

  public fun isActive(): Boolean
}

/** [ScrollZoomHandler](https://maplibre.org/maplibre-gl-js/docs/API/classes/ScrollZoomHandler/) */
public external class ScrollZoomHandler {
  public fun disable()

  public fun enable()

  public fun isEnabled(): Boolean

  public fun isActive(): Boolean

  public fun setWheelZoomRate(wheelZoomRate: Double)

  public fun setZoomRate(zoomRate: Double)
}

/**
 * [TwoFingersTouchPitchHandler](https://maplibre.org/maplibre-gl-js/docs/API/classes/TwoFingersTouchPitchHandler/)
 */
public external class TwoFingersTouchPitchHandler {
  public fun disable()

  public fun enable()

  public fun isEnabled(): Boolean

  public fun isActive(): Boolean
}

/**
 * [TwoFingersTouchZoomRotateHandler](https://maplibre.org/maplibre-gl-js/docs/API/classes/TwoFingersTouchZoomRotateHandler/)
 */
public external class TwoFingersTouchZoomRotateHandler {
  public fun disable()

  public fun disableRotation()

  public fun enable()

  public fun enableRotation()

  public fun isEnabled(): Boolean

  public fun isActive(): Boolean
}

/** [LogoControl](https://maplibre.org/maplibre-gl-js/docs/API/classes/LogoControl/) */
public external class LogoControl
public constructor(options: LogoControlOptions = definedExternally) : IControl {
  override fun onAdd(map: Map): HTMLElement

  override fun onRemove(map: Map)
}

/**
 * [LogoControlOptions](https://maplibre.org/maplibre-gl-js/docs/API/type-aliases/LogoControlOptions/)
 */
public external interface LogoControlOptions {
  public var compact: Boolean?
}

/** [ScaleControl](https://maplibre.org/maplibre-gl-js/docs/API/classes/ScaleControl/) */
public external class ScaleControl
public constructor(options: ScaleControlOptions = definedExternally) : IControl {
  override fun onAdd(map: Map): HTMLElement

  override fun onRemove(map: Map)
}

/**
 * [ScaleControlOptions](https://maplibre.org/maplibre-gl-js/docs/API/type-aliases/ScaleControlOptions/)
 */
public external interface ScaleControlOptions {
  public var maxWidth: Double?
  public var unit: String?
}

/**
 * [AttributionControl](https://maplibre.org/maplibre-gl-js/docs/API/classes/AttributionControl/)
 */
public external class AttributionControl
public constructor(options: AttributionControlOptions = definedExternally) : IControl {
  override fun onAdd(map: Map): HTMLElement

  override fun onRemove(map: Map)
}

/**
 * [AttributionControlOptions](https://maplibre.org/maplibre-gl-js/docs/API/type-aliases/AttributionControlOptions/)
 */
public external interface AttributionControlOptions {
  public var compact: Boolean?
  public var customAttribution: String?
}

/** [NavigationControl](https://maplibre.org/maplibre-gl-js/docs/API/classes/NavigationControl/) */
public external class NavigationControl
public constructor(options: NavigationControlOptions = definedExternally) : IControl {
  override fun onAdd(map: Map): HTMLElement

  override fun onRemove(map: Map)
}

/**
 * [NavigationControlOptions](https://maplibre.org/maplibre-gl-js/docs/API/type-aliases/NavigationControlOptions/)
 */
public external interface NavigationControlOptions {
  public var showCompass: Boolean?
  public var showZoom: Boolean?
  public var visualizePitch: Boolean?
}

/** [IControl](https://maplibre.org/maplibre-gl-js/docs/API/interfaces/IControl/) */
public external interface IControl {
  public fun onAdd(map: Map): HTMLElement

  public fun onRemove(map: Map)
}

/** [LngLat](https://maplibre.org/maplibre-gl-js/docs/API/classes/LngLat/) */
public external class LngLat(public val lng: Double, public val lat: Double) {
  public fun toArray(): DoubleArray
}

/** [JumpToOptions](https://maplibre.org/maplibre-gl-js/docs/API/type-aliases/JumpToOptions/) */
public sealed external interface JumpToOptions : CameraOptions {
  public var padding: PaddingOptions?
}

/** [FlyToOptions](https://maplibre.org/maplibre-gl-js/docs/API/type-aliases/FlyToOptions/) */
public sealed external interface FlyToOptions : CameraOptions {
  public var curve: Double?
  public var maxDuration: Double?
  public var minZoom: Double?
  public var padding: PaddingOptions?
  public var speed: Double?
  public var screenSpeed: Double?
}

/** [CameraOptions](https://maplibre.org/maplibre-gl-js/docs/API/type-aliases/CameraOptions/) */
public sealed external interface CameraOptions : CenterZoomBearing {
  public var around: LngLat?
  public var pitch: Double?
}

/**
 * [CenterZoomBearing](https://maplibre.org/maplibre-gl-js/docs/API/type-aliases/CenterZoomBearing/)
 */
public sealed external interface CenterZoomBearing {
  public var bearing: Double?
  public var center: LngLat?
  public var zoom: Double?
}

/** [PaddingOptions](https://maplibre.org/maplibre-gl-js/docs/API/type-aliases/PaddingOptions/) */
public sealed external interface PaddingOptions {
  public var bottom: Double
  public var left: Double
  public var right: Double
  public var top: Double
}

/** [MapOptions](https://maplibre.org/maplibre-gl-js/docs/API/type-aliases/MapOptions/) */
public sealed external interface MapOptions {
  public var container: HTMLElement
  public var attributionControl: dynamic // false | AttributionControlOptions
}
