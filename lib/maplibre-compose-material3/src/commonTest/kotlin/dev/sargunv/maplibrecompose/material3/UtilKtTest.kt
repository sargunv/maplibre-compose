package dev.sargunv.maplibrecompose.material3

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class UtilKtTest {
  @Test
  fun asSequenceOfPairs_of_empty_list_is_empty() {
    assertTrue(listOf<Int>().asSequenceOfPairs().toList().isEmpty())
  }

  @Test
  fun asSequenceOfPairs_of_singleton_list_is_empty() {
    assertTrue(listOf(0).asSequenceOfPairs().toList().isEmpty())
  }

  @Test
  fun asSequenceOfPairs_of_list() {
    assertEquals(listOf(0 to 1, 1 to 2, 2 to 3), listOf(0, 1, 2, 3).asSequenceOfPairs().toList())
  }
}
