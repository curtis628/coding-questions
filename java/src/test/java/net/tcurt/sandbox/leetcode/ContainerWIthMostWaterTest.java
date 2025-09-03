package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import net.tcurt.sandbox.leetcode.ContainerWithMostWater.Method;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class ContainerWIthMostWaterTest {

  @ParameterizedTest
  @EnumSource(Method.class)
  void case1(Method method) {
    ContainerWithMostWater underTest = new ContainerWithMostWater(method);
    int[] input = new int[] {1, 8, 6, 2, 5, 4, 8, 3, 7};
    assertThat(underTest.maxArea(input)).isEqualTo(49);
  }

  @ParameterizedTest
  @EnumSource(Method.class)
  void case2(Method method) {
    ContainerWithMostWater underTest = new ContainerWithMostWater(method);
    int[] input = new int[] {1, 8, 6, 2, 5, 4, 8, 3, 4};
    assertThat(underTest.maxArea(input)).isEqualTo(40);
  }

  @ParameterizedTest
  @EnumSource(Method.class)
  void case3(Method method) {
    ContainerWithMostWater underTest = new ContainerWithMostWater(method);
    int[] input = new int[] {1, 1};
    assertThat(underTest.maxArea(input)).isEqualTo(1);
  }
}
