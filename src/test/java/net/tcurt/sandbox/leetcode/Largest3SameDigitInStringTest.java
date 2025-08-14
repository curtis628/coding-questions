package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class Largest3SameDigitInStringTest {
  private Largest3SameDigitInString underTest = new Largest3SameDigitInString(3);

  @Test
  void input6777133339_is_777() {
    assertThat(underTest.largestGoodInteger("6777133339")).isEqualTo("777");
  }

  @Test
  void input2300019_is_000() {
    assertThat(underTest.largestGoodInteger("2300019")).isEqualTo("000");
  }

  @Test
  void input42352338_is_empty() {
    assertThat(underTest.largestGoodInteger("42352338")).isEmpty();
  }
}
