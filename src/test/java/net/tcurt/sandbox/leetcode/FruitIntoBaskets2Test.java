package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class FruitIntoBaskets2Test {
  private FruitIntoBaskets2 underTest = new FruitIntoBaskets2();

  @Test
  void fruits_4_2_5_baskets_3_5_4_is_1() {
    int[] fruits = new int[] {4, 2, 5};
    int[] baskets = new int[] {3, 5, 4};
    assertThat(underTest.numOfUnplacedFruits(fruits, baskets)).isEqualTo(1);
  }

  @Test
  void fruits_3_6_1_baskets_6_4_7_is_0() {
    int[] fruits = new int[] {3, 6, 1};
    int[] baskets = new int[] {6, 4, 7};
    assertThat(underTest.numOfUnplacedFruits(fruits, baskets)).isEqualTo(0);
  }
}
