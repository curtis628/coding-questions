package net.tcurt.sandbox.problems;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SmallestSubarraysWithMaximumBitwiseOrTest {
  private SmallestSubarraysWithMaximumBitwiseOr underTest =
      new SmallestSubarraysWithMaximumBitwiseOr();

  @Test
  void input_1_0_2_1_3_is_3_3_2_2_1() {
    int[] input = new int[] {1, 0, 2, 1, 3};
    int[] expected = new int[] {3, 3, 2, 2, 1};
    assertThat(underTest.smallestSubarrays(input)).isEqualTo(expected);
  }

  @Test
  void input_1_0_2_1_3_1_is_3_3_2_2_1_1() {
    int[] input = new int[] {1, 0, 2, 1, 3, 1};
    int[] expected = new int[] {3, 3, 2, 2, 1, 1};
    assertThat(underTest.smallestSubarrays(input)).isEqualTo(expected);
  }

  @Test
  void input_1_2_is_2_1() {
    int[] input = new int[] {1, 2};
    int[] expected = new int[] {2, 1};
    assertThat(underTest.smallestSubarrays(input)).isEqualTo(expected);
  }
}
