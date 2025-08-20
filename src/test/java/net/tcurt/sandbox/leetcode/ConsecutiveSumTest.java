package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.Test;

public class ConsecutiveSumTest {
  private ConsecutiveSum underTest = new ConsecutiveSum();

  @Test
  void basicTests() {
    Map<Long, Integer> expectedMap = Map.of(21L, 3, 15L, 3, 10L, 1, 110088L, 11, 85824L, 5);

    for (Map.Entry<Long, Integer> entry : expectedMap.entrySet()) {
      long num = entry.getKey();
      int actual = underTest.consecutiveNumbersSum(num);
      assertThat(actual).as("Invoked consecutive(num=%d)", num).isEqualTo(entry.getValue());
    }
    assertThat(underTest.consecutiveNumbersSum(21L)).isEqualTo(3);
  }

  @Test
  void giant_input_6611091734() {
    assertThat(underTest.consecutiveNumbersSum(6611091734L)).isEqualTo(5);
  }
}
