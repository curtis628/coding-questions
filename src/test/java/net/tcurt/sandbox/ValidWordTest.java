package net.tcurt.sandbox;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ValidWordTest {
  private ValidWord validWord = new ValidWord();

  @Test
  void valid() {
    assertThat(validWord.isValid("abc")).isTrue();
    assertThat(validWord.isValid("ABC")).isTrue();
    assertThat(validWord.isValid("AAABBBCCC1234aaabbbcccddd")).isTrue();
  }

  @Test
  void invalid() {
    assertThat(validWord.isValid("ab")).isFalse();
    assertThat(validWord.isValid("aaa")).isFalse();
    assertThat(validWord.isValid("bbb")).isFalse();
    assertThat(validWord.isValid("!abc")).isFalse();
    assertThat(validWord.isValid("abc!")).isFalse();
  }
}
