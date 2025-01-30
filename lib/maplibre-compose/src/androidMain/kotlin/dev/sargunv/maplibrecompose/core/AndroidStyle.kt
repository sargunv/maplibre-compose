package dev.sargunv.maplibrecompose.core

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import dev.sargunv.maplibrecompose.core.layer.Layer
import dev.sargunv.maplibrecompose.core.layer.UnknownLayer
import dev.sargunv.maplibrecompose.core.source.GeoJsonSource
import dev.sargunv.maplibrecompose.core.source.RasterSource
import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.core.source.UnknownSource
import dev.sargunv.maplibrecompose.core.source.VectorSource
import org.maplibre.android.maps.Style as MLNStyle
import org.maplibre.android.style.sources.GeoJsonSource as MLNGeoJsonSource
import org.maplibre.android.style.sources.RasterSource as MLNRasterSource
import org.maplibre.android.style.sources.Source as MLNSource
import org.maplibre.android.style.sources.VectorSource as MLNVectorSource

internal class AndroidStyle(style: MLNStyle) : Style {
  private var impl: MLNStyle = style

  override fun addImage(id: String, image: ImageBitmap, sdf: Boolean) {
    if (impl.isFullyLoaded) impl.addImage(id, image.asAndroidBitmap(), sdf)
  }

  override fun removeImage(id: String) {
    if (impl.isFullyLoaded) impl.removeImage(id)
  }

  private fun MLNSource.toSource() =
    when (this) {
      is MLNVectorSource -> VectorSource(this)
      is MLNGeoJsonSource -> GeoJsonSource(this)
      is MLNRasterSource -> RasterSource(this)
      else -> UnknownSource(this)
    }

  override fun getSource(id: String): Source? {
    return if (impl.isFullyLoaded) impl.getSource(id)?.toSource() else null
  }

  override fun getSources(): List<Source> {
    return if (impl.isFullyLoaded) impl.sources.map { it.toSource() } else emptyList()
  }

  override fun addSource(source: Source) {
    if (impl.isFullyLoaded) impl.addSource(source.impl)
  }

  override fun removeSource(source: Source) {
    if (impl.isFullyLoaded) impl.removeSource(source.impl)
  }

  override fun getLayer(id: String): Layer? {
    return if (impl.isFullyLoaded) impl.getLayer(id)?.let { UnknownLayer(it) } else null
  }

  override fun getLayers(): List<Layer> {
    return if (impl.isFullyLoaded) impl.layers.map { UnknownLayer(it) } else return emptyList()
  }

  override fun addLayer(layer: Layer) {
    if (impl.isFullyLoaded) impl.addLayer(layer.impl)
  }

  override fun addLayerAbove(id: String, layer: Layer) {
    if (impl.isFullyLoaded) impl.addLayerAbove(layer.impl, id)
  }

  override fun addLayerBelow(id: String, layer: Layer) {
    if (impl.isFullyLoaded) impl.addLayerBelow(layer.impl, id)
  }

  override fun addLayerAt(index: Int, layer: Layer) {
    if (impl.isFullyLoaded) impl.addLayerAt(layer.impl, index)
  }

  override fun removeLayer(layer: Layer) {
    if (impl.isFullyLoaded) impl.removeLayer(layer.impl)
  }
}
