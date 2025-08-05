package net.tcurt.sandbox.problems;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class StringToIntegerTest {
  private StringToInteger underTest = new StringToInteger();

  @Test
  void input_42() {
    assertThat(underTest.myAtoi("42")).isEqualTo(42);
  }

  @Test
  void input_min42() {
    assertThat(underTest.myAtoi(" -042")).isEqualTo(-42);
  }

  @Test
  void input_1337c0d3() {
    assertThat(underTest.myAtoi("1337c0d3")).isEqualTo(1337);
  }

  @Test
  void input_0_1() {
    assertThat(underTest.myAtoi("0-1")).isEqualTo(0);
  }

  @Test
  void input_words_and_987() {
    assertThat(underTest.myAtoi("words and 987")).isEqualTo(0);
  }

  @Test
  void input_maxOverflow() {
    assertThat(underTest.myAtoi("91283472332")).isEqualTo(Integer.MAX_VALUE);
  }

  @Test
  void input_minOverflow() {
    assertThat(underTest.myAtoi("-91283472332")).isEqualTo(Integer.MIN_VALUE);
  }
}
