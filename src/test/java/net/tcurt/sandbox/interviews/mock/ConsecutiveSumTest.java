package net.tcurt.sandbox.interviews.mock;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.stream.Stream;
import net.tcurt.sandbox.Method;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class ConsecutiveSumTest {

  private static Stream<Method> methodProvider() {
    return Stream.of(Method.SLIDING_WINDOW);
  }


  @ParameterizedTest
  @MethodSource("methodProvider")
  void basicTests(Method method) {
    Map<Long, Integer> expectedMap = Map.of(21L, 3, 15L, 3, 10L, 1, 110088L, 11, 85824L, 5);
    ConsecutiveSum underTest = new ConsecutiveSum(method);

    for (Map.Entry<Long, Integer> entry : expectedMap.entrySet()) {
      long num = entry.getKey();
      int actual = underTest.consecutive(num);
      assertThat(actual)
              .as("Invoked consecutive(num=%d)", num)
              .isEqualTo(entry.getValue());
    }
    assertThat(underTest.consecutive(21L)).isEqualTo(3);
  }

//  @ParameterizedTest
//  @MethodSource("methodProvider")
  void giant_input_6611091734(Method method) {
    ConsecutiveSum underTest = new ConsecutiveSum(method);
    assertThat(underTest.consecutive(6611091734L)).isEqualTo(5);
  }
}
