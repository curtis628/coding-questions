package net.tcurt.sandbox.problems;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * From <a href="https://leetcode.com/problems/two-sum-ii-input-array-is-sorted">Leetcode 167</a>.
 */
@RequiredArgsConstructor
@Slf4j
public class TwoSumPartTwo {
  enum Method {
    BRUTE_FORCE,
    BINARY_SEARCH,
    TWO_POINTERS
  };

  private final Method method;

  public int[] twoSum(int[] numbers, int target) {

    int[] results = new int[0];
    switch (method) {
      case BRUTE_FORCE -> results = bruteForce(numbers, target);
      case BINARY_SEARCH -> results = binarySearch(numbers, target);
      case TWO_POINTERS -> results = twoPointers(numbers, target);
    }
    return results;
  }

  private int[] twoPointers(int[] numbers, int target) {
    int lNdx = 0, rNdx = numbers.length - 1;

    while (lNdx < rNdx) {
      int left = numbers[lNdx];
      int right = numbers[rNdx];
      int sum = left + right;

      if (sum == target) {
        return new int[] {lNdx + 1, rNdx + 1};
      } else if (sum < target) {
        lNdx++;
      } else {
        rNdx--;
      }
    }

    return null;
  }

  private int[] binarySearch(int[] numbers, int target) {
    for (int i = 0; i < numbers.length; i++) {
      int num1 = numbers[i];
      int complement = target - num1;

      // 0  1  2  3  4  5  6  7  8  9  : ndx
      // -2 0  1  1  2  3  5  6  7  10 : numbers
      // target: 4
      // ndx-v1: (10/2): 5
      // ndx-v2: (10-5) / 2 + 5: 7
      // ndx-v3: (10-7) / 2 + 7: 8
      int ndx = (numbers.length - i) / 2;
      while (true) {
        int num = numbers[ndx];

        if (complement == num) {
          return new int[] {i + 1, ndx + 1};
        }

        // log.debug("Searching for complement={}: numbers[{}]={}", complement, ndx, num);
        int newNdx = -1;
        if (complement < num) {
          newNdx = ndx - (numbers.length - ndx) / 2;
        } else if (complement > num) {
          newNdx = (numbers.length - ndx) / 2 + ndx;
        }
        if (ndx == newNdx) break;
        ndx = newNdx;
      }
    }
    return null;
  }

  private int[] bruteForce(int[] numbers, int target) {
    for (int i = 0; i < numbers.length; i++) {
      int num1 = numbers[i];
      int complement = target - num1;

      for (int j = i + 1; j < numbers.length; j++) {
        if (numbers[j] == complement) {
          return new int[] {i + 1, j + 1};
        }
      }
    }
    return null;
  }
}
