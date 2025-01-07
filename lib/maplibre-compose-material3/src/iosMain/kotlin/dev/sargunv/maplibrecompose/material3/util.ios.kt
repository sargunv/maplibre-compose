package dev.sargunv.maplibrecompose.material3

import dev.sargunv.maplibrecompose.material3.controls.ScaleBarMeasure

internal actual fun scaleBareMeasurePreference(): ScaleBarMeasure? = null

/*
TODO iOS/Mac developer:
internal actual fun scaleBareMeasurePreference(): ScaleBarMeasure? {
  // user locale also contains user preferences such as the measurement system
  val userlocale = NSLocale.currentLocale
  // and this locale doesn't
  val defaultLocale = NSLocale(languageCode = locale.language, languageRegion = locale.region)

  // ONLY return the user's measurement system setting if it deviates from the language+country
  // default. We do this because the whole CLDR MeasurementSystem data is less precise than what we
  // have in util.kt
  if (userLocale.measurementSystem != defaultLocale.measurementSystem) {
    return when (userlocale.measurementSystem) {
      Locale.MeasurementSystem.metric -> ScaleBarMeasure.Metric
      Locale.MeasurementSystem.uk -> ScaleBarMeasure.ImperialAndMetric
      Locale.MeasurementSystem.us -> ScaleBarMeasure.Imperial
      else -> ScaleBarMeasure.ImperialAndMetric
    }
  }
  return null
}
*/
