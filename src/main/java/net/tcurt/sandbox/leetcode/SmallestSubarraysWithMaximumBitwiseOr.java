package net.tcurt.sandbox.leetcode;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

/**
 * From <a href="https://leetcode.com/problems/smallest-subarrays-with-maximum-bitwise-or">Leetcode
 * 2411</a>
 *
 * <p>NOTE: We need to use bitwise operators to get the solution to work without resulting in a TLE
 * (timelimit exceededed). Given I don't have endless amounts of time to prepare, I'm ignoring
 * bitwise-related coding solutions for now...
 */
@Slf4j
public class SmallestSubarraysWithMaximumBitwiseOr {

  // input: [1,0,2,1,3,1]
  // The maximum possible bitwise OR starting at any index is 3.
  // - Starting at index 0, the shortest subarray that yields the maximum bitwise OR is [1,0,2].
  // - Starting at index 1, the shortest subarray that yields the maximum bitwise OR is [0,2,1].
  // - Starting at index 2, the shortest subarray that yields the maximum bitwise OR is [2,1].
  // - Starting at index 3, the shortest subarray that yields the maximum bitwise OR is [1,3].
  // - Starting at index 4, the shortest subarray that yields the maximum bitwise OR is [3].
  // - Starting at index 5, the shortest subarray that yields the maximum bitwise OR is [1].
  // Therefore, we return [3, 3, 2, 2, 1, 1].
  public int[] smallestSubarrays(int[] nums) {
    int[] result = new int[nums.length];
    int max = Arrays.stream(nums).max().getAsInt();
    log.debug("Max bitwise OR of {} is: {}", nums, max);

    for (int i = 0; i < nums.length; i++) {
      int num = nums[i];
      int maxOr = num;
      int lastMaxOrNdx = i;

      int ndx = i + 1;
      while (maxOr < max && ndx < nums.length) {
        num = nums[ndx];
        if ((maxOr | num) > maxOr) {
          maxOr |= num;
          lastMaxOrNdx = ndx;
        }
        ndx++;
      }
      result[i] = lastMaxOrNdx - i + 1;
    }

    return result;
  }
}
