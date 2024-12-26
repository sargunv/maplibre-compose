package dev.sargunv.maplibrecompose.compose.engine

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.takeOrElse
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

internal class ImageManager(private val node: StyleNode) {
  private val bitmapIds = IncrementingIdMap<ImageBitmap>("bitmap")
  private val bitmapCounter = ReferenceCounter<ImageBitmap>()

  private val painterIds = IncrementingIdMap<Painter>("painter")
  private val painterCounter = ReferenceCounter<Painter>()
  private val painterBitmaps = mutableMapOf<Painter, ImageBitmap>()

  internal fun acquireBitmap(bitmap: ImageBitmap): String {
    bitmapCounter.increment(bitmap) {
      val id = bitmapIds.addId(bitmap)
      node.logger?.i { "Adding bitmap $id" }
      node.style.addImage(id, bitmap)
    }
    return bitmapIds.getId(bitmap)
  }

  internal fun releaseBitmap(bitmap: ImageBitmap) {
    bitmapCounter.decrement(bitmap) {
      val id = bitmapIds.removeId(bitmap)
      node.logger?.i { "Removing bitmap $id" }
      node.style.removeImage(id)
    }
  }

  private fun Painter.drawToBitmap(
    density: Density,
    layoutDirection: LayoutDirection,
  ): ImageBitmap {
    val size = intrinsicSize.takeOrElse { Size(16f, 16f) }
    return ImageBitmap(size.width.toInt(), size.height.toInt()).also { bitmap ->
      CanvasDrawScope().draw(density, layoutDirection, Canvas(bitmap), size) { draw(size) }
    }
  }

  internal fun acquirePainter(
    painter: Painter,
    density: Density,
    layoutDirection: LayoutDirection,
  ): String {
    painterCounter.increment(painter) {
      val id = painterIds.addId(painter)
      node.logger?.i { "Adding painter $id" }
      painter.drawToBitmap(density, layoutDirection).let { bitmap ->
        painterBitmaps[painter] = bitmap
        node.style.addImage(id, bitmap)
      }
    }
    return painterIds.getId(painter)
  }

  internal fun releasePainter(painter: Painter) {
    painterCounter.decrement(painter) {
      val id = painterIds.removeId(painter)
      node.logger?.i { "Removing painter $id" }
      painterBitmaps.remove(painter)
      node.style.removeImage(id)
    }
  }
}
