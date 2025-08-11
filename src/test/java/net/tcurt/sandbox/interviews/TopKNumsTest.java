package net.tcurt.sandbox.interviews;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import net.tcurt.sandbox.Method;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class TopKNumsTest {

  static Stream<Method> methodProvider() {
    return Stream.of(Method.HEAP, Method.OPTIMIZED);
  }

  @ParameterizedTest
  @MethodSource("methodProvider")
  void input_111223_k2_is_12(Method method) {
    TopKNums underTest = new TopKNums(method);
    int[] input = new int[] {1, 1, 1, 2, 2, 3};
    int[] expected = new int[] {1, 2};
    assertThat(underTest.topKnums(input, 2)).containsExactlyInAnyOrder(expected);
  }

  @ParameterizedTest
  @MethodSource("methodProvider")
  void input_4444556_k1_is_4(Method method) {
    TopKNums underTest = new TopKNums(method);
    int[] input = new int[] {4, 4, 4, 4, 5, 5, 6};
    int[] expected = new int[] {4};
    assertThat(underTest.topKnums(input, 1)).containsExactlyInAnyOrder(expected);
  }
}
