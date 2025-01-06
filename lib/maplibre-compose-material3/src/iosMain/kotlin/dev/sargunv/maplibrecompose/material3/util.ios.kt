package dev.sargunv.maplibrecompose.material3

import platform.Foundation.NSLocale
import platform.Foundation.countryCode
import platform.Foundation.currentLocale
import platform.Foundation.languageCode

internal actual fun defaultLanguage(): String? =
  NSLocale.currentLocale.languageCode

internal actual fun defaultCountry(): String? =
  NSLocale.currentLocale.countryCode
