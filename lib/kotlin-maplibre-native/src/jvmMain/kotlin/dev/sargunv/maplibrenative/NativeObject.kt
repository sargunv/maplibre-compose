package dev.sargunv.maplibrenative

import java.lang.ref.Cleaner
import jnr.ffi.Pointer

public sealed class NativeObject(constructor: () -> Pointer, destructor: (Pointer) -> Unit) :
  AutoCloseable {

  protected val nativePtr: Pointer = constructor()

  @Suppress("LeakingThis") private val cleanable = cleaner.register(this) { destructor(nativePtr) }

  private var disposed = false

  protected fun checkNotDisposed() {
    if (disposed) error("${this::class.simpleName} is disposed")
  }

  override fun close() {
    cleanable.clean()
    disposed = true
  }

  private companion object {
    val cleaner: Cleaner = Cleaner.create()
  }
}
