package net.tcurt.sandbox.leetcode;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * From <a href="https://leetcode.com/problems/number-of-zero-filled-subarrays">Leetcode 2348</a>.
 */
@Slf4j
public class NumberOfZeroFilledSubarrays {

  // 1:     0:  1
  // 2:    00:  3 (2 '1s' + 1 '3')
  // 3:   000:  6 (3 '1s' + 2 '2s' + 1 '3')
  // 4:  0000: 10 (4 '1s' + 3 '2s' + 2 '3s' + 1 '4')
  // 5: 00000: 15 (5 '1s' + 4 '2s' + 3 '3s' + 2 '4s' + 1 '5')
  // 1 + ... + n: (n * n+1) / 2
  public long zeroFilledSubarray(int[] nums) {
    // first: find all max subarray lengths of zeros
    List<Integer> zeroSubarrayLengths = new ArrayList<>();
    int consecutiveZeros = 0;
    for (int num : nums) {
      if (num == 0) {
        consecutiveZeros++;
      } else {
        if (consecutiveZeros > 0) {
          zeroSubarrayLengths.add(consecutiveZeros);
        }
        consecutiveZeros = 0;
      }
    }
    if (consecutiveZeros > 0) { // ended on a subarray of zeros
      zeroSubarrayLengths.add(consecutiveZeros);
    }

    // System.out.printf("Found %d subarrays of zeros: %s\n", zeroSubarrayLengths.size(),
    // zeroSubarrayLengths);
    long sum = 0;
    for (long subarrayLength : zeroSubarrayLengths) {
      long poss = subarrayLength * (subarrayLength + 1) / 2;
      // System.out.printf("  For length=%d --> %d\n", subarrayLength, poss);
      sum += poss;
    }

    return sum;
  }
}
