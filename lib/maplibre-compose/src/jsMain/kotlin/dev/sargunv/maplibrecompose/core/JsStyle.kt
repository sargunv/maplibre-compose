package dev.sargunv.maplibrecompose.core

import androidx.compose.ui.graphics.ImageBitmap
import dev.sargunv.maplibrecompose.core.layer.Layer
import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrejs.Map as MlJsMap

internal class JsStyle(internal val impl: MlJsMap) : Style {
  private var listener: Style.Callbacks? = null

  override fun addImage(id: String, image: ImageBitmap, sdf: Boolean) {}

  override fun removeImage(id: String) {}

  override fun getSource(id: String): Source? {
    return null
  }

  override fun getSources(): List<Source> {
    return emptyList()
  }

  override fun addSource(source: Source) {
    // uncomment once adding sources is implemented
    // listener?.onSourceAdded(source)
  }

  override fun removeSource(source: Source) {
    // uncomment once adding sources is implemented
    // listener?.onSourceRemoved(source)
  }

  override fun getLayer(id: String): Layer? {
    return null
  }

  override fun getLayers(): List<Layer> {
    return emptyList()
  }

  override fun addLayer(layer: Layer) {}

  override fun addLayerAbove(id: String, layer: Layer) {}

  override fun addLayerBelow(id: String, layer: Layer) {}

  override fun addLayerAt(index: Int, layer: Layer) {}

  override fun removeLayer(layer: Layer) {}

  override fun setListener(listener: Style.Callbacks?) {
    this.listener = listener
  }
}
