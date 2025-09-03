package net.tcurt.sandbox.leetcode;

import lombok.extern.slf4j.Slf4j;

/** From <a href="https://leetcode.com/problems/diagonal-traverse">Leetcode 498</a>. */
@Slf4j
public class DiagonalTraverse {

  public int[] findDiagonalOrder(int[][] mat) {
    int rows = mat.length;
    int cols = mat[0].length;
    int[] result = new int[rows * cols];
    if (rows == 1) {
      return mat[0];
    } else if (cols == 1) {
      for (int row = 0; row < rows; row++) {
        result[row] = mat[row][0];
      }
      return result;
    }

    int row = 0, col = 0;
    int rowAdd = -1;
    int colAdd = 1;
    int numsAddedToLine = 1;

    for (int itr = 0; itr < rows * cols; itr++) {
      result[itr] = mat[row][col];
      System.out.printf("itr=%d: [row=%d col=%d] Adds %d\n", itr, row, col, result[itr]);

      if (numsAddedToLine > 0 && (row == 0 || col + 1 == cols)) {
        // switch direction to downward-left
        rowAdd = 1;
        colAdd = -1;
        numsAddedToLine = 0;

        row = (col + 1 < cols) ? 0 : row + 1;
        col = (col + 1 < cols) ? col + 1 : col;
        System.out.println("  switchDirection: downward-left");
      } else if (numsAddedToLine > 0 && (col == 0 || row + 1 == rows)) {
        // switch direction to upward-right
        rowAdd = -1;
        colAdd = 1;
        numsAddedToLine = 0;

        col = (row == rows - 1) ? col + 1 : 0;
        row = (row == rows - 1) ? row : row + 1;
        System.out.println("  switchDirection: upward-right");
      } else {
        row += rowAdd;
        col += colAdd;
        numsAddedToLine++;
      }
    }

    return result;
  }
}
