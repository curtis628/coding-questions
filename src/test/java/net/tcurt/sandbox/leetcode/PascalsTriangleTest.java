package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.tcurt.sandbox.leetcode.PascalsTriangle.Method;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class PascalsTriangleTest {

  @ParameterizedTest
  @EnumSource(Method.class)
  void numRows_5(Method method) {
    PascalsTriangle pascalsTriangle = new PascalsTriangle(method);
    List<List<Integer>> expected =
        List.of(
            List.of(1),
            List.of(1, 1),
            List.of(1, 2, 1),
            List.of(1, 3, 3, 1),
            List.of(1, 4, 6, 4, 1));
    List<List<Integer>> actual = pascalsTriangle.generate(5);
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest
  @EnumSource(Method.class)
  void numRows_1(Method method) {
    PascalsTriangle pascalsTriangle = new PascalsTriangle(method);
    List<List<Integer>> expected = List.of(List.of(1));
    List<List<Integer>> actual = pascalsTriangle.generate(1);
    assertThat(actual).isEqualTo(expected);
  }
}
