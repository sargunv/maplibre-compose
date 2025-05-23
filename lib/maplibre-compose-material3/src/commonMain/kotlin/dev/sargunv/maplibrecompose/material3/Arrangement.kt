package dev.sargunv.maplibrecompose.material3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

internal fun Arrangement.Horizontal.reversed(): Arrangement.Horizontal {
  val original = this

  return object : Arrangement.Horizontal {
    override fun Density.arrange(
      totalSize: Int,
      sizes: IntArray,
      layoutDirection: LayoutDirection,
      outPositions: IntArray,
    ) {
      with(original) { arrange(totalSize, sizes, layoutDirection.reversed(), outPositions) }
    }
  }
}

private fun LayoutDirection.reversed() =
  when (this) {
    LayoutDirection.Ltr -> LayoutDirection.Rtl
    LayoutDirection.Rtl -> LayoutDirection.Ltr
  }
