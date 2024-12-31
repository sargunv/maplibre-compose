package dev.sargunv.maplibrecompose.core

import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.LayoutDirection
import dev.sargunv.maplibrecompose.expressions.ast.CompiledExpression
import dev.sargunv.maplibrecompose.expressions.value.BooleanValue
import dev.sargunv.maplibrejs.AttributionControl
import dev.sargunv.maplibrejs.LogoControl
import dev.sargunv.maplibrejs.Map
import dev.sargunv.maplibrejs.NavigationControl
import dev.sargunv.maplibrejs.NavigationControlOptions
import dev.sargunv.maplibrejs.ScaleControl
import io.github.dellisd.spatialk.geojson.BoundingBox
import io.github.dellisd.spatialk.geojson.Feature
import io.github.dellisd.spatialk.geojson.Position
import kotlin.time.Duration

internal class JsMap(internal val map: Map) : StandardMaplibreMap {

  private var lastStyleUri: String = ""

  override fun setStyleUri(styleUri: String) {
    if (styleUri == lastStyleUri) return
    lastStyleUri = styleUri
    map.setStyle(styleUri)
  }

  override fun setDebugEnabled(enabled: Boolean) {
    map.showCollisionBoxes = enabled
    map.showPadding = enabled
    map.showTileBoundaries = enabled
  }

  override fun setMinPitch(minPitch: Double) {
    map.setMinPitch(minPitch)
  }

  override fun setMaxPitch(maxPitch: Double) {
    map.setMaxPitch(maxPitch)
  }

  override fun setMinZoom(minZoom: Double) {
    map.setMinZoom(minZoom)
  }

  override fun setMaxZoom(maxZoom: Double) {
    map.setMaxZoom(maxZoom)
  }

  override fun getVisibleBoundingBox(): BoundingBox {
    return BoundingBox(0.0, 0.0, 0.0, 0.0) // TODO
  }

  override fun getVisibleRegion(): VisibleRegion {
    // TODO
    return VisibleRegion(
      Position(0.0, 0.0),
      Position(0.0, 0.0),
      Position(0.0, 0.0),
      Position(0.0, 0.0),
    )
  }

  override fun setMaximumFps(maximumFps: Int) {
    // not supported on web
  }

  override fun setGestureSettings(value: GestureSettings) {
    if (value.isTiltGesturesEnabled) {
      map.touchPitch.enable()
    } else {
      map.touchPitch.disable()
    }

    if (value.isRotateGesturesEnabled) {
      map.dragRotate.enable()
      map.keyboard.enableRotation()
      map.touchZoomRotate.enableRotation()
    } else {
      map.dragRotate.disable()
      map.keyboard.disableRotation()
      map.touchZoomRotate.disableRotation()
    }

    if (value.isScrollGesturesEnabled) {
      map.dragPan.enable()
    } else {
      map.dragPan.disable()
    }

    if (value.isZoomGesturesEnabled) {
      map.doubleClickZoom.enable()
      map.scrollZoom.enable()
      map.touchZoomRotate.enable()
    } else {
      map.doubleClickZoom.disable()
      map.scrollZoom.disable()
      map.touchZoomRotate.disable()
    }

    if (value.isKeyboardGesturesEnabled) {
      map.keyboard.enable()
    } else {
      map.keyboard.disable()
    }
  }

  private var compassPosition: String? = null
  private var logoPosition: String? = null
  private var scalePosition: String? = null
  private var attributionPosition: String? = null

  private val navigationControl = NavigationControl(NavigationControlOptions(visualizePitch = true))
  private val logoControl = LogoControl()
  private val scaleControl = ScaleControl()
  private val attributionControl = AttributionControl()

  override fun setOrnamentSettings(value: OrnamentSettings) {
    val layoutDir = LayoutDirection.Ltr // TODO: Get from composition

    val desiredCompassPosition =
      if (value.isCompassEnabled) value.compassAlignment.toControlPosition(layoutDir) else null
    val desiredLogoPosition =
      if (value.isLogoEnabled) value.logoAlignment.toControlPosition(layoutDir) else null
    val desiredScalePosition =
      if (value.isScaleBarEnabled) value.scaleBarAlignment.toControlPosition(layoutDir) else null
    val desiredAttributionPosition =
      if (value.isAttributionEnabled) value.attributionAlignment.toControlPosition(layoutDir)
      else null

    if (compassPosition != desiredCompassPosition) {
      if (desiredCompassPosition == null) map.removeControl(navigationControl)
      else map.addControl(navigationControl, desiredCompassPosition)
      compassPosition = desiredCompassPosition
    }

    if (logoPosition != desiredLogoPosition) {
      if (desiredLogoPosition == null) map.removeControl(logoControl)
      else map.addControl(logoControl, desiredLogoPosition)
      logoPosition = desiredLogoPosition
    }

    if (scalePosition != desiredScalePosition) {
      if (desiredScalePosition == null) map.removeControl(scaleControl)
      else map.addControl(scaleControl, desiredScalePosition)
      scalePosition = desiredScalePosition
    }

    if (attributionPosition != desiredAttributionPosition) {
      if (desiredAttributionPosition == null) map.removeControl(attributionControl)
      else map.addControl(attributionControl, desiredAttributionPosition)
      attributionPosition = desiredAttributionPosition
    }
  }

  override fun getCameraPosition(): CameraPosition {
    return CameraPosition() // TODO
  }

  override fun setCameraPosition(cameraPosition: CameraPosition) {
    // TODO
  }

  override suspend fun animateCameraPosition(finalPosition: CameraPosition, duration: Duration) {
    // TODO
  }

  override fun positionFromScreenLocation(offset: DpOffset): Position {
    return Position(0.0, 0.0) // TODO
  }

  override fun screenLocationFromPosition(position: Position): DpOffset {
    return DpOffset.Zero // TODO
  }

  override fun queryRenderedFeatures(
    offset: DpOffset,
    layerIds: Set<String>?,
    predicate: CompiledExpression<BooleanValue>?,
  ): List<Feature> {
    return emptyList() // TODO
  }

  override fun queryRenderedFeatures(
    rect: DpRect,
    layerIds: Set<String>?,
    predicate: CompiledExpression<BooleanValue>?,
  ): List<Feature> {
    return emptyList() // TODO
  }

  override fun metersPerDpAtLatitude(latitude: Double): Double {
    return 0.0 // TODO
  }
}
