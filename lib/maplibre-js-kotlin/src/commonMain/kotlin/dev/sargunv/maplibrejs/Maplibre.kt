package dev.sargunv.maplibrejs

@JsModule("maplibre-gl")
@JsName("Map")
public external class Maplibre public constructor(options: MapOptions)

public external interface MapOptions {
  public val container: String
}

// TODO split the pure MapLibre JS to Kotlin/JS wrapper module from the JS webview to Kotlin bridge
@OptIn(ExperimentalJsExport::class)
@JsExport
public fun initMap(container: String): Maplibre {
  return Maplibre(
    object : MapOptions {
      override val container = container
    }
  )
}
