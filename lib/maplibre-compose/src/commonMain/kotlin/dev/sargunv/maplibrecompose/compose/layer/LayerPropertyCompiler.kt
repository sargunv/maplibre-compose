package dev.sargunv.maplibrecompose.compose.layer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.TextUnitType
import dev.sargunv.maplibrecompose.compose.engine.LocalStyleNode
import dev.sargunv.maplibrecompose.compose.engine.StyleNode
import dev.sargunv.maplibrecompose.expression.ExpressionContext
import dev.sargunv.maplibrecompose.expression.ast.BitmapLiteral
import dev.sargunv.maplibrecompose.expression.ast.CompiledExpression
import dev.sargunv.maplibrecompose.expression.ast.Expression
import dev.sargunv.maplibrecompose.expression.ast.PainterLiteral
import dev.sargunv.maplibrecompose.expression.dsl.const
import dev.sargunv.maplibrecompose.expression.value.ExpressionValue
import dev.sargunv.maplibrecompose.expression.value.FloatValue

internal class LayerPropertyCompiler(
  private val styleNode: StyleNode,
  private val density: Density,
  private val layoutDirection: LayoutDirection,
  private val emScale: Expression<FloatValue>? = null,
  private val spScale: Expression<FloatValue>? = null,
) {
  private val context =
    object : ExpressionContext {
      private var seenTextUnitType: TextUnitType? = null

      override val emScale: Expression<FloatValue>
        get() {
          return this@LayerPropertyCompiler.emScale
            ?: when (seenTextUnitType) {
              null -> {
                seenTextUnitType = TextUnitType.Em
                const(1f)
              }

              TextUnitType.Em -> const(1f)
              else -> error("mixing EM and SP units is not supported in most expressions")
            }
        }

      override val spScale: Expression<FloatValue>
        get() {
          return this@LayerPropertyCompiler.spScale
            ?: when (seenTextUnitType) {
              null -> {
                seenTextUnitType = TextUnitType.Sp
                const(1f)
              }

              TextUnitType.Sp -> const(1f)
              else -> error("mixing SP and EM units is not supported in most expressions")
            }
        }

      override fun resolveBitmap(bitmap: ImageBitmap): String {
        return styleNode.imageManager.acquireBitmap(bitmap)
      }

      override fun resolvePainter(painter: Painter): String {
        return styleNode.imageManager.acquirePainter(painter, density, layoutDirection)
      }

      fun reset() {
        seenTextUnitType = null
      }
    }

  @Composable
  operator fun <T : ExpressionValue> invoke(expression: Expression<T>): CompiledExpression<T> {
    DisposableEffect(this, expression) {
      onDispose {
        expression.visit {
          when (it) {
            is BitmapLiteral -> styleNode.imageManager.releaseBitmap(it.value)
            is PainterLiteral -> styleNode.imageManager.releasePainter(it.value)
            else -> {}
          }
        }
      }
    }
    return remember(this, expression) {
      context.reset()
      expression.compile(context)
    }
  }
}

@Composable
internal fun rememberPropertyCompiler(
  emScale: Expression<FloatValue>? = null,
  spScale: Expression<FloatValue>? = null,
): LayerPropertyCompiler {
  val styleNode = LocalStyleNode.current
  val density = LocalDensity.current
  val layoutDirection = LocalLayoutDirection.current
  return remember(styleNode, density, layoutDirection, emScale, spScale) {
    LayerPropertyCompiler(styleNode, density, layoutDirection, emScale, spScale)
  }
}
