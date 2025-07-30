package net.tcurt.sandbox.problems;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/** From <a href="https://leetcode.com/problems/fibonacci-number/">Leetcode 509</a>. */
@Slf4j
public class FibonacciNumber {

  // track call count to verify how many cache lookups there are
  Map<Integer, Integer> callMap = new HashMap<>();

  public int fib(int n) {
    return fib(n, new int[n + 1]);
  }

  private int fib(int n, int[] memo) {
    if (n == 0) return 0;
    if (n == 1) return 1;

    if (memo[n] == 0) {
      int callCount = callMap.getOrDefault(n, 0);
      callMap.put(n, callCount + 1);
      memo[n] = fib(n - 2, memo) + fib(n - 1, memo);
    }
    return memo[n];
  }
}
