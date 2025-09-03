package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class MaxScoreRemovingSubstringsTest {
  private MaxScoreRemovingSubstrings underTest = new MaxScoreRemovingSubstrings();

  @Test
  void cdbcbbaaabab_x4_y5() {
    String input = "cdbcbbaaabab";
    assertThat(underTest.maximumGain(input, 4, 5)).isEqualTo(19);
  }

  @Test
  void aabbaaxybbaabb_x5_y4() {
    String input = "aabbaaxybbaabb";
    assertThat(underTest.maximumGain(input, 5, 4)).isEqualTo(20);
  }
}
