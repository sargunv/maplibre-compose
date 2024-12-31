package dev.sargunv.composehtmlinterop

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import org.w3c.dom.HTMLElement

@Composable
internal fun HtmlFocusAdapter(container: HTMLElement) {
  val focusManager = LocalFocusManager.current
  var ownFocusRequest by remember { mutableStateOf(false) }

  val head = remember { FocusRequester() }
  val tail = remember { FocusRequester() }

  val currentContainer by rememberUpdatedState(container)

  Box(
    modifier =
      Modifier.focusRequester(head).onFocusChanged {
        if (it.isFocused && !ownFocusRequest) {
          val htmlHead = currentContainer.firstElementChild
          if (htmlHead != null) {
            focusManager.clearFocus(force = true)
            htmlHead.unsafeCast<HTMLElement>().focus()
          } else {
            ownFocusRequest = true
            tail.requestFocus()
            ownFocusRequest = false
            focusManager.moveFocus(FocusDirection.Next)
          }
        }
      }
  )

  Box(
    modifier =
      Modifier.focusRequester(tail).onFocusChanged {
        if (it.isFocused && !ownFocusRequest) {
          val htmlTail = currentContainer.lastElementChild
          if (htmlTail != null) {
            focusManager.clearFocus(force = true)
            htmlTail.unsafeCast<HTMLElement>().focus()
          } else {
            ownFocusRequest = true
            head.requestFocus()
            ownFocusRequest = false
            focusManager.moveFocus(FocusDirection.Previous)
          }
        }
      }
  )
}
