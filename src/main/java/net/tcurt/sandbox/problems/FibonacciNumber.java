package net.tcurt.sandbox.problems;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/** From <a href="https://leetcode.com/problems/fibonacci-number/">Leetcode 509</a>. */
@Slf4j
public class FibonacciNumber {

  // memoization!
  Map<Integer, Integer> cache = new HashMap<>();

  public int fib(int n) {
    if (cache.containsKey(n)) {
      return cache.get(n);
    }

    int result;
    if (n < 2) {
      result = (n == 0) ? 0 : 1;
    } else {
      result = fib(n - 2) + fib(n - 1);
    }
    cache.put(n, result);

    return result;
  }
}
