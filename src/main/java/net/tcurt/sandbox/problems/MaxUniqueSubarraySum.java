package net.tcurt.sandbox.problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * From <a href="https://leetcode.com/problems/maximum-unique-subarray-sum-after-deletion">Leetcode
 * 3487</a>
 */
public class MaxUniqueSubarraySum {

  public int maxSum(int[] nums) {
    Set<Integer> positiveIntSet = new HashSet<>();
    for (int num : nums) {
      if (num > 0) {
        positiveIntSet.add(num);
      }
    }

    int maxSum;
    if (positiveIntSet.isEmpty()) {
      maxSum = Arrays.stream(nums).max().getAsInt();
    } else {
      maxSum = positiveIntSet.stream().mapToInt(Integer::intValue).sum();
    }

    return maxSum;
  }
}
