package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DummyPullRequestTest {
  private DummyPullRequest underTest = new DummyPullRequest();

  @Test
  void case1() {
    assertThat(underTest).isNotNull();
  }
}
