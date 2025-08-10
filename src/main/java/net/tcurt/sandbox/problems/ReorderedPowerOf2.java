package net.tcurt.sandbox.problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.tcurt.sandbox.Method;

/**
 * From <a href="https://leetcode.com/problems/reordered-power-of-2">Leetcode 869</a>.
 *
 * <p>You are given an integer {@code n}. We reorder the digits in any order (including the original
 * order) such that the leading digit is not zero.
 *
 * <p>Return {@code true} <i>if and only if we can do this so that the resulting number is a power
 * of two</i>.
 */
@RequiredArgsConstructor
@Slf4j
public class ReorderedPowerOf2 {
  private final Method method;

  public boolean reorderedPowerOf2(int n) {
    boolean result = false;
    switch (method) {
      case HASH_MAP -> result = hashMap(n);
      case OPTIMIZED -> result = optimized(n);
    }

    return result;
  }

  private boolean optimized(int n) {
    int[] initial = count(n);

    for (int exp = 0; exp < 31; exp++) {
      int powerOfTwo = (int) Math.pow(2, exp);
      if (Arrays.equals(initial, count(powerOfTwo))) {
        return true;
      }
    }
    return false;
  }

  /** Returns {@code int[]} where indices track counts of that number. */
  private int[] count(int n) {
    // map int[0-9] to their associated counts.
    int[] counts = new int[10];
    while (n > 0) {
      int remainder = n % 10;
      counts[remainder]++;
      n /= 10;
    }
    return counts;
  }

  private boolean hashMap(int n) {
    if (n == 1) return true;

    // map int to counts
    Map<Character, Integer> numToCountMap = countMap(n);
    int digits = Integer.toString(n).length();

    Set<String> possibilities = new HashSet<>();

    // iterate over powers of two until we exceed number of digits we have
    String resultStr = "";
    for (int exp = 1; resultStr.length() <= digits; exp++) {
      int result = (int) Math.pow(2, exp);
      resultStr = Integer.toString(result);
      if (resultStr.length() == digits) {
        possibilities.add(resultStr);
      }
    }

    for (String poss : possibilities) {
      Map<Character, Integer> possCountMap = countMap(Integer.parseInt(poss));
      if (numToCountMap.equals(possCountMap)) {
        return true;
      }
    }
    return false;
  }

  private Map<Character, Integer> countMap(int n) {
    // map int to counts
    Map<Character, Integer> numToCountMap = new HashMap<>();
    for (char num : Integer.toString(n).toCharArray()) {
      numToCountMap.compute(num, (number, count) -> count == null ? 1 : count + 1);
    }
    return numToCountMap;
  }
}
