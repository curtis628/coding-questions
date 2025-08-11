package net.tcurt.sandbox.leetcode;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/** From <a href="https://leetcode.com/problems/climbing-stairs/">Leetcode 70</a>. */
@Slf4j
public class ClimbingStairs {

  // helpful to visualize how many calls were made
  Map<Pair, Integer> callMap = new HashMap<>();

  record Pair(int currentStep, int destinationStep) {}

  public int climbStairs(int n) {
    return climbStairs(0, n, new int[n + 1]);
  }

  private int climbStairs(int currentStep, int destinationStep, int[] memo) {
    if (currentStep > destinationStep) return 0;
    if (currentStep == destinationStep) return 1;

    // memoization helps reduce time complexity to O(n)
    if (memo[currentStep] == 0) {
      Pair key = new Pair(currentStep, destinationStep);
      int existingCount = callMap.getOrDefault(key, 0);
      callMap.put(key, existingCount + 1);

      int oneStepOptions = climbStairs(currentStep + 1, destinationStep, memo);
      int twoStepOptions = climbStairs(currentStep + 2, destinationStep, memo);
      memo[currentStep] = oneStepOptions + twoStepOptions;
    }

    return memo[currentStep];
  }
}
