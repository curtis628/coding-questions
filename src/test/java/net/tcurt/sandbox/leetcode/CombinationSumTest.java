package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class CombinationSumTest {
  private CombinationSum underTest = new CombinationSum();

  @Test
  void input2_3_6_7_target7() {
    int[] input = new int[] {2, 3, 6, 7};
    List<List<Integer>> result = underTest.combinationSum(input, 7);
    assertThat(result).containsExactlyInAnyOrder(List.of(2, 2, 3), List.of(7));
  }

  @Test
  void input3_4_5_target8() {
    int[] input = new int[] {3, 4, 5};
    List<List<Integer>> result = underTest.combinationSum(input, 8);
    assertThat(result).containsExactlyInAnyOrder(List.of(3, 5), List.of(4, 4));
  }

  @Test
  void input2_3_5_target8() {
    int[] input = new int[] {2, 3, 5};
    List<List<Integer>> result = underTest.combinationSum(input, 8);
    assertThat(result)
        .containsExactlyInAnyOrder(List.of(2, 2, 2, 2), List.of(2, 3, 3), List.of(3, 5));
  }

  @Test
  void input2_target1() {
    int[] input = new int[] {2};
    List<List<Integer>> result = underTest.combinationSum(input, 1);
    assertThat(result).isEmpty();
  }
}
