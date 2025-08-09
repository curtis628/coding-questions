package net.tcurt.sandbox.problems;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import net.tcurt.sandbox.Method;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class TwoSumPartTwoTest {

  static Stream<Method> methodProvider() {
    return Stream.of(Method.BRUTE_FORCE, Method.BINARY_SEARCH, Method.TWO_POINTERS);
  }

  @ParameterizedTest
  @MethodSource("methodProvider")
  void case1(Method method) {
    TwoSumPartTwo underTest = new TwoSumPartTwo(method);
    int[] input = new int[] {2, 7, 11, 15};
    int[] expected = new int[] {1, 2};
    assertThat(underTest.twoSum(input, 9)).isEqualTo(expected);
  }

  @ParameterizedTest
  @MethodSource("methodProvider")
  void case2(Method method) {
    TwoSumPartTwo underTest = new TwoSumPartTwo(method);
    int[] input = new int[] {2, 3, 4};
    int[] expected = new int[] {1, 3};
    assertThat(underTest.twoSum(input, 6)).isEqualTo(expected);
  }

  @ParameterizedTest
  @MethodSource("methodProvider")
  void case3(Method method) {
    TwoSumPartTwo underTest = new TwoSumPartTwo(method);
    int[] input = new int[] {-1, 0};
    int[] expected = new int[] {1, 2};
    assertThat(underTest.twoSum(input, -1)).isEqualTo(expected);
  }

  @ParameterizedTest
  @MethodSource("methodProvider")
  void caseLonger(Method method) {
    TwoSumPartTwo underTest = new TwoSumPartTwo(method);
    int[] input = new int[] {-2, 0, 1, 1, 2, 3, 5, 6, 7, 10};
    int[] expected = new int[] {2, 9};
    assertThat(underTest.twoSum(input, 7)).isEqualTo(expected);
  }
}
