package dev.sargunv.maplibrecompose.material3

import dev.sargunv.maplibrecompose.material3.controls.ScaleBarMeasure

internal actual fun scaleBareMeasurePreference(): ScaleBarMeasure? = null
  /* TODO iOS/Mac developer:
  when (NSLocale.currentLocale.measurementSystem) {
    Locale.MeasurementSystem.metric -> ScaleBarMeasure.Metric
    Locale.MeasurementSystem.uk -> ScaleBarMeasure.ImperialAndMetric
    Locale.MeasurementSystem.us -> ScaleBarMeasure.Imperial
    else -> ScaleBarMeasure.ImperialAndMetric
  }
  */
