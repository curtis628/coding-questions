package net.tcurt.sandbox.problems;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class UniqueBinarySearchTreesTest {
  private UniqueBinarySearchTrees underTest = new UniqueBinarySearchTrees();

  @Test
  void n3is5() {
    assertThat(underTest.numTrees(3)).isEqualTo(5);
  }

  @Test
  void n1is1() {
    assertThat(underTest.numTrees(1)).isEqualTo(1);
  }

  @Test
  void n4is14() {
    assertThat(underTest.numTrees(4)).isEqualTo(14);
  }
}
