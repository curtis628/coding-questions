package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import net.tcurt.sandbox.leetcode.BitwiseORsOfSubarrays.Method;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class BitwiseORsOfSubarraysTest {

  @ParameterizedTest
  @EnumSource(Method.class)
  void input_0_is_1(Method method) {
    BitwiseORsOfSubarrays underTest = new BitwiseORsOfSubarrays(method);
    int[] input = new int[] {0};
    assertThat(underTest.subarrayBitwiseORs(input)).isEqualTo(1);
  }

  @ParameterizedTest
  @EnumSource(Method.class)
  void input_1_1_2_is_3(Method method) {
    BitwiseORsOfSubarrays underTest = new BitwiseORsOfSubarrays(method);
    int[] input = new int[] {1, 1, 2};
    assertThat(underTest.subarrayBitwiseORs(input)).isEqualTo(3);
  }

  @ParameterizedTest
  @EnumSource(Method.class)
  void input_1_2_4_is_6(Method method) {
    BitwiseORsOfSubarrays underTest = new BitwiseORsOfSubarrays(method);
    int[] input = new int[] {1, 2, 4};
    assertThat(underTest.subarrayBitwiseORs(input)).isEqualTo(6);
  }
}
