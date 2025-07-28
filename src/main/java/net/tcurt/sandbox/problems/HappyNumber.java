package net.tcurt.sandbox.problems;

import java.util.HashSet;
import java.util.Set;

/**
 * From <a href="https://leetcode.com/problems/happy-number">Leetcode 202</a>.
 *
 * <p>Write an algorithm to determine if a number {@code n} is happy.
 *
 * <p>A <b>happy number</b> is a number defined by the following process:
 *
 * <ul>
 *   <li>Starting with any positive integer, replace the number by the sum of the squares of its
 *       digits.
 *   <li>Repeat the process until the number equals 1 (where it will stay), or it <b>loops endlessly
 *       in a cycle</b> which does not include 1.
 *   <li>Those numbers for which this process <b>ends in 1</b> are happy.
 * </ul>
 *
 * Return {@code true} if {@code n} is a happy number, and {@code false} if not.
 */
public class HappyNumber {

  public boolean isHappy(int n) {
    Set<Integer> seen = new HashSet<>();
    while (n != 1 && !seen.contains(n)) {
      seen.add(n);

      System.out.printf("Checking: %d\n", n);
      int sum = 0;
      while (n > 0) {
        int digit = n % 10;
        System.out.printf("  processing digit: %d\n", digit);
        n /= 10;
        sum += (digit * digit);
      }
      System.out.printf("  sum: %d\n", sum);
      n = sum;
    }
    return n == 1;
  }
}
