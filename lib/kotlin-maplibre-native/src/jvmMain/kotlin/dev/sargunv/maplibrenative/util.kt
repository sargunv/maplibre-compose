package dev.sargunv.maplibrenative

import jnr.ffi.LibraryLoader

public inline fun <reified T> loadMaplibreNative(domain: String): T {
  return LibraryLoader.create(T::class.java)
    .mapper { functionName, _ -> "MLN_${domain}_${functionName}" }
    .load("maplibre-native-ffi")
}
