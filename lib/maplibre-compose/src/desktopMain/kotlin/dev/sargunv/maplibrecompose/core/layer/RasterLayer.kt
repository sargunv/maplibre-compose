package dev.sargunv.maplibrecompose.core.layer

import dev.sargunv.maplibrecompose.core.expression.EnumValue
import dev.sargunv.maplibrecompose.core.expression.Expression
import dev.sargunv.maplibrecompose.core.expression.FloatValue
import dev.sargunv.maplibrecompose.core.expression.MillisecondsValue
import dev.sargunv.maplibrecompose.core.expression.RasterResampling
import dev.sargunv.maplibrecompose.core.source.Source

internal actual class RasterLayer actual constructor(id: String, actual val source: Source) :
  Layer() {
  override val impl = TODO()

  actual fun setRasterOpacity(opacity: Expression<FloatValue>) {
    TODO()
  }

  actual fun setRasterHueRotate(hueRotate: Expression<FloatValue>) {
    TODO()
  }

  actual fun setRasterBrightnessMin(brightnessMin: Expression<FloatValue>) {
    TODO()
  }

  actual fun setRasterBrightnessMax(brightnessMax: Expression<FloatValue>) {
    TODO()
  }

  actual fun setRasterSaturation(saturation: Expression<FloatValue>) {
    TODO()
  }

  actual fun setRasterContrast(contrast: Expression<FloatValue>) {
    TODO()
  }

  actual fun setRasterResampling(resampling: Expression<EnumValue<RasterResampling>>) {
    TODO()
  }

  actual fun setRasterFadeDuration(fadeDuration: Expression<MillisecondsValue>) {
    TODO()
  }
}
