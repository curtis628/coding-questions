package net.tcurt.sandbox.leetcode;

import lombok.extern.slf4j.Slf4j;

/**
 * Return number of ways an integer can be represented as a sum of two or more consecutive positive
 * integers.
 *
 * <p>From <a href="https://leetcode.com/problems/consecutive-numbers-sum">Leetcode 829</a> Find all
 * valid
 */
@Slf4j
public class ConsecutiveSum {

  // 1 + 2 + ... + n = n * (n+1) / 2
  // m + (m+1) + (m+2) + ... + n = (n(n*1) / 2) - (m-1) m / 2
  // num = (n(n*1) / 2) - (m-1) m / 2
  public int consecutiveNumbersSum(long num) {
    int count = 0;

    // k = number of terms
    for (long k = 2; k * (k + 1) / 2 <= num; k++) {
      long numerator = num - k * (k - 1) / 2;
      if (numerator <= 0) break;

      if (numerator % k == 0) { // must divide evenly
        long m = numerator / k;
        if (m > 0) count++;
      }
    }

    return count;
  }
}
