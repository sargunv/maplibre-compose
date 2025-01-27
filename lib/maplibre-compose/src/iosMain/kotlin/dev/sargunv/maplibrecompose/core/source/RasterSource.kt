package dev.sargunv.maplibrecompose.core.source

import cocoapods.MapLibre.MLNRasterTileSource
import platform.Foundation.NSURL

public actual class RasterSource : Source {
  override val impl: MLNRasterTileSource

  internal constructor(source: MLNRasterTileSource) {
    this.impl = source
  }

  public actual constructor(id: String, uri: String, tileSize: Int) : super() {
    this.impl = MLNRasterTileSource(id, NSURL(string = uri), tileSize.toDouble())
  }
}
