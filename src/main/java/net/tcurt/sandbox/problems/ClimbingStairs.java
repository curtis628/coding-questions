package net.tcurt.sandbox.problems;

import lombok.extern.slf4j.Slf4j;

/** From <a href="https://leetcode.com/problems/climbing-stairs/">Leetcode 70</a>. */
@Slf4j
public class ClimbingStairs {

  public int climbStairs(int n) {
    return climbStairs(0, n, new int[n + 1]);
  }

  private int climbStairs(int currentStep, int destinationStep, int[] memo) {
    if (currentStep > destinationStep) return 0;
    if (currentStep == destinationStep) return 1;
    if (memo[currentStep] > 0) {
      return memo[currentStep];
    }

    memo[currentStep] =
        climbStairs(currentStep + 1, destinationStep, memo)
            + climbStairs(currentStep + 2, destinationStep, memo);
    return memo[currentStep];
  }
}
