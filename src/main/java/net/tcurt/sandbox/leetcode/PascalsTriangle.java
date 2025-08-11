package net.tcurt.sandbox.leetcode;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// TODO struggled!
/** From <a href="https://leetcode.com/problems/pascals-triangle">Leetcode 118</a> */
@RequiredArgsConstructor
@Slf4j
public class PascalsTriangle {

  enum Method {
    BRUTE_FORCE_RECURSIVE,
    DYNAMIC_PROGRAMMING
  }

  private final Method method;

  public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> triangle;
    switch (method) {
      case BRUTE_FORCE_RECURSIVE:
        triangle = bruteForceRecursive(numRows);
        break;
      case DYNAMIC_PROGRAMMING:
        triangle = dynamicProgramming(numRows);
        break;
      default:
        throw new RuntimeException("not implemented");
    }
    return triangle;
  }

  // Still O(n^2) for time
  private List<List<Integer>> dynamicProgramming(int numRows) {
    List<List<Integer>> triangle = new ArrayList<>();
    triangle.add(List.of(1)); // base case

    for (int levelNdx = 1; levelNdx < numRows; levelNdx++) {
      List<Integer> level = new ArrayList<>();

      for (int i = 0; i <= levelNdx; i++) {
        if (i == 0 || i == levelNdx) {
          level.add(1);
        } else {
          List<Integer> prevRow = triangle.get(levelNdx - 1);
          int parent1 = prevRow.get(i - 1);
          int parent2 = prevRow.get(i);
          level.add(parent1 + parent2);
        }
      }
      triangle.add(level);
    }
    return triangle;
  }

  private List<List<Integer>> bruteForceRecursive(int numRows) {
    if (numRows == 1) { // base case
      List<List<Integer>> base = new ArrayList<>();
      base.add(List.of(1));
      return base;
    }

    // generate triangle for row above
    List<List<Integer>> result = bruteForceRecursive(numRows - 1);
    List<Integer> prevRow = result.get(result.size() - 1);
    List<Integer> currentRow = new ArrayList<>();
    currentRow.add(1); // first element in the row always
    for (int i = 1; i < prevRow.size(); i++) {
      currentRow.add(prevRow.get(i - 1) + prevRow.get(i));
    }
    currentRow.add(1); // last element in the row always
    result.add(currentRow);
    return result;
  }
}
