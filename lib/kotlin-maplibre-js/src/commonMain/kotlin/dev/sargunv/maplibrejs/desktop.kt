@file:OptIn(ExperimentalJsExport::class)

package dev.sargunv.maplibrejs

import kotlinx.browser.document
import org.w3c.dom.HTMLDivElement

internal lateinit var map: Maplibre

internal fun main() {
  val container = document.createElement("div") as HTMLDivElement
  container.setAttribute("style", "width: 100%; height: 100vh;")
  document.body!!.appendChild(container)
  map =
    Maplibre(MapOptions(container = container, style = "https://demotiles.maplibre.org/style.json"))
}

@JsExport
public fun setStyle(style: String) {
  map.setStyle(style)
}

// TODO the desktop bridge (this file) should be a separate module from the JS bindings
