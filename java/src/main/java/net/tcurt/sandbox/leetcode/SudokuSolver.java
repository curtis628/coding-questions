package net.tcurt.sandbox.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

/** From <a href="https://leetcode.com/problems/sudoku-solver">Leetcode 37</a>. */
@Slf4j
public class SudokuSolver {

  // 9x9 grid
  private static final int N = 9;

  private record Cell(int row, int col) {}

  public void solveSudoku(char[][] board) {
    // sets to track which numbers are used in each row, col, and box
    List<Set<Character>> rowUsed = new ArrayList<>();
    List<Set<Character>> colUsed = new ArrayList<>();
    List<Set<Character>> boxUsed = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      rowUsed.add(new HashSet<>());
      colUsed.add(new HashSet<>());
      boxUsed.add(new HashSet<>());
    }

    // initialize sets and collect empty cells
    List<Cell> empties = new ArrayList<>();
    for (int r = 0; r < N; r++) {
      for (int c = 0; c < N; c++) {
        char val = board[r][c];
        if (val == '.') {
          empties.add(new Cell(r, c));
        } else {
          rowUsed.get(r).add(val);
          colUsed.get(c).add(val);
          boxUsed.get(boxIndex(r, c)).add(val);
        }
      }
    }

    backtrack(board, empties, rowUsed, colUsed, boxUsed);
  }

  // 0 | 1 | 2
  // 3 | 4 | 5
  // 6 | 7 | 8
  private int boxIndex(int r, int c) {
    return (r / 3) * 3 + (c / 3);
  }

  private boolean backtrack(
      char[][] board,
      List<Cell> empties,
      List<Set<Character>> rowUsed,
      List<Set<Character>> colUsed,
      List<Set<Character>> boxUsed) {
    if (empties.size() == 0) return true; // solved!

    // MRV (minimum remaining values): pick cell with fewest options
    int bestNdx = -1;
    int bestCount = N + 1;
    Set<Character> bestCandidates = null;

    for (int ndx = 0; ndx < empties.size(); ndx++) {
      int row = empties.get(ndx).row();
      int col = empties.get(ndx).col();
      Set<Character> candidates = candidatesFor(row, col, rowUsed, colUsed, boxUsed);

      if (candidates.size() < bestCount) {
        bestCount = candidates.size();
        bestNdx = ndx;
        bestCandidates = candidates;
        if (bestCount == 1) break; // can't get better
      }
    }

    Cell bestCell = empties.remove(bestNdx);
    int r = bestCell.row(), c = bestCell.col();
    int b = boxIndex(r, c);
    System.out.printf(
        "  Placing %d candidates in [row=%d col=%d box=%d]: %s\n",
        bestCount, r, c, b, bestCandidates);

    // NOTE: if we have no candidates here, we'll return false and re-add it back to empties
    for (char ch : bestCandidates) {
      // try placing candidate
      board[r][c] = ch;
      rowUsed.get(r).add(ch);
      colUsed.get(c).add(ch);
      boxUsed.get(b).add(ch);

      if (backtrack(board, empties, rowUsed, colUsed, boxUsed)) return true;
      System.out.printf("  Undo failed candidate=%s in [row=%d col=%d box=%d]\n", ch, r, c, b);

      // backtrack / undo guess that was incorrect
      board[r][c] = '.';
      rowUsed.get(r).remove(ch);
      colUsed.get(c).remove(ch);
      boxUsed.get(b).remove(ch);
    }

    empties.add(bestNdx, bestCell); // restore order
    return false;
  }

  private Set<Character> candidatesFor(
      int row,
      int col,
      List<Set<Character>> rowUsed,
      List<Set<Character>> colUsed,
      List<Set<Character>> boxUsed) {

    Set<Character> candidates = new HashSet<>();
    for (char num = '1'; num <= '9'; num++) {
      int boxNdx = boxIndex(row, col);
      if (!rowUsed.get(row).contains(num)
          && !colUsed.get(col).contains(num)
          && !boxUsed.get(boxNdx).contains(num)) {
        candidates.add(num);
      }
    }

    return candidates;
  }
}
