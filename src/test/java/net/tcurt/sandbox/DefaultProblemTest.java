package net.tcurt.sandbox;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DefaultProblemTest {
  private DefaultProblem underTest = new DefaultProblem();

  @Test
  void case1() {
    assertThat(underTest).isNotNull();
  }
}
