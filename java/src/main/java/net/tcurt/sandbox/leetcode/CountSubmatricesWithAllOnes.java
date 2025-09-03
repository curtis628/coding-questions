package net.tcurt.sandbox.leetcode;

import lombok.extern.slf4j.Slf4j;

/**
 * From <a href="https://leetcode.com/problems/count-submatrices-with-all-ones">Leetcode 1504</a>.
 *
 * <p>Given an {@code m} x {@code n} binary matrix {@code mat}, return the number of
 * <b>submatrices</b> that have all ones.
 */
@Slf4j
public class CountSubmatricesWithAllOnes {

  public int numSubmat(int[][] mat) {
    int rows = mat.length;
    int cols = mat[0].length;
    int[][] newmat = new int[rows][cols];

    // convert mat to show continguous numbers of '1' to the right
    for (int row = 0; row < rows; row++) {
      newmat[row][cols - 1] = mat[row][cols - 1];

      for (int col = cols - 2; col >= 0; col--) {
        int val = mat[row][col];
        newmat[row][col] = val == 0 ? 0 : 1 + newmat[row][col + 1];
      }
    }

    // for each cell, expand downward row-by-row, and maintain minimum width across rows
    // add that width to the count since each row extension forms valid rectangles
    int count = 0;
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        int minWidth = newmat[row][col];
        for (int d = row; d < rows; d++) {
          int checkColumn = newmat[d][col];
          if (checkColumn == 0) break;
          minWidth = Math.min(minWidth, checkColumn);
          count += minWidth;
        }
      }
    }

    return count;
  }
}
