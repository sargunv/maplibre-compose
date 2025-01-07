package dev.sargunv.maplibrecompose.material3

import dev.sargunv.maplibrecompose.material3.controls.ScaleBarMeasure

internal actual fun scaleBareMeasurePreference(): ScaleBarMeasure? = null

/*
TODO iOS/Mac developer:
internal actual fun scaleBareMeasurePreference(): ScaleBarMeasure? {
  // user locale also contains user preferences such as the measurement system
  val userLocale = NSLocale.currentLocale
  // and this locale doesn't
  val defaultLocale = NSLocale(languageCode = locale.language, languageRegion = locale.region)

  // ONLY return the user's measurement system setting if it deviates from the language+country
  // default, i.e. the user actually explicitly changed this setting
  if (userLocale.measurementSystem != defaultLocale.measurementSystem) {
    return when (userlocale.measurementSystem) {
      Locale.MeasurementSystem.metric -> ScaleBarMeasure.Metric
      Locale.MeasurementSystem.uk -> ScaleBarMeasure.YardsAndMiles
      Locale.MeasurementSystem.us -> ScaleBarMeasure.FeetAndMiles
      else -> null
    }
  }
  return null
}
*/
