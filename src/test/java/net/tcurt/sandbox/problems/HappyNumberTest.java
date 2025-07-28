package net.tcurt.sandbox.problems;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class HappyNumberTest {
  private HappyNumber underTest = new HappyNumber();

  @Test
  void happy_19() {
    assertThat(underTest.isHappy(19)).isTrue();
  }

  /**
   * Proof that 2 is an unhappy number.
   *
   * <pre>
   * 2:   (4)        --> 4
   * 4:  (16)        --> 16
   * 16:  (1) + (36) --> 37
   * 37:  (9) + (49) --> 58
   * 58: (25) + (64) --> 89
   * 89: (64) + (81) --> 145
   * 145: (1) + (16) + (25) --> 42
   * 42: (16) +  (4) --> 20
   * 20:  (4) +  (0) --> 4 (cycle found!)
   * </pre>
   */
  @Test
  void unhappy_2() {
    assertThat(underTest.isHappy(2)).isFalse();
  }
}
