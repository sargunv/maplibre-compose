package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.source.Source
import dev.sargunv.maplibrecompose.expressions.ast.CompiledExpression
import dev.sargunv.maplibrecompose.expressions.value.FloatValue
import dev.sargunv.maplibrecompose.expressions.value.MillisecondsValue
import dev.sargunv.maplibrecompose.expressions.value.RasterResampling

internal expect class RasterLayer(id: String, source: Source) : Layer {
  val source: Source

  fun setRasterOpacity(opacity: CompiledExpression<FloatValue>)

  fun setRasterHueRotate(hueRotate: CompiledExpression<FloatValue>)

  fun setRasterBrightnessMin(brightnessMin: CompiledExpression<FloatValue>)

  fun setRasterBrightnessMax(brightnessMax: CompiledExpression<FloatValue>)

  fun setRasterSaturation(saturation: CompiledExpression<FloatValue>)

  fun setRasterContrast(contrast: CompiledExpression<FloatValue>)

  fun setRasterResampling(resampling: CompiledExpression<RasterResampling>)

  fun setRasterFadeDuration(fadeDuration: CompiledExpression<MillisecondsValue>)
}
