package dev.sargunv.maplibrecompose.core.layer

import cocoapods.MapLibre.MLNRasterStyleLayer
import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.core.util.toNSExpression
import dev.sargunv.maplibrecompose.expressions.ast.CompiledExpression
import dev.sargunv.maplibrecompose.expressions.value.FloatValue
import dev.sargunv.maplibrecompose.expressions.value.MillisecondsValue
import dev.sargunv.maplibrecompose.expressions.value.RasterResampling

internal actual class RasterLayer actual constructor(id: String, actual val source: Source) :
  Layer() {

  override val impl = MLNRasterStyleLayer(id, source.impl)

  actual fun setRasterOpacity(opacity: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setRasterOpacity")
    if (!isUnloaded) impl.rasterOpacity = opacity.toNSExpression()
  }

  actual fun setRasterHueRotate(hueRotate: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setRasterHueRotate")
    if (!isUnloaded) impl.rasterHueRotation = hueRotate.toNSExpression()
  }

  actual fun setRasterBrightnessMin(brightnessMin: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setRasterBrightnessMin")
    if (!isUnloaded) impl.minimumRasterBrightness = brightnessMin.toNSExpression()
  }

  actual fun setRasterBrightnessMax(brightnessMax: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setRasterBrightnessMax")
    if (!isUnloaded) impl.maximumRasterBrightness = brightnessMax.toNSExpression()
  }

  actual fun setRasterSaturation(saturation: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setRasterSaturation")
    if (!isUnloaded) impl.rasterSaturation = saturation.toNSExpression()
  }

  actual fun setRasterContrast(contrast: CompiledExpression<FloatValue>) {
    warnIfUnloaded("setRasterContrast")
    if (!isUnloaded) impl.rasterContrast = contrast.toNSExpression()
  }

  actual fun setRasterResampling(resampling: CompiledExpression<RasterResampling>) {
    warnIfUnloaded("setRasterResampling")
    if (!isUnloaded) impl.rasterResamplingMode = resampling.toNSExpression()
  }

  actual fun setRasterFadeDuration(fadeDuration: CompiledExpression<MillisecondsValue>) {
    warnIfUnloaded("setRasterFadeDuration")
    if (!isUnloaded) impl.rasterFadeDuration = fadeDuration.toNSExpression()
  }
}
