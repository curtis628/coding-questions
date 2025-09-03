package net.tcurt.sandbox.interviews.mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.tcurt.sandbox.Method;

/**
 * From <a href="https://www.youtube.com/watch?v=Ti5vfu9arXQ">YouTube: How to solve a Google coding
 * interview question</a>.
 *
 * <p>Problem: A farmer wants to farm their land with the maximum area where good land is present.
 * The "land" is represented as a matrix with 1s and 0s, where 1s mean good land and 0s means bad
 * land. The farmer only wants to farm in a square good land with the maximum area. Please help the
 * farmer to find the maximum area of the land they can farm in good land.
 *
 * <pre>
 * 0 1 1 0 1
 * 1 1 0 1 0
 * 0 1 1 1 0
 * 1 1 1 1 0
 * 1 1 1 1 1
 * 0 0 0 0 0
 * </pre>
 */
@RequiredArgsConstructor
@Slf4j
public class GoodFarmLand {

  private final Method method;

  public int maximumSquare(int[][] land) {
    int result = 0;

    switch (method) {
      case RECURSIVE -> {
        int rows = land.length;
        int cols = land[0].length;
        int[][] memo = new int[rows][cols];
        for (int[] row : memo) {
          Arrays.fill(row, -1);
        }

        for (int row = 0; row < rows; row++) {
          for (int col = 0; col < cols; col++) {
            int largest = recursiveCalc(land, row, col, memo);
            result = Math.max(result, largest);
          }
        }
      }
      case DYNAMIC_PROGRAMMING -> result = dynamicProgramming(land);
    }

    return result;
  }

  private int recursiveCalc(int[][] land, int row, int col, int[][] memo) {
    if (land[row][col] == 0) memo[row][col] = 0;
    // if at edge or bottom, number stays the same
    if (row == land.length - 1 || col == land[0].length - 1)
      memo[row][col] = land[row][col]; // if at edge, number stays the same

    if (memo[row][col] == -1) {
      int right = (row + 1 < land.length) ? recursiveCalc(land, row + 1, col, memo) : 0;
      int bottom = (col + 1 < land[0].length) ? recursiveCalc(land, row, col + 1, memo) : 0;
      int diag = 0;
      if (row + 1 < land.length && col + 1 < land[0].length) {
        diag = recursiveCalc(land, row + 1, col + 1, memo);
      }

      List<Integer> neighbors = List.of(right, bottom, diag);
      memo[row][col] = Collections.min(neighbors) + 1;
    }
    return memo[row][col];
  }

  private int dynamicProgramming(int[][] land) {
    int rows = land.length;
    int cols = land[0].length;
    int max = 0;
    int[][] dp = new int[rows][cols];

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        if (land[row][col] == 0) continue;
        int up = 0, left = 0, diag = 0;
        if (row - 1 >= 0) left = dp[row - 1][col];
        if (col - 1 >= 0) up = dp[row][col - 1];
        if (row - 1 >= 0 && col - 1 >= 0) diag = dp[row - 1][col - 1];

        List<Integer> neighbors = List.of(up, left, diag);
        dp[row][col] = Collections.min(neighbors) + 1;
        max = Math.max(max, dp[row][col]);
      }
    }

    return max;
  }
}
