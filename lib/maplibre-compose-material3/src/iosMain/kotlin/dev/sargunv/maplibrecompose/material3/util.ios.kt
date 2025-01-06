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
    Locale.MeasurementSystem.metric -> ScaleBarMeasure.Metric
    Locale.MeasurementSystem.uk -> ScaleBarMeasure.ImperialAndMetric
    Locale.MeasurementSystem.us -> ScaleBarMeasure.Imperial
    else -> ScaleBarMeasure.ImperialAndMetric
  }
  */
