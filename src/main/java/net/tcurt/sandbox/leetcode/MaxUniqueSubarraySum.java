package net.tcurt.sandbox.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;

/**
 * From <a href="https://leetcode.com/problems/maximum-unique-subarray-sum-after-deletion">Leetcode
 * 3487</a>
 */
@RequiredArgsConstructor
public class MaxUniqueSubarraySum {

  enum Method {
    POSITIVE_SET,
    N_INSTEAD_OF_2N
  }

  private final Method method;

  public int maxSum(int[] nums) {
    return (method == Method.POSITIVE_SET) ? maxSumOption1(nums) : maxSumOption2(nums);
  }

  public int maxSumOption1(int[] nums) {
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

  // Admittedly... this solution is a lot more complicated to understand.
  // I much prefer option 1 - even if it's O(2n) in the worst case.
  public int maxSumOption2(int[] nums) {
    int maxSum = Integer.MIN_VALUE;
    int currentSum = Integer.MIN_VALUE;
    Set<Integer> subarraySet = new HashSet<>();

    for (int num : nums) {
      if (subarraySet.isEmpty()) {
        subarraySet.add(num);
        currentSum = num;
      } else {
        if (!subarraySet.contains(num) && currentSum + num > currentSum) {
          if (currentSum < 0 && num > 0) {
            subarraySet.clear();
            currentSum = 0;
          }
          subarraySet.add(num);
          currentSum += num;
        } else if (subarraySet.contains(num)) {
          maxSum = Math.max(maxSum, currentSum);
          currentSum = num;
        }
      }
    }
    maxSum = Math.max(maxSum, currentSum);
    return maxSum;
  }
}
