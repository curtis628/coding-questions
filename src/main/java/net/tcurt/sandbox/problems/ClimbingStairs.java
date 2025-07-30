package net.tcurt.sandbox.problems;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/** From <a href="https://leetcode.com/problems/climbing-stairs/">Leetcode 70</a>. */
@Slf4j
public class ClimbingStairs {

  List<Integer> possibleSteps = List.of(1, 2);

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

  private void addSteps(List<List<Integer>> possibilities, List<Integer> sandbox, int n) {
    if (n == 0) return;

    for (int step : possibleSteps) {
      if (n - step >= 0) {
        log.debug("Adding step={} for n={}. sandbox={} poss={}", step, n, sandbox, possibilities);
        sandbox.add(step);
        addSteps(possibilities, sandbox, n - step);
        int sum = sandbox.stream().mapToInt(Integer::intValue).sum();
        if (sum == n) {
          log.debug("  storing to possibilities: {}", sandbox);
          possibilities.add(new ArrayList<>(sandbox));
        }
        sandbox.remove(sandbox.size() - 1); // backtrack
      }
    }
  }
}
