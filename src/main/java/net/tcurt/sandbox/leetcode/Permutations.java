package net.tcurt.sandbox.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/** From <a href="https://leetcode.com/problems/permutations">Leetcode 46</a>. */
@Slf4j
public class Permutations {

  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> results = new ArrayList<>();
    backtrack(new LinkedList<>(), results, nums);
    return results;
  }

  private void backtrack(LinkedList<Integer> comb, List<List<Integer>> results, int[] nums) {
    if (comb.size() == nums.length) {
      results.add(new ArrayList<>(comb));
      return;
    }

    for (int num : nums) {
      if (!comb.contains(num)) {
        comb.add(num);
        backtrack(comb, results, nums);
        comb.removeLast();
      }
    }
  }
}
