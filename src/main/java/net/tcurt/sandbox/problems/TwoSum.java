package net.tcurt.sandbox.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/** From <a href="https://leetcode.com/problems/two-sum">Leetcode 1</a>. */
@Slf4j
public class TwoSum {

  Map<Integer, List<Integer>> numToNdxList = new HashMap<>();

  public int[] twoSum(int[] nums, int target) {
    for (int ndx = 0; ndx < nums.length; ndx++) {
      int num = nums[ndx];
      numToNdxList
          .computeIfAbsent(num, k -> new ArrayList<>()) // create list if doesn't exist
          .add(ndx); // add ndx to list
    }

    int[] result = new int[2];
    for (int i = 0; i < nums.length; i++) {
      int num = nums[i];
      int complement = target - num;
      if (numToNdxList.containsKey(complement)) {
        List<Integer> list = this.numToNdxList.get(complement);
        int complementNdx = list.get(0);
        if (i != complementNdx) {
          result[0] = i;
          result[1] = complementNdx;
          break;
        } else if (list.size() > 1) {
          result[0] = i;
          result[1] = list.get(1);
        }
      }
    }

    return result;
  }
}
