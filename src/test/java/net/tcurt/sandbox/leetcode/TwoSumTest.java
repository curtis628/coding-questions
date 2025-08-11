package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class TwoSumTest {
  private TwoSum underTest = new TwoSum();

  @Test
  void case1() {
    int[] input = new int[] {2, 7, 11, 15};
    assertThat(underTest.twoSum(input, 9)).containsExactlyInAnyOrder(0, 1);
  }

  @Test
  void case2() {
    int[] input = new int[] {3, 2, 4};
    assertThat(underTest.twoSum(input, 6)).containsExactlyInAnyOrder(1, 2);
  }

  @Test
  void case3() {
    int[] input = new int[] {3, 3};
    assertThat(underTest.twoSum(input, 6)).containsExactlyInAnyOrder(0, 1);
  }
}
