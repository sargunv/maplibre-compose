package dev.sargunv.maplibrenative

import jnr.ffi.LibraryLoader

public inline fun <reified T> loadMaplibreNativeFfi(name: String): T {
  return LibraryLoader.create(T::class.java)
    .mapper { functionName, _ -> "MLN_${name}_${functionName}" }
    .load("maplibre-native-ffi")
}
