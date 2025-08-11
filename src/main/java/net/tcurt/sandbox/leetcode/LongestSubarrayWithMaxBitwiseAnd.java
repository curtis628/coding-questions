package net.tcurt.sandbox.leetcode;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

/**
 * From <a href="https://leetcode.com/problems/longest-subarray-with-maximum-bitwise-and">Leetcode
 * 2419</a>.
 */
@Slf4j
public class LongestSubarrayWithMaxBitwiseAnd {

  public int longestSubarray(int[] nums) {
    int max = Arrays.stream(nums).max().getAsInt();

    int i = 0;
    int maxWindowSize = 0;
    int currentWindowSize = 0;
    while (i < nums.length) {
      if (nums[i] == max) {
        currentWindowSize = 1;
        while (i + 1 < nums.length && nums[i + 1] == max) {
          currentWindowSize++;
          i++;
        }
        maxWindowSize = Math.max(maxWindowSize, currentWindowSize);
      }

      i++;
    }

    return maxWindowSize;
  }
}
