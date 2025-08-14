package net.tcurt.sandbox.leetcode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.tcurt.sandbox.Method;

/** From <a href="https://leetcode.com/problems/search-a-2d-matrix">Leetcode 74</a>. */
@RequiredArgsConstructor
@Slf4j
public class Search2DMatrix {

  private final Method method;

  public boolean searchMatrix(int[][] matrix, int target) {
    boolean result = false;
    switch (method) {
      case BRUTE_FORCE -> result = bruteForce(matrix, target);
      case BINARY_SEARCH -> result = binarySearch(matrix, target);
      case OPTIMIZED -> result = binarySearchOptimized(matrix, target);
    }

    return result;
  }

  /** treat {@code matrix} as a single array, but map to 2D matrix. */
  private boolean binarySearchOptimized(int[][] matrix, int target) {
    int rows = matrix.length;
    if (rows == 0) return false;
    int cols = matrix[0].length;

    int loNdx = 0, hiNdx = rows * cols - 1;
    while (loNdx <= hiNdx) {
      int midNdx = (loNdx + hiNdx) / 2;
      int row = midNdx / cols;
      int col = midNdx % cols;
      int mid = matrix[row][col];

      if (target < mid) {
        hiNdx = midNdx - 1;
      } else if (target > mid) {
        loNdx = midNdx + 1;
      } else {
        return true;
      }
    }
    return false;
  }

  private boolean binarySearch(int[][] matrix, int target) {
    int rowLo = 0, rowHi = matrix.length - 1;

    while (rowLo <= rowHi) {
      int rowMid = rowLo + (rowHi - rowLo) / 2;
      int colLo = 0, colHi = matrix[rowMid].length - 1;

      int smallest = matrix[rowMid][colLo];
      int largest = matrix[rowMid][colHi];

      if (target < smallest) {
        rowHi = rowMid - 1; // go to lower row
      } else if (target > largest) {
        rowLo = rowMid + 1; // go to higher row
      } else {
        // found row it should be in. Now do another binary search on column...
        log.debug(
            "target={} should be in row={} [smallest={} largest={}]",
            target,
            rowMid,
            smallest,
            largest);

        while (colLo <= colHi) {
          int colMid = colLo + (colHi - colLo) / 2;
          int poss = matrix[rowMid][colMid];

          if (target < poss) {
            colHi = colMid - 1;
          } else if (target > poss) {
            colLo = colMid + 1;
          } else {
            log.debug("  Found target={} in matrix[{}][{}]", target, rowMid, colMid);
            return true;
          }
        }
        break;
      }
    }

    return false;
  }

  private boolean bruteForce(int[][] matrix, int target) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        if (matrix[i][j] == target) {
          return true;
        }
      }
    }
    return false;
  }
}
