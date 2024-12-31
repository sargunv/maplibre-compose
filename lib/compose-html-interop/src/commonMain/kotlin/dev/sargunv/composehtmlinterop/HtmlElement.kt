package dev.sargunv.composehtmlinterop

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity

@Composable
public fun <T : HTMLElement> HtmlElement(
  factory: () -> T,
  update: (T) -> Unit = {},
  modifier: Modifier = Modifier,
) {
  val density = LocalDensity.current
  val container = rememberContainerNode()
  val child = rememberDomNode(parent = container, factory = factory)
  SnapshotEffect(child) { update(it) }
  Box(modifier.onGloballyPositioned { container.matchLayout(it, density) })
  HtmlFocusAdapter(container)
}
