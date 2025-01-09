package dev.sargunv.maplibrecompose.material3

import android.icu.util.LocaleData
import android.icu.util.ULocale
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import dev.sargunv.maplibrecompose.material3.controls.ScaleBarMeasurementSystem

@Composable
internal actual fun systemDefaultPrimaryMeasure(): ScaleBarMeasurementSystem? {
  if (android.os.Build.VERSION.SDK_INT < 28) return null
  val locales = LocalContext.current.resources.configuration.locales
  if (locales.isEmpty) return null
  return when (LocaleData.getMeasurementSystem(ULocale.forLocale(locales[0]))) {
    LocaleData.MeasurementSystem.SI -> ScaleBarMeasurementSystem.Metric
    LocaleData.MeasurementSystem.US -> ScaleBarMeasurementSystem.FeetAndMiles
    LocaleData.MeasurementSystem.UK -> ScaleBarMeasurementSystem.YardsAndMiles
    else -> null
  }
}
