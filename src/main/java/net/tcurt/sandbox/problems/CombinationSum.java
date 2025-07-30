package net.tcurt.sandbox.problems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

// TODO struggled!
/**
 * From <a href="https://leetcode.com/problems/combination-sum">Leetcode 39</a>
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

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> results = new ArrayList<>();
    LinkedList<Integer> comb = new LinkedList<>();
    calculate(target, comb, 0, candidates, results);
    return results;
  }

  private void calculate(
      int remain,
      LinkedList<Integer> comb,
      int start,
      int[] candidates,
      List<List<Integer>> results) {
    if (remain == 0) {
      results.add(new ArrayList<>(comb)); // return deep copy of the combination
      return;
    } else if (remain < 0) {
      return; // no other opportunities down this path
    }

    for (int ndx = start; ndx < candidates.length; ndx++) {
      int num = candidates[ndx];
      comb.add(num);
      calculate(remain - num, comb, ndx, candidates, results);
      comb.removeLast();
    }
  }
}
