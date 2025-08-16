package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class PermutationsTest {
  private Permutations underTest = new Permutations();

  @Test
  void input_123() {
    int[] input = new int[] {1, 2, 3};
    assertThat(underTest.permute(input))
        .containsExactlyInAnyOrder(
            List.of(1, 2, 3),
            List.of(1, 3, 2),
            List.of(2, 1, 3),
            List.of(2, 3, 1),
            List.of(3, 1, 2),
            List.of(3, 2, 1));
  }

  @Test
  void input_01() {
    int[] input = new int[] {0, 1};
    assertThat(underTest.permute(input))
        .containsExactlyInAnyOrder(
            List.of(0, 1), // only 2 options
            List.of(1, 0));
  }

  @Test
  void input_1() {
    int[] input = new int[] {1};
    assertThat(underTest.permute(input)).containsExactly(List.of(1));
  }
}
