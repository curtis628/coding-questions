package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SortMatrixByDiagonalsTest {
  private SortMatrixByDiagonals underTest = new SortMatrixByDiagonals();

  @Test
  void input_1by1() {
    int[][] input =
        new int[][] {
          new int[] {1},
        };
    int[][] expected =
        new int[][] {
          new int[] {1},
        };
    assertThat(underTest.sortMatrix(input)).isEqualTo(expected);
  }

  @Test
  void input_2by2() {
    int[][] input =
        new int[][] {
          new int[] {0, 1},
          new int[] {1, 2},
        };
    int[][] expected =
        new int[][] {
          new int[] {2, 1},
          new int[] {1, 0},
        };
    assertThat(underTest.sortMatrix(input)).isEqualTo(expected);
  }

  @Test
  void input_3by3() {
    int[][] input =
        new int[][] {
          new int[] {1, 7, 3},
          new int[] {9, 8, 2},
          new int[] {4, 5, 6}
        };
    int[][] expected =
        new int[][] {
          new int[] {8, 2, 3},
          new int[] {9, 6, 7},
          new int[] {4, 5, 1}
        };
    assertThat(underTest.sortMatrix(input)).isEqualTo(expected);
  }

  @Test
  void input_5by5() {
    int[][] input =
        new int[][] {
          new int[] {1, 2, 3, 4, 5},
          new int[] {6, 7, 8, 9, 10},
          new int[] {11, 12, 13, 14, 15},
          new int[] {16, 17, 18, 19, 20},
          new int[] {21, 22, 23, 24, 25},
        };
    int[][] expected =
        new int[][] {
          new int[] {25, 2, 3, 4, 5},
          new int[] {24, 19, 8, 9, 10},
          new int[] {23, 18, 13, 14, 15},
          new int[] {22, 17, 12, 7, 20},
          new int[] {21, 16, 11, 6, 1},
        };
    assertThat(underTest.sortMatrix(input)).isEqualTo(expected);
  }
}
