package dev.sargunv.maplibrecompose.material3

import dev.sargunv.maplibrecompose.material3.controls.ScaleBarMeasure

internal actual fun defaultLanguage(): String? =
  new Intl.Locale(navigator.language).language

internal actual fun defaultCountry(): String? =
  new Intl.Locale(navigator.language).region
