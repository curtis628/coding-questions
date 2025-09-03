package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class PascalsTriangle2Test {
  private PascalsTriangle2 pascalsTriangle2 = new PascalsTriangle2();

  @Test
  void row_0() {
    assertThat(pascalsTriangle2.getRow(0)).containsExactly(1);
  }

  @Test
  void row_1() {
    assertThat(pascalsTriangle2.getRow(1)).containsExactly(1, 1);
  }

  @Test
  void row_3() {
    assertThat(pascalsTriangle2.getRow(3)).containsExactly(1, 3, 3, 1);
  }

  @Test
  void row_4() {
    assertThat(pascalsTriangle2.getRow(4)).containsExactly(1, 4, 6, 4, 1);
  }
}
