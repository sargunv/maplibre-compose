@file:JsModule("maplibre-gl")

package dev.sargunv.maplibrejs

import org.w3c.dom.HTMLElement

/** [Map](https://maplibre.org/maplibre-gl-js/docs/API/classes/Map/) */
@JsName("Map")
public external class Maplibre public constructor(options: MapOptions) {
  public fun setStyle(style: String)
}

/** [MapOptions](https://maplibre.org/maplibre-gl-js/docs/API/type-aliases/MapOptions/) */
public sealed external interface MapOptions {
  public var container: HTMLElement
}
