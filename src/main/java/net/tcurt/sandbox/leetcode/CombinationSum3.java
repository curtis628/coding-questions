package net.tcurt.sandbox.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// TODO struggled
/**
 * From <a href="https://leetcode.com/problems/combination-sum-iii">leetcode 216</a> Find all valid
 * combinations of k numbers that sum up to n such that the following conditions are true:
 *
 * <ol>
 *   <li>Only numbers 1 through 9 are used.
 *   <li>Each number is used at most once
 * </ol>
 *
 * <p>Return a list of all possible valid combinations. The list must not contain the same
 * combination twice, and the combinations may be returned in any order.
 */
public class CombinationSum3 {

  public List<List<Integer>> combinationSum3(int k, int n) {
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> comb = new LinkedList<>();
    backtrack(n, k, comb, 1, result);
    return result;
  }

  private void backtrack(
      int remain, int k, LinkedList<Integer> comb, int nextStart, List<List<Integer>> result) {
    if (comb.size() == k && remain == 0) {
      // we found a valid combination in comb! Store deep-copy so it's not reverted after
      // backtracking.
      result.add(new ArrayList<>(comb));
      return;
    }
    if (remain < 0) {
      return; // no possible combination
    }

    for (int n = nextStart; n <= 9; n++) {
      comb.add(n);
      backtrack(remain - n, k, comb, n + 1, result);
      comb.removeLast();
    }
  }
}
