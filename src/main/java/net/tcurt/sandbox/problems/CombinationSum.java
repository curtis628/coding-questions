package net.tcurt.sandbox.problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * From <a href="https://leetcode.com/problems/combination-sum">leetcode 39</a>
 *
 * <p>Given an array of distinct integers candidates and a target integer target, return a list of
 * all unique combinations of candidates where the chosen numbers sum to target. You may return the
 * combinations in any order.
 *
 * <p>The same number may be chosen from candidates an unlimited number of times. Two combinations
 * are unique if the frequency of at least one of the chosen numbers is different.
 *
 * <p>The test cases are generated such that the number of unique combinations that sum up to target
 * is less than 150 combinations for the given input.
 */
@Slf4j
public class CombinationSum {

  protected void backtrack(
      int remain,
      LinkedList<Integer> comb,
      int start,
      int[] candidates,
      List<List<Integer>> results) {
    if (remain == 0) {
      results.add(new ArrayList<>(comb)); // deep clone of current combination
      return;
    } else if (remain < 0) {
      return; // no longer need to check
    }

    for (int ndx = start; ndx < candidates.length; ndx++) {
      int i = candidates[ndx];
      comb.add(i);
      backtrack(remain - i, comb, ndx, candidates, results);
      comb.removeLast();
    }
  }

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    log.debug("Find target={} using candidates={}", target, candidates);
    List<List<Integer>> results = new ArrayList<>();
    LinkedList<Integer> comb = new LinkedList<>();

    this.backtrack(target, comb, 0, candidates, results);
    return results;
  }
}
