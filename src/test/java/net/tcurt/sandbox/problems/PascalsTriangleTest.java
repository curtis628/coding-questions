package net.tcurt.sandbox.problems;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class PascalsTriangleTest {
  private PascalsTriangle pascalsTriangle = new PascalsTriangle();

  @Test
  void numRows_5() {
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

  @Test
  void numRows_1() {
    List<List<Integer>> expected = List.of(List.of(1));
    List<List<Integer>> actual = pascalsTriangle.generate(1);
    assertThat(actual).isEqualTo(expected);
  }
}
