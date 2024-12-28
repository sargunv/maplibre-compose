package dev.sargunv.maplibrejs

import org.w3c.dom.HTMLElement

public fun MapOptions(container: HTMLElement, style: String): MapOptions = jso {
  this.container = container
  this.style = style
}
