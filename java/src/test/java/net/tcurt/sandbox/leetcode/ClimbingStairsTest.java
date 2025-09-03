package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ClimbingStairsTest {
  private ClimbingStairs climbingStairs = new ClimbingStairs();

  // 1. 1 step
  @Test
  void input1_is_1() {
    assertThat(climbingStairs.climbStairs(1)).isEqualTo(1);
  }

  // 1. 1 step + 1 step
  // 2. 2 steps
  @Test
  void input2_is_2() {
    assertThat(climbingStairs.climbStairs(2)).isEqualTo(2);
  }

  // 1. 1 step + 1 step + 1 step
  // 2. 1 step + 2 steps
  // 3. 2 steps + 1 step
  @Test
  void input3_is_3() {
    assertThat(climbingStairs.climbStairs(3)).isEqualTo(3);
  }

  // 1. 1 step + 1 step + 1 step + 1 step
  // 2. 1 step + 1 step + 2 steps
  // 3. 1 step + 2 steps + 1 steps
  // 4. 2 steps + 1 step + 1 step
  // 5. 2 steps + 2 steps
  @Test
  void input4_is_5() {
    assertThat(climbingStairs.climbStairs(4)).isEqualTo(5);
  }

  @Test
  void input5_is_8() {
    assertThat(climbingStairs.climbStairs(5)).isEqualTo(8);
  }
}
