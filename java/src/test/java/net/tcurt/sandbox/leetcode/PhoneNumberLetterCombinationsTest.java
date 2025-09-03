package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class PhoneNumberLetterCombinationsTest {
  private PhoneNumberLetterCombinations underTest = new PhoneNumberLetterCombinations();

  @Test
  void case_23() {
    assertThat(underTest.letterCombinations("23"))
        .containsExactlyInAnyOrder("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
  }

  @Test
  void case_empty() {
    assertThat(underTest.letterCombinations("")).isEmpty();
  }

  @Test
  void case_2() {
    assertThat(underTest.letterCombinations("2")).containsExactlyInAnyOrder("a", "b", "c");
  }
}
