package net.tcurt.sandbox.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.tcurt.sandbox.Method;

/** From <a href="https://leetcode.com/problems/3sum/">Leetcode 15</a>. */
@RequiredArgsConstructor
@Slf4j
public class ThreeSum {
  private final Method method;

  public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> results = new ArrayList<>();

    switch (method) {
      case BRUTE_FORCE -> bruteForce(new LinkedList<>(), results, 0, nums);
      case TWO_POINTERS -> twoPointers(nums, results);
      default -> throw new IllegalStateException("not implemented: " + method);
    }

    return results;
  }

  private void twoPointers(int[] nums, List<List<Integer>> results) {
    Arrays.sort(nums);

    for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
      int num = nums[i];
      if (i == 0 || nums[i - 1] != num) {
        int lNdx = i + 1, rNdx = nums.length - 1;
        while (lNdx < rNdx) {
          int left = nums[lNdx];
          int right = nums[rNdx];
          int sum = num + left + right;

          if (sum == 0) {
            results.add(List.of(num, left, right));
            lNdx++;
            rNdx--;
            while (lNdx < rNdx && left == nums[lNdx]) {
              lNdx++;
            }
          } else if (sum < 0) {
            lNdx++;
          } else {
            rNdx--;
          }
        }
      }
    }
  }

  private void bruteForce(
      LinkedList<Integer> comb, List<List<Integer>> results, int ndx, int[] nums) {
    int sum = comb.stream().mapToInt(Integer::intValue).sum();
    if (comb.size() == 3 && sum == 0) {
      // only add if comb contains distinct values - which we do via sorting
      List<Integer> copy = new ArrayList<>(comb);
      Collections.sort(copy);
      if (!results.contains(copy)) {
        results.add(copy);
      }
      return;
    } else if (comb.size() == 3) {
      return;
    }

    for (int i = ndx; i < nums.length; i++) {
      comb.add(nums[i]);
      bruteForce(comb, results, i + 1, nums);
      comb.removeLast();
    }
  }
}
