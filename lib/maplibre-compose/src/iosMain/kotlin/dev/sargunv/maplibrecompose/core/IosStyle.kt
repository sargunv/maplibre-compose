package dev.sargunv.maplibrecompose.core

import androidx.compose.ui.graphics.ImageBitmap
import cocoapods.MapLibre.MLNRasterTileSource
import cocoapods.MapLibre.MLNShapeSource
import cocoapods.MapLibre.MLNSource
import cocoapods.MapLibre.MLNStyle
import cocoapods.MapLibre.MLNStyleLayer
import cocoapods.MapLibre.MLNVectorTileSource
import dev.sargunv.maplibrecompose.core.layer.Layer
import dev.sargunv.maplibrecompose.core.layer.UnknownLayer
import dev.sargunv.maplibrecompose.core.source.GeoJsonSource
import dev.sargunv.maplibrecompose.core.source.RasterSource
import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.core.source.UnknownSource
import dev.sargunv.maplibrecompose.core.source.VectorSource
import dev.sargunv.maplibrecompose.core.util.toUIImage

internal class IosStyle(style: MLNStyle, private val getScale: () -> Float) : Style {
  private var impl: MLNStyle = style
  private var listener: Style.Callbacks? = null

  override fun addImage(id: String, image: ImageBitmap, sdf: Boolean) {
    impl.setImage(image.toUIImage(getScale(), sdf), forName = id)
  }

  override fun removeImage(id: String) {
    impl.removeImageForName(id)
  }

  private fun MLNSource.toSource() =
    when (this) {
      is MLNVectorTileSource -> VectorSource(this)
      is MLNShapeSource -> GeoJsonSource(this)
      is MLNRasterTileSource -> RasterSource(this)
      else -> UnknownSource(this)
    }

  override fun getSource(id: String): Source? {
    return impl.sourceWithIdentifier(id)?.toSource()
  }

  override fun getSources(): List<Source> {
    return impl.sources.map { (it as MLNSource).toSource() }
  }

  override fun addSource(source: Source) {
    impl.addSource(source.impl)
    listener?.onSourceAdded(source)
  }

  override fun removeSource(source: Source) {
    impl.removeSource(source.impl)
    listener?.onSourceRemoved(source)
  }

  override fun getLayer(id: String): Layer? {
    return impl.layerWithIdentifier(id)?.let { UnknownLayer(it) }
  }

  override fun getLayers(): List<Layer> {
    return impl.layers.map { UnknownLayer(it as MLNStyleLayer) }
  }

  override fun addLayer(layer: Layer) {
    impl.addLayer(layer.impl)
  }

  override fun addLayerAbove(id: String, layer: Layer) {
    impl.insertLayer(layer.impl, aboveLayer = impl.layerWithIdentifier(id)!!)
  }

  override fun addLayerBelow(id: String, layer: Layer) {
    impl.insertLayer(layer.impl, belowLayer = impl.layerWithIdentifier(id)!!)
  }

  override fun addLayerAt(index: Int, layer: Layer) {
    impl.insertLayer(layer.impl, atIndex = index.toULong())
  }

  override fun removeLayer(layer: Layer) {
    impl.removeLayer(layer.impl)
  }

  override fun setListener(listener: Style.Callbacks?) {
    this.listener = listener
  }
}
