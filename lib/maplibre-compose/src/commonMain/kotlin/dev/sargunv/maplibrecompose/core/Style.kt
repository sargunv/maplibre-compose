package dev.sargunv.maplibrecompose.core

import androidx.compose.ui.graphics.ImageBitmap
import dev.sargunv.maplibrecompose.core.layer.Layer
import dev.sargunv.maplibrecompose.core.source.Source

internal interface Style {
  /**
   * Returns true if the style is loaded. Returns false if a new style is underway of being loaded.
   */
  var isLoaded: Boolean

  /** @throws IllegalStateException on Android if [isLoaded] is false. */
  fun addImage(id: String, image: ImageBitmap, sdf: Boolean)

  /** @throws IllegalStateException on Android if [isLoaded] is false. */
  fun removeImage(id: String)

  /** @throws IllegalStateException on Android if [isLoaded] is false. */
  fun getSource(id: String): Source?

  /** @throws IllegalStateException on Android if [isLoaded] is false. */
  fun getSources(): List<Source>

  /** @throws IllegalStateException on Android if [isLoaded] is false. */
  fun addSource(source: Source)

  /** @throws IllegalStateException on Android if [isLoaded] is false. */
  fun removeSource(source: Source)

  /** @throws IllegalStateException on Android if [isLoaded] is false. */
  fun getLayer(id: String): Layer?

  /** @throws IllegalStateException on Android if [isLoaded] is false. */
  fun getLayers(): List<Layer>

  /** @throws IllegalStateException on Android if [isLoaded] is false. */
  fun addLayer(layer: Layer)

  /** @throws IllegalStateException on Android if [isLoaded] is false. */
  fun addLayerAbove(id: String, layer: Layer)

  /** @throws IllegalStateException on Android if [isLoaded] is false. */
  fun addLayerBelow(id: String, layer: Layer)

  /** @throws IllegalStateException on Android if [isLoaded] is false. */
  fun addLayerAt(index: Int, layer: Layer)

  /** @throws IllegalStateException on Android if [isLoaded] is false. */
  fun removeLayer(layer: Layer)

  object Null : Style {
    override var isLoaded: Boolean = false

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
  }
}
