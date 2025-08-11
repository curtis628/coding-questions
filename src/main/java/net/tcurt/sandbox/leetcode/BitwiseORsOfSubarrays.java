package net.tcurt.sandbox.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** From <a href="https://leetcode.com/problems/bitwise-ors-of-subarrays">Leetcode 898</a>. */
@RequiredArgsConstructor
@Slf4j
public class BitwiseORsOfSubarrays {

  enum Method {
    BRUTE_FORCE_RECURSIVE,
    BRUTE_FORCE_ITERATIVE,
    OPTIMAL_ITERATIVE
  }

  private final Method method;

  public int subarrayBitwiseORs(int[] arr) {
    int result = 0;
    switch (method) {
      case BRUTE_FORCE_RECURSIVE:
        result = recursive(arr);
        break;
      case BRUTE_FORCE_ITERATIVE:
        result = bruteForceIterative(arr);
        break;
      case OPTIMAL_ITERATIVE:
        result = optimalIterative(arr);
        break;
    }
    log.debug(
        "Using {}, the number of all unique OR values across subarrays of {} is {}",
        method,
        arr,
        result);
    return result;
  }

  private int optimalIterative(int[] arr) {
    // Stores all unique OR values found across all subarrays
    Set<Integer> resultOrs = new HashSet<>();

    // Stores distinct ORs of all subarrays ending at previous position
    Set<Integer> currentOrs = new HashSet<>();

    for (int x : arr) {
      // `nextOrs` will store the ORs of subarrays ending at the current element `x`.
      Set<Integer> nextOrs = new HashSet<>();
      nextOrs.add(x);

      // Compute new ORs by extending previous subarrays with the current element x.
      for (int y : currentOrs) {
        nextOrs.add(x | y);
      }

      // Add all newly found ORs to the main result set.
      resultOrs.addAll(nextOrs);

      // For the next iteration, the current results become the previous results.
      currentOrs = nextOrs;
    }
    return resultOrs.size();
  }

  private int bruteForceIterative(int[] arr) {
    List<List<Integer>> possibilities = new ArrayList<>();
    for (int i = 0; i < arr.length; i++) {
      List<Integer> list = new ArrayList<>();
      list.add(arr[i]);
      possibilities.add(new ArrayList(list));
      for (int j = i + 1; j < arr.length; j++) {
        list.add(arr[j]);
        possibilities.add(new ArrayList(list));
      }
    }

    return bitwiseSumPossibleSubarrays(possibilities);
  }

  private int bitwiseSumPossibleSubarrays(List<List<Integer>> possibleSubarrays) {
    Set<Integer> sums = new HashSet<>();
    for (List<Integer> possibility : possibleSubarrays) {
      int subarrayOr = 0;
      for (int num : possibility) {
        subarrayOr |= num;
      }
      sums.add(subarrayOr);
    }

    return sums.size();
  }

  private int recursive(int[] arr) {
    LinkedList<Integer> comb = new LinkedList<>();
    List<List<Integer>> possibleSubarrays = new ArrayList<>();

    backtrack(comb, 0, arr, possibleSubarrays);
    return bitwiseSumPossibleSubarrays(possibleSubarrays);
  }

  private void backtrack(
      LinkedList<Integer> comb, int start, int[] arr, List<List<Integer>> possibilities) {
    if (start == arr.length) {
      if (!comb.isEmpty()) {
        possibilities.add(new ArrayList<>(comb));
      }
      return;
    }

    int num = arr[start];

    // don't pick num...
    backtrack(comb, start + 1, arr, possibilities);

    // pick num if it's contiguous
    if (comb.isEmpty() || (start > 0 && comb.getLast() == arr[start - 1])) {
      comb.add(num);
      backtrack(comb, start + 1, arr, possibilities);
      comb.removeLast();
    }
  }
}
