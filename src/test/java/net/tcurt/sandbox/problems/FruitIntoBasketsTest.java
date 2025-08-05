package net.tcurt.sandbox.problems;

import static org.assertj.core.api.Assertions.assertThat;

import net.tcurt.sandbox.problems.FruitIntoBaskets.Method;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class FruitIntoBasketsTest {

  @ParameterizedTest
  @EnumSource(Method.class)
  void input_1_2_1_is_3(Method method) {
    FruitIntoBaskets underTest = new FruitIntoBaskets(method);
    int[] input = new int[] {1, 2, 1};
    assertThat(underTest.totalFruit(input)).isEqualTo(3);
  }

  @ParameterizedTest
  @EnumSource(Method.class)
  void input_0_1_2_2_is_3(Method method) {
    FruitIntoBaskets underTest = new FruitIntoBaskets(method);
    int[] input = new int[] {0, 1, 2, 2};
    assertThat(underTest.totalFruit(input)).isEqualTo(3);
  }

  @ParameterizedTest
  @EnumSource(Method.class)
  void input_1_2_3_2_2_is_4(Method method) {
    FruitIntoBaskets underTest = new FruitIntoBaskets(method);
    int[] input = new int[] {1, 2, 3, 2, 2};
    assertThat(underTest.totalFruit(input)).isEqualTo(4);
  }

  @ParameterizedTest
  @EnumSource(Method.class)
  void input_long(Method method) {
    FruitIntoBaskets underTest = new FruitIntoBaskets(method);
    int[] input = new int[] {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};
    assertThat(underTest.totalFruit(input)).isEqualTo(5);
  }
}
