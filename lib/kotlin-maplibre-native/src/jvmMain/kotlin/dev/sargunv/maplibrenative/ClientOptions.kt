package dev.sargunv.maplibrenative

public class ClientOptions(name: String, version: String) :
  NativeObject(
    constructor = { LibMaplibreNative.ClientOptions_new(name, version) },
    destructor = { LibMaplibreNative.ClientOptions_delete(it) },
  ) {

  public val name: String
    get() {
      assertNotDisposed()
      return LibMaplibreNative.ClientOptions_name(nativePtr)
    }

  public val version: String
    get() {
      assertNotDisposed()
      return LibMaplibreNative.ClientOptions_version(nativePtr)
    }
}
