package dev.sargunv.maplibrecompose.compose.engine

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.takeOrElse
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import dev.sargunv.maplibrecompose.core.expression.Expression
import dev.sargunv.maplibrecompose.core.expression.ExpressionValue
import dev.sargunv.maplibrecompose.core.expression.ExpressionsDsl.cast
import dev.sargunv.maplibrecompose.core.expression.ResolvedValue

internal class ImageManager(private val node: StyleNode) {
  private val bitmapIds = IncrementingIdMap<ImageBitmap>("bitmap")
  private val bitmapCounter = ReferenceCounter<ImageBitmap>()

  private val painterIds = IncrementingIdMap<Painter>("painter")
  private val painterCounter = ReferenceCounter<Painter>()
  private val painterBitmaps = mutableMapOf<Painter, ImageBitmap>()

  private fun resolveBitmap(bitmap: ImageBitmap): String {
    bitmapCounter.increment(bitmap) {
      val id = bitmapIds.addId(bitmap)
      node.logger?.i { "Adding bitmap $id" }
      node.style.addImage(id, bitmap)
    }
    return bitmapIds.getId(bitmap)
  }

  private fun disposeBitmap(bitmap: ImageBitmap) {
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

  private fun resolvePainter(
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

  private fun disposePainter(painter: Painter) {
    painterCounter.decrement(painter) {
      val id = painterIds.removeId(painter)
      node.logger?.i { "Removing painter $id" }
      painterBitmaps.remove(painter)
      node.style.removeImage(id)
    }
  }

  @Composable
  internal fun <T : ExpressionValue> resolveImages(
    expr: Expression<T>
  ): Expression<ResolvedValue<T>> {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current

    DisposableEffect(expr) {
      onDispose {
        expr.visitLeaves { value ->
          when (value) {
            is ImageBitmap -> disposeBitmap(value)
            is Painter -> disposePainter(value)
            else -> {}
          }
        }
      }
    }

    return remember(expr) {
      expr
        .mapLeaves { value ->
          when (value) {
            is ImageBitmap -> resolveBitmap(value)
            is Painter -> resolvePainter(value, density, layoutDirection)
            else -> value
          }
        }
        .cast()
    }
  }
}
