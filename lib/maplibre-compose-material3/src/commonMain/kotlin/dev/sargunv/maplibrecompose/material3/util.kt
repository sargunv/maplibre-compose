package dev.sargunv.maplibrecompose.material3

import androidx.compose.ui.text.intl.Locale
import dev.sargunv.maplibrecompose.material3.controls.ScaleBarMeasure

/** user system preference for the scale bar measure, if any */
internal expect fun scaleBareMeasurePreference(): ScaleBarMeasure?

/**
 * default scale bar measure to use, depending on the user's locale (or system preferences, if
 * available)
 */
internal fun defaultScaleBarMeasure(): ScaleBarMeasure =
  scaleBareMeasurePreference()
    ?: defaultScaleBarMeasure(Locale.current.language, Locale.current.region)

/** default scale bar measure to use, depending on the locale */
internal fun defaultScaleBarMeasure(language: String?, country: String?): ScaleBarMeasure {
  if (!country.isNullOrEmpty()) {
    return when (country) {
      // US is famously imperial only
      "US" -> {
        ScaleBarMeasure.Imperial
      }
      // Belize, Liberia, Myanmar transition slowly to metric
      "BZ",
      "LR",
      "MM",
      // US dependencies are mainly imperial, some metric in use
      "AS",
      "GU",
      "MH",
      "MP",
      "PR",
      "PW",
      "VI",
      "WS",
      // United Kingdom and British dependencies are mainly metric, some imperial remains
      "AG",
      "AI",
      "BM",
      "BS",
      "FK",
      "GB",
      "GD",
      "GG",
      "GI",
      "GS",
      "IM",
      "JE",
      "KN",
      "KY",
      "LC",
      "MS",
      "TC",
      "VG",
      "VC" -> {
        ScaleBarMeasure.ImperialAndMetric
      }
      // all others are metric only
      else -> {
        ScaleBarMeasure.Metric
      }
    }
  }
  // if no country available -> make an educated guess based on the language
  if (!language.isNullOrEmpty()) {
    when (language) {
      // English (GB, US, LR and dependencies of GB, US), Burmese (MM), Samoan (WS, AS)
      "en",
      "my",
      "sm" -> {
        ScaleBarMeasure.ImperialAndMetric
      }
      else -> {
        ScaleBarMeasure.Metric
      }
    }
  }
  // default to both
  return ScaleBarMeasure.ImperialAndMetric
}
