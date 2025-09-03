package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class RearrangingFruitsTest {
  private RearrangingFruits rearrangingFruits = new RearrangingFruits();

  @Test
  void b1_4_2_2_2_b2_1_4_1_2_is_1() {
    int[] basket1 = new int[] {4, 2, 2, 2};
    int[] basket2 = new int[] {1, 4, 1, 2};
    assertThat(rearrangingFruits.minCost(basket1, basket2)).isEqualTo(1);
  }

  @Test
  void b1_1_4_1_2_b2_4_2_2_2_is_1() {
    int[] basket1 = new int[] {1, 4, 1, 2};
    int[] basket2 = new int[] {4, 2, 2, 2};
    assertThat(rearrangingFruits.minCost(basket1, basket2)).isEqualTo(1);
  }

  @Test
  void b1_2_3_4_1_b2_3_2_5_1_is_min1() {
    int[] basket1 = new int[] {2, 3, 4, 1};
    int[] basket2 = new int[] {3, 2, 5, 1};
    assertThat(rearrangingFruits.minCost(basket1, basket2)).isEqualTo(-1);
  }

  @Test
  void b1_4_4_4_4_3_b2_5_5_5_5_3_is_8() {
    int[] basket1 = new int[] {4, 4, 4, 4, 3};
    int[] basket2 = new int[] {5, 5, 5, 5, 3};
    assertThat(rearrangingFruits.minCost(basket1, basket2)).isEqualTo(8);
  }
}
