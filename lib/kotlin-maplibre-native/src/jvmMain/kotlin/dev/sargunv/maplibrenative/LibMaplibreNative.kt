package dev.sargunv.maplibrenative

import jnr.ffi.LibraryLoader
import jnr.ffi.Pointer
import org.scijava.nativelib.NativeLoader

public interface LibMaplibreNative {
  public fun ClientOptions_new(name: String, version: String): Pointer

  public fun ClientOptions_delete(nativePtr: Pointer)

  public fun ClientOptions_name(nativePtr: Pointer): String

  public fun ClientOptions_version(nativePtr: Pointer): String

  public companion object :
    LibMaplibreNative by run({
      // use org.scijava.nativelib to help us extract it from the jar
      // can remove this when we're no longer packaging the native library in the jar
      NativeLoader.loadLibrary("kotlin-maplibre-native")

      // generates an implementation of the interface that calls the native functions
      LibraryLoader.create(LibMaplibreNative::class.java).load("kotlin-maplibre-native")
    })
}
