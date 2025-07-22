package net.tcurt.sandbox.problems;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class Vector2DTest {

  @Test
  void case1() {
    int[][] matrix = {
      {1, 2},
      {3},
      {4}
    };
    Vector2D vector = new Vector2D(matrix);
    assertThat(vector.next()).isEqualTo(1);
    assertThat(vector.next()).isEqualTo(2);
    assertThat(vector.next()).isEqualTo(3);
    assertThat(vector.hasNext()).isTrue();
    assertThat(vector.hasNext()).isTrue();
    assertThat(vector.next()).isEqualTo(4);
    assertThat(vector.hasNext()).isFalse();
  }
}
