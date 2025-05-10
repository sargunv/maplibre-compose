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
}
