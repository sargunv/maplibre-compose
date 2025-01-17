package dev.sargunv.maplibrecompose.material3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import kotlin.test.Test
import kotlin.test.assertEquals

class AlignmentKtTest {

  @Test
  fun alignmentHorizontal() {
    assertEquals(Alignment.Start, Alignment.TopStart.horizontal)
    assertEquals(Alignment.Start, Alignment.CenterStart.horizontal)
    assertEquals(Alignment.Start, Alignment.BottomStart.horizontal)

    assertEquals(Alignment.CenterHorizontally, Alignment.TopCenter.horizontal)
    assertEquals(Alignment.CenterHorizontally, Alignment.Center.horizontal)
    assertEquals(Alignment.CenterHorizontally, Alignment.BottomCenter.horizontal)

    assertEquals(Alignment.End, Alignment.TopEnd.horizontal)
    assertEquals(Alignment.End, Alignment.CenterEnd.horizontal)
    assertEquals(Alignment.End, Alignment.BottomEnd.horizontal)
  }

  @Test
  fun alignmentHorizontalBias() {
    assertEquals(BiasAlignment.Horizontal(0.75f), BiasAlignment(0.75f, 0.0f).horizontal)
    assertEquals(BiasAlignment.Horizontal(-0.3f), BiasAlignment(-0.3f, 0.0f).horizontal)
  }

  @Test
  fun alignmentVertical() {
    assertEquals(Alignment.Top, Alignment.TopStart.vertical)
    assertEquals(Alignment.Top, Alignment.TopCenter.vertical)
    assertEquals(Alignment.Top, Alignment.TopEnd.vertical)

    assertEquals(Alignment.CenterVertically, Alignment.CenterStart.vertical)
    assertEquals(Alignment.CenterVertically, Alignment.Center.vertical)
    assertEquals(Alignment.CenterVertically, Alignment.CenterEnd.vertical)

    assertEquals(Alignment.Bottom, Alignment.BottomStart.vertical)
    assertEquals(Alignment.Bottom, Alignment.BottomCenter.vertical)
    assertEquals(Alignment.Bottom, Alignment.BottomEnd.vertical)
  }

  @Test
  fun alignmentVerticalBias() {
    assertEquals(BiasAlignment.Vertical(0.75f), BiasAlignment(0.0f, 0.75f).vertical)
    assertEquals(BiasAlignment.Vertical(-0.3f), BiasAlignment(0.0f, -0.3f).vertical)
  }

  @Test
  fun horizontalArrangement() {
    assertEquals(Arrangement.Start, Alignment.Start.toArrangement())
    assertEquals(Arrangement.Center, Alignment.CenterHorizontally.toArrangement())
    assertEquals(Arrangement.End, Alignment.End.toArrangement())
  }

  @Test
  fun verticalArrangement() {
    assertEquals(Arrangement.Top, Alignment.Top.toArrangement())
    assertEquals(Arrangement.Center, Alignment.CenterVertically.toArrangement())
    assertEquals(Arrangement.Bottom, Alignment.Bottom.toArrangement())
  }
}
