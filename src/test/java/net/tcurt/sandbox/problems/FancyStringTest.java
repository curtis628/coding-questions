package net.tcurt.sandbox.problems;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class FancyStringTest {
  FancyString tester = new FancyString();

  @Test
  void leeetcode() {
    String input = "leeetcode";
    String expected = "leetcode";
    assertThat(tester.makeFancyString(input)).isEqualTo(expected);
  }

  @Test
  void aaabaaaa() {
    String input = "aaabaaaa";
    String expected = "aabaa";
    assertThat(tester.makeFancyString(input)).isEqualTo(expected);
  }
}
