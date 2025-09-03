package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import net.tcurt.sandbox.Method;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class Search2DMatrixTest {

  static Stream<Method> methodProvider() {
    return Stream.of(Method.BRUTE_FORCE, Method.BINARY_SEARCH, Method.OPTIMIZED);
  }

  @ParameterizedTest
  @MethodSource("methodProvider")
  void caseExists(Method method) {
    Search2DMatrix underTest = new Search2DMatrix(method);
    int[][] input =
        new int[][] {
          {1, 3, 5, 7},
          {10, 11, 16, 20},
          {23, 30, 34, 60}
        };
    for (int existingNum : List.of(1, 3, 5, 7, 10, 11, 16, 20, 23, 30, 34, 60)) {
      assertThat(underTest.searchMatrix(input, existingNum)).isTrue();
    }
  }

  @ParameterizedTest
  @MethodSource("methodProvider")
  void case2(Method method) {
    Search2DMatrix underTest = new Search2DMatrix(method);
    int[][] input =
        new int[][] {
          {1, 3, 5, 7},
          {10, 11, 16, 20},
          {23, 30, 34, 60}
        };
    assertThat(underTest.searchMatrix(input, 13)).isFalse();
  }
}
