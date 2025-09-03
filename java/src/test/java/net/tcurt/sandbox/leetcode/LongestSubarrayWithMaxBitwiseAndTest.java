package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class LongestSubarrayWithMaxBitwiseAndTest {
  private LongestSubarrayWithMaxBitwiseAnd underTest = new LongestSubarrayWithMaxBitwiseAnd();

  @Test
  void input_1_2_3_3_2_2_is_2() {
    int[] input = new int[] {1, 2, 3, 3, 2, 2};
    assertThat(underTest.longestSubarray(input)).isEqualTo(2);
  }

  @Test
  void input_1_2_3_4_is_1() {
    int[] input = new int[] {1, 2, 3, 4};
    assertThat(underTest.longestSubarray(input)).isEqualTo(1);
  }

  @Test
  void input_3_3_3_1_3_3_3_3_is_4() {
    int[] input = new int[] {3, 3, 3, 1, 3, 3, 3, 3};
    assertThat(underTest.longestSubarray(input)).isEqualTo(4);
  }
}
