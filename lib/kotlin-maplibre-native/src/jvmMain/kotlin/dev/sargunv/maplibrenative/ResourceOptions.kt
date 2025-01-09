package dev.sargunv.maplibrenative

import jnr.ffi.Pointer

public class ResourceOptions : NativeObject(C::new, C::delete) {

  public var assetPath: String
    get() {
      checkNotDisposed()
      return C.assetPath(nativePtr)
    }
    set(value) {
      checkNotDisposed()
      C.setAssetPath(nativePtr, value)
    }

  public var cachePath: String
    get() {
      checkNotDisposed()
      return C.cachePath(nativePtr)
    }
    set(value) {
      checkNotDisposed()
      C.setCachePath(nativePtr, value)
    }

  public var maximumCacheSize: ULong
    get() {
      checkNotDisposed()
      return C.maximumCacheSize(nativePtr).toULong()
    }
    set(value) {
      checkNotDisposed()
      C.setMaximumCacheSize(nativePtr, value.toLong())
    }

  override fun toString(): String {
    return "ResourceOptions(assetPath='$assetPath', cachePath='$cachePath', maximumCacheSize=$maximumCacheSize)"
  }

  internal interface C {
    fun new(): Pointer

    fun delete(nativePtr: Pointer)

    fun setAssetPath(nativePtr: Pointer, assetPath: String)

    fun assetPath(nativePtr: Pointer): String

    fun setCachePath(nativePtr: Pointer, cachePath: String)

    fun cachePath(nativePtr: Pointer): String

    fun setMaximumCacheSize(nativePtr: Pointer, maximumCacheSize: Long)

    fun maximumCacheSize(nativePtr: Pointer): Long

    companion object : C by loadMaplibreNative(ResourceOptions::class.simpleName!!)
  }
}
