package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import net.tcurt.sandbox.Method;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class KthGrammarTest {

  static Stream<Method> methodProvider() {
    return Stream.of(Method.RECURSIVE, Method.BINARY_TREE);
  }

  @ParameterizedTest
  @MethodSource("methodProvider")
  void n1_k1_is_0(Method method) {
    KthGrammar underTest = new KthGrammar(method);
    assertThat(underTest.kthGrammar(1, 1)).isEqualTo(0);
  }

  @ParameterizedTest
  @MethodSource("methodProvider")
  void n2_k1_is_0(Method method) {
    KthGrammar underTest = new KthGrammar(method);
    assertThat(underTest.kthGrammar(2, 1)).isEqualTo(0);
  }

  @ParameterizedTest
  @MethodSource("methodProvider")
  void n2_k2_is_1(Method method) {
    KthGrammar underTest = new KthGrammar(method);
    assertThat(underTest.kthGrammar(2, 2)).isEqualTo(1);
  }

  @ParameterizedTest
  @MethodSource("methodProvider")
  void n3(Method method) {
    KthGrammar underTest = new KthGrammar(method);
    assertThat(underTest.kthGrammar(3, 1)).isEqualTo(0);
    assertThat(underTest.kthGrammar(3, 2)).isEqualTo(1);
  }

  @ParameterizedTest
  @MethodSource("methodProvider")
  void n6(Method method) {
    KthGrammar underTest = new KthGrammar(method);
    assertThat(underTest.kthGrammar(6, 21)).isEqualTo(0);
  }
}
