package dev.sargunv.maplibrecompose.core.source

import dev.sargunv.maplibrecompose.core.util.correctedAndroidUri
import org.maplibre.android.style.sources.VectorSource

public actual class VectorSource : Source {
  override val impl: VectorSource

  public constructor(source: VectorSource) {
    impl = source
  }

  public actual constructor(id: String, uri: String) {
    impl = VectorSource(id, uri.correctedAndroidUri())
  }
}
