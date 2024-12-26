package dev.sargunv.maplibrecompose.expressions

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import dev.sargunv.maplibrecompose.expressions.ast.Expression
import dev.sargunv.maplibrecompose.expressions.value.FloatValue

public interface ExpressionContext {
  /** The scale factor to convert EMs to the desired unit */
  public val emScale: Expression<FloatValue>

  /** The scale factor to convert SPs to the desired unit */
  public val spScale: Expression<FloatValue>

  /** @return the resolved identifier for the [bitmap]. */
  public fun resolveBitmap(bitmap: ImageBitmap): String

  /** @return the resolved identifier for the [painter]. */
  public fun resolvePainter(painter: Painter): String

  public object None : ExpressionContext {
    override val emScale: Expression<FloatValue>
      get() = error("TextUnit not allowed in this context")

    override val spScale: Expression<FloatValue>
      get() = error("TextUnit not allowed in this context")

    override fun resolveBitmap(bitmap: ImageBitmap): String =
      error("Bitmap not allowed in this context")

    override fun resolvePainter(painter: Painter): String =
      error("Painter not allowed in this context")
  }
}
