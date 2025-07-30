package net.tcurt.sandbox.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// TODO struggled
/** From <a href="https://leetcode.com/problems/pascals-triangle-ii">Leetcode 119</a> */
@Slf4j
public class PascalsTriangle2 {

  @Data
  @RequiredArgsConstructor
  private final class RowCol {
    private final int row;
    private final int col;
  }

  private Map<RowCol, Integer> cache = new HashMap<>();

  public List<Integer> getRow(int rowIndex) {
    List<Integer> results = new ArrayList<>();
    for (int colNdx = 0; colNdx <= rowIndex; colNdx++) {
      int result = getNum(rowIndex, colNdx);
      results.add(result);
    }
    return results;
  }

  private int getNum(int rowNdx, int colNdx) {
    RowCol rowCol = new RowCol(rowNdx, colNdx);
    if (!cache.containsKey(rowCol)) {
      log.debug("Freshly processing: {}", rowCol);
      int num =
          (rowNdx == 0 || colNdx == 0 || colNdx == rowNdx)
              ? 1
              : getNum(rowNdx - 1, colNdx - 1) + getNum(rowNdx - 1, colNdx);
      cache.put(rowCol, num);
    }

    return cache.get(rowCol);
  }
}
