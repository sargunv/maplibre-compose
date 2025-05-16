package dev.sargunv.maplibrecompose.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import dev.sargunv.maplibrecompose.core.CameraPosition
import io.github.dellisd.spatialk.geojson.Position

internal object CameraStateSaver : Saver<CameraState, Map<String, Any>> {
  override fun SaverScope.save(value: CameraState): Map<String, Any>? {
    val position = value.position
    return mapOf(
      Keys.BEARING to position.bearing,
      Keys.LATITUDE to position.target.latitude,
      Keys.LONGITUDE to position.target.longitude,
      Keys.TILT to position.tilt,
      Keys.ZOOM to position.zoom,
      Keys.PADDING_LEFT to position.padding.calculateStartPadding(LayoutDirection.Ltr),
      Keys.PADDING_TOP to position.padding.calculateTopPadding(),
      Keys.PADDING_RIGHT to position.padding.calculateEndPadding(LayoutDirection.Ltr),
      Keys.PADDING_BOTTOM to position.padding.calculateBottomPadding(),
    )
  }

  override fun restore(value: Map<String, Any>): CameraState? {
    return CameraState(
      CameraPosition(
        bearing = value[Keys.BEARING] as Double,
        target =
          Position(
            latitude = value[Keys.LATITUDE] as Double,
            longitude = value[Keys.LONGITUDE] as Double,
          ),
        tilt = value[Keys.TILT] as Double,
        zoom = value[Keys.ZOOM] as Double,
        padding =
          PaddingValues.Absolute(
            left = value[Keys.PADDING_LEFT] as Dp,
            top = value[Keys.PADDING_TOP] as Dp,
            right = value[Keys.PADDING_RIGHT] as Dp,
            bottom = value[Keys.PADDING_BOTTOM] as Dp,
          ),
      )
    )
  }

  private object Keys {
    const val BEARING = "bearing"
    const val LATITUDE = "latitude"
    const val LONGITUDE = "longitude"
    const val TILT = "tilt"
    const val ZOOM = "zoom"
    const val PADDING_LEFT = "padding_left"
    const val PADDING_TOP = "padding_top"
    const val PADDING_RIGHT = "padding_right"
    const val PADDING_BOTTOM = "padding_bottom"
  }
}
