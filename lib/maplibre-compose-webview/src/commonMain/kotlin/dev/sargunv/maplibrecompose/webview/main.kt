@file:OptIn(ExperimentalJsExport::class)

package dev.sargunv.maplibrecompose.webview

import dev.sargunv.maplibrejs.MapOptions
import dev.sargunv.maplibrejs.Maplibre
import kotlinx.browser.document
import org.w3c.dom.HTMLDivElement

internal lateinit var map: Maplibre

internal fun main() {
  document.addEventListener(
    "DOMContentLoaded",
    {
      val container = document.createElement("div") as HTMLDivElement
      container.setAttribute("style", "width: 100%; height: 100vh;")
      document.body!!.appendChild(container)
      map = Maplibre(MapOptions(container = container))
    },
  )
}

@JsExport
fun setStyle(style: String) {
  map.setStyle(style)
}
