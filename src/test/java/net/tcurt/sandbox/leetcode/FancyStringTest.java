package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class FancyStringTest {
  FancyString underTest = new FancyString();

  @Test
  void leeetcode() {
    String input = "leeetcode";
    String expected = "leetcode";
    assertThat(underTest.makeFancyString(input)).isEqualTo(expected);
  }

  @Test
  void aaabaaaa() {
    String input = "aaabaaaa";
    String expected = "aabaa";
    assertThat(underTest.makeFancyString(input)).isEqualTo(expected);
  }
}
