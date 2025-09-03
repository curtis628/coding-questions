package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class NumberOfZeroFilledSubarraysTest {
  private NumberOfZeroFilledSubarrays underTest = new NumberOfZeroFilledSubarrays();

  @Test
  void input_13002004_is_6() {
    int[] input = new int[] {1, 3, 0, 0, 2, 0, 0, 4};
    assertThat(underTest.zeroFilledSubarray(input)).isEqualTo(6);
  }

  @Test
  void input_000200_is_9() {
    int[] input = new int[] {0, 0, 0, 2, 0, 0};
    assertThat(underTest.zeroFilledSubarray(input)).isEqualTo(9);
  }

  @Test
  void input_2_10_2019_is_0() {
    int[] input = new int[] {2, 10, 2019};
    assertThat(underTest.zeroFilledSubarray(input)).isEqualTo(0);
  }

  @Test
  void giant() {
    int[] input = new int[100000];
    assertThat(underTest.zeroFilledSubarray(input)).isEqualTo(5000050000L);
  }
}
