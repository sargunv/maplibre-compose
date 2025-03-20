package dev.sargunv.maplibrecompose.core

import androidx.compose.ui.graphics.ImageBitmap
import dev.sargunv.maplibrecompose.core.layer.Layer
import dev.sargunv.maplibrecompose.core.source.Source

internal interface Style {
  fun addImage(id: String, image: ImageBitmap, sdf: Boolean)

  fun removeImage(id: String)

  fun getSource(id: String): Source?

  fun getSources(): List<Source>

  fun addSource(source: Source)

  fun removeSource(source: Source)

  fun getLayer(id: String): Layer?

  fun getLayers(): List<Layer>

  fun addLayer(layer: Layer)

  fun addLayerAbove(id: String, layer: Layer)

  fun addLayerBelow(id: String, layer: Layer)

  fun addLayerAt(index: Int, layer: Layer)

  fun removeLayer(layer: Layer)

  fun setListener(listener: Callbacks?)

  interface Callbacks {
    fun onSourceAdded(source: Source)

    fun onSourceRemoved(source: Source)
  }

  object Null : Style {
    override fun addImage(id: String, image: ImageBitmap, sdf: Boolean) {}

    override fun removeImage(id: String) {}

    override fun getSource(id: String): Source? = null

    override fun getSources(): List<Source> = emptyList()

    override fun addSource(source: Source) {}

    override fun removeSource(source: Source) {}

    override fun getLayer(id: String): Layer? = null

    override fun getLayers(): List<Layer> = emptyList()

    override fun addLayer(layer: Layer) {}

    override fun addLayerAbove(id: String, layer: Layer) {}

    override fun addLayerBelow(id: String, layer: Layer) {}

    override fun addLayerAt(index: Int, layer: Layer) {}

    override fun removeLayer(layer: Layer) {}

    override fun setListener(listener: Callbacks?) {}
  }
}
