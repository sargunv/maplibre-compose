package dev.sargunv.maplibrenative

import jnr.ffi.Pointer

public class ClientOptions : NativeObject(C::new, C::delete) {

  public var name: String
    get() {
      checkNotDisposed()
      return C.name(nativePtr)
    }
    set(value) {
      checkNotDisposed()
      C.setName(nativePtr, value)
    }

  public var version: String
    get() {
      checkNotDisposed()
      return C.version(nativePtr)
    }
    set(value) {
      checkNotDisposed()
      C.setVersion(nativePtr, value)
    }

  override fun toString(): String {
    return "ClientOptions(name='$name', version='$version')"
  }

  internal interface C {
    fun new(): Pointer

    fun delete(nativePtr: Pointer)

    fun setName(nativePtr: Pointer, name: String)

    fun name(nativePtr: Pointer): String

    fun setVersion(nativePtr: Pointer, version: String)

    fun version(nativePtr: Pointer): String

    companion object : C by loadMaplibreNative(ClientOptions::class.simpleName!!)
  }
}
