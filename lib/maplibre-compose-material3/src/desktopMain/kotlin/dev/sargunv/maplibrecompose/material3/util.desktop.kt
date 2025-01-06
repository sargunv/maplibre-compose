package dev.sargunv.maplibrecompose.material3

import java.util.Locale

internal actual fun defaultLanguage(): String? =
  Locale.getDefault().language

internal actual fun defaultCountry(): String? =
  Locale.getDefault().country
