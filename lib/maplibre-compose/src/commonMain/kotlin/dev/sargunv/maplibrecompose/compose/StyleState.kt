package dev.sargunv.maplibrecompose.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import dev.sargunv.maplibrecompose.compose.engine.StyleNode
import dev.sargunv.maplibrecompose.core.source.Source

/** Remember a new [StyleState]. */
@Composable
public fun rememberStyleState(): StyleState {
  return remember { StyleState() }
}

/** Use this class to access information about the style, such as sources and layers. */
public class StyleState internal constructor() {
  private var styleNode: StyleNode? = null

  public val sources: List<Source>
    get() = sourcesState.value

  private val sourcesState = mutableStateOf(emptyList<Source>())

  internal fun attach(styleNode: StyleNode?) {
    if (this.styleNode != styleNode) {
      this.styleNode?.onEndChangesCallback = null
      this.styleNode = styleNode
      this.styleNode?.onEndChangesCallback = { updateSources() }
      updateSources()
    }
  }

  internal fun updateSources() {
    sourcesState.value = styleNode?.style?.getSources().orEmpty()
  }

  /**
   * Retrieves a source by its [id].
   *
   * @param id The ID of the source to retrieve.
   * @return The source with the specified ID, or null if no such source exists.
   */
  public fun getSource(id: String): Source? = styleNode?.style?.getSource(id)
}
