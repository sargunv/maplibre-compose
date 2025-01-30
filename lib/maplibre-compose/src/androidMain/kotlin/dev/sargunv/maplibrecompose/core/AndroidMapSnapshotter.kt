package dev.sargunv.maplibrecompose.core

import android.content.Context
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import dev.sargunv.maplibrecompose.core.util.correctedAndroidUri
import dev.sargunv.maplibrecompose.core.util.toLatLngBounds
import dev.sargunv.maplibrecompose.core.util.toMLNCameraPosition
import io.github.dellisd.spatialk.geojson.BoundingBox
import org.maplibre.android.constants.MapLibreConstants
import org.maplibre.android.maps.Style
import org.maplibre.android.snapshotter.MapSnapshot as MLNMapSnapshot
import org.maplibre.android.snapshotter.MapSnapshotter as MLNMapSnapshotter

internal class AndroidMapSnapshotter(
  private val context: Context,
  private val layoutDir: LayoutDirection,
  private val density: Density,
) : MapSnapshotter {
  private var impl: MLNMapSnapshotter? = null
  private var isStarted: Boolean = false

  override fun snapshot(
    width: Dp,
    height: Dp,
    styleUri: String,
    region: BoundingBox?,
    cameraPosition: CameraPosition?,
    showLogo: Boolean,
    localIdeographFontFamily: String?,
    pixelRatio: Float,
    callback: (MapSnapshot) -> Unit,
    errorHandler: (String) -> Unit,
  ) {
    with(density) {
      if (isStarted) return

      isStarted = true

      val styleBuilder = Style.Builder().fromUri(styleUri.correctedAndroidUri())
      val options =
        MLNMapSnapshotter.Options(width.roundToPx(), height.roundToPx())
          .withStyleBuilder(styleBuilder)
          .withRegion(region?.toLatLngBounds())
          .withCameraPosition(cameraPosition?.toMLNCameraPosition(this, layoutDir))
          .withLogo(showLogo)
          .withLocalIdeographFontFamily(localIdeographFontFamily ?: MapLibreConstants.DEFAULT_FONT)
          .withPixelRatio(pixelRatio)

      impl = MLNMapSnapshotter(context, options)
      impl?.start({ snapshot ->
        callback(snapshot.toMapSnapshot())
        isStarted = false
      }) { error ->
        errorHandler(error)
        isStarted = false
      }
    }
  }

  override fun cancel() {
    impl?.cancel()
    isStarted = false
  }

  private fun MLNMapSnapshot.toMapSnapshot() =
    MapSnapshot(bitmap.asImageBitmap(), attributions.toList(), isShowLogo)
}
