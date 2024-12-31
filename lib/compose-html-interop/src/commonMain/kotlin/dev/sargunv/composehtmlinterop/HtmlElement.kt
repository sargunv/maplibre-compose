package dev.sargunv.composehtmlinterop

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import kotlinx.browser.document
import org.w3c.dom.HTMLElement

@Composable
public fun <T : HTMLElement> HtmlElement(
  factory: () -> T,
  update: (T) -> Unit = {},
  modifier: Modifier = Modifier,
) {
  val density = LocalDensity.current

  val container =
    rememberDomNode(parent = document.body!!) {
      document.createElement("div").unsafeCast<HTMLElement>().apply {
        style.position = "absolute"
        style.margin = "0px"
      }
    }

  val child = rememberDomNode(parent = container, factory = factory)

  SnapshotEffect(child) { update(it) }

  Box(modifier.onGloballyPositioned { container.matchLayout(it, density) })

  HtmlFocusAdapter(container)
}
