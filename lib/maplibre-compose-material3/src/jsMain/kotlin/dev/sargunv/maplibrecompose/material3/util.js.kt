package dev.sargunv.maplibrecompose.material3

internal actual fun defaultLanguage(): String? =
  new Intl.Locale(navigator.language).language

internal actual fun defaultCountry(): String? =
  new Intl.Locale(navigator.language).region
