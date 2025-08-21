package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import net.tcurt.sandbox.Method;
import org.junit.jupiter.api.Test;

public class CountSubmatricesWithAllOnesTest {
  private CountSubmatricesWithAllOnes underTest = new CountSubmatricesWithAllOnes();

  private static Stream<Method> methodProvider() {
    return Stream.of(Method.RECURSIVE);
  }

  /**
   * There are 13 submatrices that have all ones.
   *
   * <pre>
   * 1 0 1
   * 1 1 0
   * 1 1 0
   * </pre>
   *
   * <ul>
   *   <li>There are 6 rectangles of side 1x1.
   *   <li>There are 2 rectangles of side 1x2.
   *   <li>There are 3 rectangles of side 2x1.
   *   <li>There is 1 rectangle of side 2x2.
   *   <li>There is 1 rectangle of side 3x1.
   *   <li><b>Total number of rectangles</b> = 6 + 2 + 3 + 1 + 1 = 13.
   * </ul>
   */
  @Test
  void case1() {
    int[][] input =
        new int[][] {
          new int[] {1, 0, 1},
          new int[] {1, 1, 0},
          new int[] {1, 1, 0},
        };

    assertThat(underTest.numSubmat(input)).isEqualTo(13);
  }

  /**
   * Result: 24
   *
   * <pre>
   * 0 1 1 0
   * 0 1 1 1
   * 1 1 1 0
   * </pre>
   *
   * <ul>
   *   <li>1x1: 8
   *   <li>1x2: 5
   *   <li>1x3: 2
   *   <li>2x1: 4
   *   <li>2x2: 2
   *   <li>3x1: 2
   *   <li>3x2: 1
   *   <li><b>Total</b>: 24
   * </ul>
   */
  @Test
  void case2() {
    int[][] input =
        new int[][] {
          new int[] {0, 1, 1, 0},
          new int[] {0, 1, 1, 1},
          new int[] {1, 1, 1, 0},
        };

    assertThat(underTest.numSubmat(input)).isEqualTo(24);
  }
}
