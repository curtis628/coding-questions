package net.tcurt.sandbox.problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    Set<Integer> attempted = new HashSet<>();

    while (n != 1) {
      if (attempted.contains(n)) {
        System.out.printf("'%d': previously tried! Aborting...\n", n);
        break;
      }
      attempted.add(n);

      List<Integer> digits = new ArrayList<>();
      // parse all digits
      do {
        digits.add(n % 10);
        n /= 10;
      } while (n > 0);

      int sum = digits.stream().mapToInt(num -> num * num).sum();
      n = sum;
    }
    return n == 1;
  }
}
