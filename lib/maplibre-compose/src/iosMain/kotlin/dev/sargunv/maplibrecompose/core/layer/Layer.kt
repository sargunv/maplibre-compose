package dev.sargunv.maplibrecompose.core.layer

import cocoapods.MapLibre.MLNStyleLayer

internal actual sealed class Layer {
  abstract val impl: MLNStyleLayer
  actual val id: String by lazy { impl.identifier }
  internal actual var isUnloaded = false

  actual var minZoom: Float
    get() = impl.minimumZoomLevel
    set(value) {
      impl.minimumZoomLevel = value
    }

  actual var maxZoom: Float
    get() = impl.maximumZoomLevel
    set(value) {
      impl.maximumZoomLevel = value
    }

  actual var visible: Boolean
    get() = impl.visible
    set(value) {
      impl.visible = value
    }

  override fun toString() = "${this::class.simpleName}(id=\"$id\")"

  internal fun warnIfUnloaded(methodName: String) {
    if (isUnloaded) {
      println("Warning: Attempting to call $methodName on an unloaded layer")
    }
  }

  internal actual fun unload() {
    isUnloaded = true
  }
}
