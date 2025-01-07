package dev.sargunv.maplibrecompose.material3

import androidx.compose.ui.text.intl.Locale
import dev.sargunv.maplibrecompose.material3.controls.ScaleBarMeasure
import dev.sargunv.maplibrecompose.material3.controls.ScaleBarMeasure.*
import dev.sargunv.maplibrecompose.material3.controls.ScaleBarMeasures

/** user system preference for the scale bar measure, if any */
internal expect fun scaleBareMeasurePreference(): ScaleBarMeasure?

/**
 * default scale bar measures to use, depending on the user's locale (or system preferences, if
 * available)
 */
internal fun defaultScaleBarMeasures(): ScaleBarMeasures =
  scaleBareMeasurePreference()?.let { ScaleBarMeasures(it) }
    ?: defaultScaleBarMeasures(Locale.current.region)


/** default scale bar measure to use, depending on the locale */
internal fun defaultScaleBarMeasures(country: String?): ScaleBarMeasures {
  if (!country.isNullOrEmpty()) {
    return when (country) {
      // United states and its unincorporated territories
      "US" -> ScaleBarMeasures(FeetAndMiles, Metric)
      "AS", "GU", "MP", "PR", "VI" -> ScaleBarMeasures(FeetAndMiles, Metric)

      // former United states territories / Compact of Free Association
      "FM", "MH", "PW" -> ScaleBarMeasures(Metric, FeetAndMiles)

      // United kingdom with its overseas territories and crown dependencies
      "GB" -> ScaleBarMeasures(YardsAndMiles, Metric)
      "AI", "BM", "FK", "GG", "GI", "GS", "IM", "IO", "JE", "KY", "MS", "PN", "SH", "TC", "VG" ->
        ScaleBarMeasures(YardsAndMiles, Metric)

      // former British overseas territories / colonies
      "BS", "BZ", "GD", "KN", "VC" -> ScaleBarMeasures(Metric, YardsAndMiles)

      // Myanmar
      "MM" -> ScaleBarMeasures(Metric, YardsAndMiles)
      // Liberia
      "LR" -> ScaleBarMeasures(Metric, FeetAndMiles)

      else -> ScaleBarMeasures(Metric)
    }
  }
  return ScaleBarMeasures(Metric)
}
