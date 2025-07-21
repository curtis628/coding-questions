package net.tcurt.sandbox.problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * From <a href="https://leetcode.com/problems/combination-sum-iii/description/">leetcode 216</a>
 * Find all valid combinations of k numbers that sum up to n such that the following conditions are
 * true:
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

  protected void backtrack(
      int remain, int k, LinkedList<Integer> comb, int next_start, List<List<Integer>> results) {
    if (remain == 0 && comb.size() == k) {
      // Note: it's important to make a deep copy here,
      // Otherwise the combination would be reverted in other branch of backtracking.
      results.add(new ArrayList<Integer>(comb));
      return;
    } else if (remain < 0 || comb.size() == k) {
      // Exceed the scope, no need to explore further.
      return;
    }

    // Iterate through the reduced list of candidates.
    for (int i = next_start; i < 9; ++i) {
      comb.add(i + 1);
      this.backtrack(remain - i - 1, k, comb, i + 1, results);
      comb.removeLast();
    }
  }

  public List<List<Integer>> combinationSum3(int k, int n) {
    List<List<Integer>> results = new ArrayList<>();
    LinkedList<Integer> comb = new LinkedList<>();

    this.backtrack(n, k, comb, 0, results);
    return results;
  }
}
