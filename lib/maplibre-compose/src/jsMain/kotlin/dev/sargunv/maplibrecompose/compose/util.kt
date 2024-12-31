package dev.sargunv.maplibrecompose.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import org.w3c.dom.HTMLElement
import org.w3c.dom.Node

internal fun Dp.toCssValue(): String = "${value}px"

internal fun HTMLElement.matchLayout(layoutCoordinates: LayoutCoordinates, density: Density) {
  with(density) {
    style.apply {
      val rect = layoutCoordinates.boundsInWindow()
      width = rect.width.toDp().toCssValue()
      height = rect.height.toDp().toCssValue()
      left = rect.left.toDp().toCssValue()
      top = rect.top.toDp().toCssValue()
    }
  }
}

@Composable
internal fun <T : Node> rememberDomNode(parent: Node, factory: () -> T): T {
  return remember(key1 = parent, calculation = factory).also { child ->
    DisposableEffect(parent, child) {
      parent.insertBefore(child, parent.firstChild)
      onDispose { parent.removeChild(child) }
    }
  }
}
