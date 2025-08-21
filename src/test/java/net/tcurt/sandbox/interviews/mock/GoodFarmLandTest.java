package net.tcurt.sandbox.interviews.mock;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import net.tcurt.sandbox.Method;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class GoodFarmLandTest {

  private static Stream<Method> methodProvider() {
    return Stream.of(Method.RECURSIVE, Method.DYNAMIC_PROGRAMMING);
  }

  /**
   * The best farmland square is 3x3.
   *
   * <pre>
   * 0 1 1 0 1
   * 1 1 0 1 0
   * 0 1 1 1 0
   * 1 1 1 1 0
   * 1 1 1 1 1
   * 0 0 0 0 0
   * </pre>
   */
  @ParameterizedTest
  @MethodSource("methodProvider")
  void case1(Method method) {
    GoodFarmLand underTest = new GoodFarmLand(method);
    int[][] input =
        new int[][] {
          new int[] {0, 1, 1, 0, 1},
          new int[] {1, 1, 0, 1, 0},
          new int[] {0, 1, 1, 1, 0},
          new int[] {1, 1, 1, 1, 0},
          new int[] {1, 1, 1, 1, 1},
          new int[] {0, 0, 0, 0, 0},
        };
    assertThat(underTest.maximumSquare(input)).isEqualTo(3);
  }

  @ParameterizedTest
  @MethodSource("methodProvider")
  void case2(Method method) {
    GoodFarmLand underTest = new GoodFarmLand(method);
    int[][] input =
        new int[][] {
          new int[] {1, 1, 1, 1, 1, 0},
          new int[] {1, 1, 1, 1, 1, 0},
          new int[] {1, 1, 1, 1, 1, 0},
          new int[] {1, 1, 1, 1, 1, 1},
          new int[] {1, 1, 1, 1, 1, 0},
          new int[] {0, 0, 1, 0, 0, 1},
        };
    assertThat(underTest.maximumSquare(input)).isEqualTo(5);
  }
}
