package dev.sargunv.maplibrecompose.material3

import dev.sargunv.maplibrecompose.material3.controls.ScaleBarMeasure
import platform.Foundation.NSLocale
import platform.Foundation.countryCode
import platform.Foundation.currentLocale
import platform.Foundation.languageCode

internal actual fun defaultLanguage(): String? =
  NSLocale.currentLocale.languageCode

internal actual fun defaultCountry(): String? =
  NSLocale.currentLocale.countryCode

internal actual fun scaleBareMeasurePreference(): ScaleBarMeasure? =
  null
  /* TODO iOS/Mac developer:
  when (NSLocale.currentLocale.measurementSystem) {
    Locale.MeasurementSystem.metric -> ScaleBarMeasure.METRIC
    Locale.MeasurementSystem.uk -> ScaleBarMeasure.IMPERIAL_AND_METRIC
    Locale.MeasurementSystem.us -> ScaleBarMeasure.IMPERIAL
    else -> ScaleBarMeasure.IMPERIAL_AND_METRIC
  }
  */
