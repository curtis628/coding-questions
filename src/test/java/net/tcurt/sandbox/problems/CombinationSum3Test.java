package net.tcurt.sandbox.problems;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class CombinationSum3Test {
  private CombinationSum3 underTest = new CombinationSum3();

  @Test
  void k_3_and_n_7() {
    List<List<Integer>> result = underTest.combinationSum3(3, 7);
    assertThat(result).containsExactlyInAnyOrder(List.of(1, 2, 4));
  }

  @Test
  void k_3_and_n_9() {
    List<List<Integer>> result = underTest.combinationSum3(3, 9);
    assertThat(result)
        .containsExactlyInAnyOrder(List.of(1, 2, 6), List.of(1, 3, 5), List.of(2, 3, 4));
  }

  @Test
  void k_4_and_n_1() {
    assertThat(underTest.combinationSum3(4, 1)).isEmpty();
  }
}
