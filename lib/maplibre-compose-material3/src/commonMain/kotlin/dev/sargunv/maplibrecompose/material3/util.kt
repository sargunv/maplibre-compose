package dev.sargunv.maplibrecompose.material3

import dev.sargunv.maplibrecompose.material3.controls.ScaleBarMeasure

/** ISO 639 language code of user's locale */
internal expect fun defaultLanguage(): String?

/** ISO 3166-1 alpha-2 country code of the user's locale */
internal expect fun defaultCountry(): String?

/**
 * default scale bar measure to use, depending on the user's locale (or system preferences, if
 * available)
 */
internal fun defaultScaleBarMeasure(): ScaleBarMeasure {
  return defaultScaleBarMeasure(defaultLanguage(), defaultCountry())
}

/** default scale bar measure to use, depending on the locale */
internal fun defaultScaleBarMeasure(language: String?, country: String?): ScaleBarMeasure {
  if (!country.isNullOrEmpty()) {
    return when (country) {
      // US is famously imperial only
      "US" -> {
        ScaleBarMeasure.IMPERIAL
      }
      // Belize, Liberia, Myanmar transition slowly to metric
      "BZ", "LR", "MM",
      // US dependencies are mainly imperial, some metric in use
      "AS", "GU", "MH", "MP", "PR", "PW", "VI", "WS",
      // United Kingdom and British dependencies are mainly metric, some imperial remains
      "AG", "AI", "BM", "BS", "FK", "GB", "GD", "GG", "GI", "GS", "IM", "JE", "KN", "KY", "LC",
      "MS", "TC", "VG", "VC" -> {
        ScaleBarMeasure.IMPERIAL_AND_METRIC
      }
      // all others are metric only
      else -> {
        ScaleBarMeasure.METRIC
      }
    }
  }
  // if no country available -> make an educated guess based on the language
  if (!language.isNullOrEmpty()) {
    when (language) {
      // English (GB, US, LR and dependencies of GB, US), Burmese (MM), Samoan (WS, AS)
      "en", "my", "sm" -> {
        ScaleBarMeasure.IMPERIAL_AND_METRIC
      }
      else -> {
        ScaleBarMeasure.METRIC
      }
    }
  }
  // default to both
  return ScaleBarMeasure.IMPERIAL_AND_METRIC
}
