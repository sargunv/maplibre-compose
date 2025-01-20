package dev.sargunv.maplibrecompose.core.source

import dev.sargunv.maplibrecompose.core.util.correctedAndroidUri
import org.maplibre.android.style.sources.RasterSource

public actual class RasterSource : Source {
  override val impl: RasterSource

  public constructor(source: RasterSource) {
    impl = source
  }

  public actual constructor(id: String, uri: String, tileSize: Int) {
    impl = RasterSource(id, uri.correctedAndroidUri(), tileSize)
  }
}
