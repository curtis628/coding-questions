package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import net.tcurt.sandbox.Method;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class ThreeSumTest {
  public static final String[] METHODS = {"BRUTE_FORCE", "BINARY_SEARCH", "TWO_POINTERS"};

  static Stream<Method> methodProvider() {
    return Stream.of(Method.BRUTE_FORCE, Method.TWO_POINTERS);
  }

  @ParameterizedTest
  @MethodSource("methodProvider")
  void case1(Method method) {
    ThreeSum threeSum = new ThreeSum(method);
    int[] input = new int[] {-1, 0, 1, 2, -1, -4};
    assertThat(threeSum.threeSum(input))
        .containsExactlyInAnyOrder(
            List.of(-1, -1, 2), // option 1
            List.of(-1, 0, 1)); // option 2
  }

  @ParameterizedTest
  @MethodSource("methodProvider")
  void case2(Method method) {
    ThreeSum threeSum = new ThreeSum(method);
    int[] input = new int[] {-2, 0, 1, 1, 2};
    assertThat(threeSum.threeSum(input))
        .containsExactlyInAnyOrder(
            List.of(-2, 0, 2), // option 1
            List.of(-2, 1, 1)); // option 2
  }

  @ParameterizedTest
  @MethodSource("methodProvider")
  void noSums(Method method) {
    ThreeSum threeSum = new ThreeSum(method);
    int[] input = new int[] {0, 1, 1};
    assertThat(threeSum.threeSum(input)).isEmpty();
  }

  @ParameterizedTest
  @MethodSource("methodProvider")
  void threeZeros(Method method) {
    ThreeSum threeSum = new ThreeSum(method);
    int[] input = new int[] {0, 0, 0};
    assertThat(threeSum.threeSum(input)).containsExactly(List.of(0, 0, 0));
  }

  @ParameterizedTest
  @MethodSource("methodProvider")
  void fourZeros(Method method) {
    ThreeSum threeSum = new ThreeSum(method);
    int[] input = new int[] {0, 0, 0, 0};
    assertThat(threeSum.threeSum(input)).containsExactly(List.of(0, 0, 0));
  }
}
