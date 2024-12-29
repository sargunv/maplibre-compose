package dev.sargunv.maplibrejs

import org.w3c.dom.HTMLElement

internal fun <T : Any> jso(): T = js("({})") as T

internal inline fun <T : Any> jso(block: T.() -> Unit): T = jso<T>().apply(block)

public fun MapOptions(container: HTMLElement): MapOptions = jso { this.container = container }
