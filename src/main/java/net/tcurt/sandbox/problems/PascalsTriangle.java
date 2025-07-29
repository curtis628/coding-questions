package net.tcurt.sandbox.problems;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/** From <a href="https://leetcode.com/problems/pascals-triangle">Leetcode 118</a> */
@Slf4j
public class PascalsTriangle {

  public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> triangle = new ArrayList<>(numRows);
    triangle.add(List.of(1));

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
}
