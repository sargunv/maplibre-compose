package dev.sargunv.maplibrecompose.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import dev.sargunv.maplibrecompose.core.CameraPosition
import io.github.dellisd.spatialk.geojson.Position

private const val KEY_BEARING = "bearing"
private const val KEY_LATITUDE = "latitude"
private const val KEY_LONGITUDE = "longitude"
private const val KEY_TILT = "tilt"
private const val KEY_ZOOM = "zoom"
private const val KEY_PADDING_START = "padding_start"
private const val KEY_PADDING_TOP = "padding_top"
private const val KEY_PADDING_END = "padding_end"
private const val KEY_PADDING_BOTTOM = "padding_bottom"

internal fun getCameraStateSaver(
  layoutDirection: LayoutDirection
): Saver<CameraState, Map<String, Any>> =
  object : Saver<CameraState, Map<String, Any>> {
    override fun SaverScope.save(value: CameraState): Map<String, Any> {
      val position = value.position
      return mapOf(
        KEY_BEARING to position.bearing,
        KEY_LATITUDE to position.target.latitude,
        KEY_LONGITUDE to position.target.longitude,
        KEY_TILT to position.tilt,
        KEY_ZOOM to position.zoom,
        KEY_PADDING_START to position.padding.calculateStartPadding(layoutDirection),
        KEY_PADDING_TOP to position.padding.calculateTopPadding().value,
        KEY_PADDING_END to position.padding.calculateEndPadding(layoutDirection),
        KEY_PADDING_BOTTOM to position.padding.calculateBottomPadding().value,
      )
    }

    override fun restore(value: Map<String, Any>) =
      CameraState(
        CameraPosition(
          bearing = value[KEY_BEARING] as Double,
          target =
            Position(
              latitude = value[KEY_LATITUDE] as Double,
              longitude = value[KEY_LONGITUDE] as Double,
            ),
          tilt = value[KEY_TILT] as Double,
          zoom = value[KEY_ZOOM] as Double,
          padding =
            PaddingValues(
              start = (value[KEY_PADDING_START] as Float).dp,
              top = (value[KEY_PADDING_TOP] as Float).dp,
              end = (value[KEY_PADDING_END] as Float).dp,
              bottom = (value[KEY_PADDING_BOTTOM] as Float).dp,
            ),
        )
      )
  }
