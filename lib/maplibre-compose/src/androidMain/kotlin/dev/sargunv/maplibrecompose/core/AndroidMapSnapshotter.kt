package dev.sargunv.maplibrecompose.core

import android.content.Context
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import dev.sargunv.maplibrecompose.core.util.correctedAndroidUri
import dev.sargunv.maplibrecompose.core.util.toLatLngBounds
import dev.sargunv.maplibrecompose.core.util.toMLNCameraPosition
import io.github.dellisd.spatialk.geojson.BoundingBox
import kotlin.coroutines.resume
import kotlinx.coroutines.suspendCancellableCoroutine
import org.maplibre.android.maps.Style
import org.maplibre.android.snapshotter.MapSnapshotter as MLNMapSnapshotter

internal class AndroidMapSnapshotter(
  private val context: Context,
  private val layoutDir: LayoutDirection,
  private val density: Density,
) : MapSnapshotter {

  override suspend fun snapshot(
    width: Dp,
    height: Dp,
    styleUri: String,
    region: BoundingBox?,
    cameraPosition: CameraPosition?,
    showLogo: Boolean,
  ): ImageBitmap {
    with(density) {
      val styleBuilder = Style.Builder().fromUri(styleUri.correctedAndroidUri())
      val options =
        MLNMapSnapshotter.Options(width.roundToPx(), height.roundToPx())
          .withStyleBuilder(styleBuilder)
          .withRegion(region?.toLatLngBounds())
          .withCameraPosition(cameraPosition?.toMLNCameraPosition(this, layoutDir))
          .withLogo(showLogo)

      val snapshotter = MLNMapSnapshotter(context, options)

      return suspendCancellableCoroutine { cont ->
        snapshotter.start({ snapshot -> cont.resume(snapshot.bitmap.asImageBitmap()) }) { error ->
          throw SnapshotException(error)
        }
        cont.invokeOnCancellation { snapshotter.cancel() }
      }
    }
  }
}
