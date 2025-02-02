package dev.sargunv.maplibrecompose.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dev.sargunv.maplibrecompose.core.Style
import dev.sargunv.maplibrecompose.core.source.AttributionLink
import dev.sargunv.maplibrecompose.core.source.Source

@Composable
public fun rememberStyleState(): StyleState {
  return remember { StyleState() }
}

public class StyleState internal constructor() {
  private var style: Style? = null

  internal fun attach(style: Style?) {
    if (this.style != style) {
      this.style = style
    }
  }

  public fun queryAttributionLinks(): List<AttributionLink> {
    // TODO expose this as State somehow?
    return getStyleIfLoaded()?.getSources()?.flatMap { it.attributionLinks } ?: emptyList()
  }

  /**
   * Retrieves all sources from the style.
   *
   * @return A list of sources, or an empty list if the style is not fully loaded or has no sources.
   */
  public fun getSources(): List<Source> = getStyleIfLoaded()?.getSources() ?: emptyList()

  /**
   * Retrieves a source by its [id].
   *
   * @param id The ID of the source to retrieve.
   * @return The source with the specified ID, or null if no such source exists, or the style is not
   *   fully loaded.
   */
  public fun getSource(id: String): Source? = getStyleIfLoaded()?.getSource(id)

  private fun getStyleIfLoaded(): Style? {
    return if (style?.isLoaded == true) style else null
  }
}
