package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import net.tcurt.sandbox.leetcode.MaxUniqueSubarraySum.Method;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class MaxUniqueSubarraySumTest {

  @ParameterizedTest
  @EnumSource(Method.class)
  void input_1_2_3_4_5(Method method) {
    MaxUniqueSubarraySum underTest = new MaxUniqueSubarraySum(method);
    int[] input = new int[] {1, 2, 3, 4, 5};
    assertThat(underTest.maxSum(input)).isEqualTo(15);
  }

  @ParameterizedTest
  @EnumSource(Method.class)
  void input_1_1_0_1_1(Method method) {
    MaxUniqueSubarraySum underTest = new MaxUniqueSubarraySum(method);
    int[] input = new int[] {1, 1, 0, 1, 1};
    assertThat(underTest.maxSum(input)).isEqualTo(1);
  }

  @ParameterizedTest
  @EnumSource(Method.class)
  void input_1_2_min1_min2_1_0_min1(Method method) {
    MaxUniqueSubarraySum underTest = new MaxUniqueSubarraySum(method);
    int[] input = new int[] {1, 2, -1, -2, 1, 0, -1};
    assertThat(underTest.maxSum(input)).isEqualTo(3);
  }

  @ParameterizedTest
  @EnumSource(Method.class)
  void input_1_2_3_min2_100_0_min1(Method method) {
    MaxUniqueSubarraySum underTest = new MaxUniqueSubarraySum(method);
    int[] input = new int[] {1, 2, 3, -2, 100, 0, -1};
    assertThat(underTest.maxSum(input)).isEqualTo(106);
  }

  @ParameterizedTest
  @EnumSource(Method.class)
  void input_min1(Method method) {
    MaxUniqueSubarraySum underTest = new MaxUniqueSubarraySum(method);
    int[] input = new int[] {-1};
    assertThat(underTest.maxSum(input)).isEqualTo(-1);
  }

  @ParameterizedTest
  @EnumSource(Method.class)
  void input_min1_1(Method method) {
    MaxUniqueSubarraySum underTest = new MaxUniqueSubarraySum(method);
    int[] input = new int[] {-1, 1};
    assertThat(underTest.maxSum(input)).isEqualTo(1);
  }
}
