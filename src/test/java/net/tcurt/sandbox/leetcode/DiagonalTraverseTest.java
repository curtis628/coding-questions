package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DiagonalTraverseTest {
  private DiagonalTraverse underTest = new DiagonalTraverse();

  @Test
  void rectangle_horizontal() {
    int[][] input =
        new int[][] {
          new int[] {1, 2, 3, 4, 5},
          new int[] {6, 7, 8, 9, 10},
          new int[] {11, 12, 13, 14, 15},
        };
    assertThat(underTest.findDiagonalOrder(input))
        .isEqualTo(new int[] {1, 2, 6, 11, 7, 3, 4, 8, 12, 13, 9, 5, 10, 14, 15});
  }

  @Test
  void rectangle_vertical() {
    int[][] input =
        new int[][] {
          new int[] {2, 5},
          new int[] {8, 4},
          new int[] {0, -1},
        };
    assertThat(underTest.findDiagonalOrder(input)).isEqualTo(new int[] {2, 5, 8, 0, 4, -1});
  }

  @Test
  void square_2by2() {
    int[][] input =
        new int[][] {
          new int[] {1, 2},
          new int[] {3, 4},
        };
    assertThat(underTest.findDiagonalOrder(input)).isEqualTo(new int[] {1, 2, 3, 4});
  }

  @Test
  void square_3by3() {
    int[][] input =
        new int[][] {
          new int[] {1, 2, 3},
          new int[] {4, 5, 6},
          new int[] {7, 8, 9},
        };
    assertThat(underTest.findDiagonalOrder(input)).isEqualTo(new int[] {1, 2, 4, 7, 5, 3, 6, 8, 9});
  }

  @Test
  void singleRow() {
    int[][] input =
        new int[][] {
          new int[] {1, 2, 3},
        };
    assertThat(underTest.findDiagonalOrder(input)).isEqualTo(new int[] {1, 2, 3});
  }

  @Test
  void singleColumn() {
    int[][] input =
        new int[][] {
          new int[] {1}, new int[] {2}, new int[] {3},
        };
    assertThat(underTest.findDiagonalOrder(input)).isEqualTo(new int[] {1, 2, 3});
  }
}
