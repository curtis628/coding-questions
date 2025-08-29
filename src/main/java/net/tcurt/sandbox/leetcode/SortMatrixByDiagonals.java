package net.tcurt.sandbox.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/** From <a href="https://leetcode.com/problems/sort-matrix-by-diagonals">Leetcode 3346</a>. */
@Slf4j
public class SortMatrixByDiagonals {

  public int[][] sortMatrix(int[][] grid) {
    int n = grid.length;

    // bottom-left triangle + middle: descending
    for (int i = 0; i < n; i++) {
      List<Integer> line = new ArrayList<>();
      for (int j = 0; j + i < n; j++) {
        line.add(grid[i + j][j]);
      }
      line.sort(Comparator.reverseOrder());

      for (int j = 0; j + i < n; j++) {
        grid[i + j][j] = line.get(j);
      }
    }

    // bottom-left triangle + middle: descending
    for (int j = 1; j < n; j++) {
      List<Integer> line = new ArrayList<>();
      for (int i = 0; i + j < n; i++) {
        line.add(grid[i][j + i]);
      }

      Collections.sort(line);
      for (int i = 0; i + j < n; i++) {
        grid[i][j + i] = line.get(i);
      }
    }
    return grid;
  }
}
