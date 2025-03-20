package dev.sargunv.maplibrecompose.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import dev.sargunv.maplibrecompose.core.Style
import dev.sargunv.maplibrecompose.core.source.Source

/** Remember a new [StyleState]. */
@Composable
public fun rememberStyleState(): StyleState {
  return remember { StyleState() }
}

/** Use this class to access information about the style, such as sources and layers. */
public class StyleState internal constructor() {
  private var style: Style? = null

  public val sources: List<Source>
    get() = sourcesState.value

  private val sourcesState = mutableStateOf(emptyList<Source>())

  private val styleListener = object : Style.Callbacks {
    override fun onSourceAdded(source: Source) { updateSources() }
    override fun onSourceRemoved(source: Source) { updateSources() }
  }

  internal fun attach(style: Style?) {
    if (this.style != style) {
      // detach previous
      this.style?.setListener(null)
      // attach new
      this.style = style
      style?.setListener(styleListener)
      updateSources()
    }
  }

  private fun updateSources() {
    sourcesState.value = style?.getSources().orEmpty()
  }

  /**
   * Retrieves a source by its [id].
   *
   * @param id The ID of the source to retrieve.
   * @return The source with the specified ID, or null if no such source exists.
   */
  public fun getSource(id: String): Source? = style?.getSource(id)
}
