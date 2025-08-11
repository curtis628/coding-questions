package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import net.tcurt.sandbox.Method;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class ReorderedPowerOf2Test {

  static Stream<Method> methodProvider() {
    return Stream.of(Method.HASH_MAP, Method.OPTIMIZED);
  }

  @ParameterizedTest
  @MethodSource("methodProvider")
  void n1_is_true(Method method) {
    ReorderedPowerOf2 underTest = new ReorderedPowerOf2(method);
    assertThat(underTest.reorderedPowerOf2(1)).isTrue();
  }

  @ParameterizedTest
  @MethodSource("methodProvider")
  void n10_is_false(Method method) {
    ReorderedPowerOf2 underTest = new ReorderedPowerOf2(method);
    assertThat(underTest.reorderedPowerOf2(10)).isFalse();
  }

  @ParameterizedTest
  @MethodSource("methodProvider")
  void n65536_is_true(Method method) {
    ReorderedPowerOf2 underTest = new ReorderedPowerOf2(method);
    // 65536
    assertThat(underTest.reorderedPowerOf2(65536)).isTrue();
    assertThat(underTest.reorderedPowerOf2(56653)).isTrue();
    assertThat(underTest.reorderedPowerOf2(36655)).isTrue();
  }
}
