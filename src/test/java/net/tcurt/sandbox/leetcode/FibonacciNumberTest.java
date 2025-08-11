package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class FibonacciNumberTest {
  private FibonacciNumber fibonacciNumber = new FibonacciNumber();

  @Test
  void input1_is_1() {
    assertThat(fibonacciNumber.fib(1)).isEqualTo(1);
  }

  @Test
  void input2_is_1() {
    assertThat(fibonacciNumber.fib(2)).isEqualTo(1);
  }

  @Test
  void input3_is_2() {
    assertThat(fibonacciNumber.fib(3)).isEqualTo(2);
  }

  @Test
  void input4_is_3() {
    assertThat(fibonacciNumber.fib(4)).isEqualTo(3);
  }

  @Test
  void input30_is_832040() {
    assertThat(fibonacciNumber.fib(30)).isEqualTo(832040);
  }
}
