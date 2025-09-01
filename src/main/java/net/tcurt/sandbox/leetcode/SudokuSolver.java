package net.tcurt.sandbox.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

/** From <a href="https://leetcode.com/problems/sudoku-solver">Leetcode 37</a>. */
@Slf4j
public class SudokuSolver {

  private record Cell(int row, int col) {}

  public static class Square {
    private char answer = '\0';
    private Set<Character> possible = new HashSet<>();

    public Square(char answer) {
      this.answer = answer;
    }

    public Square() {
      this.possible.addAll(Set.of('1', '2', '3', '4', '5', '6', '7', '8', '9'));
    }

    public boolean isAnswer() {
      return answer != '\0';
    }

    public String toString() {
      char entry = (answer != '\0') ? answer : '.';
      return Character.toString(entry);
    }
  }

  private static List<List<Cell>> ALL_UNITS = new ArrayList<>();

  static {
    // cells for 9 rows
    for (int row = 0; row < 9; row++) {
      List<Cell> rowCells = new ArrayList<>();
      for (int col = 0; col < 9; col++) {
        rowCells.add(new Cell(row, col));
      }
      ALL_UNITS.add(rowCells);
    }

    // cells for 9 cols
    for (int col = 0; col < 9; col++) {
      List<Cell> colCells = new ArrayList<>();
      for (int row = 0; row < 9; row++) {
        colCells.add(new Cell(row, col));
      }
      ALL_UNITS.add(colCells);
    }

    // cells for 9 3×3 Blocks
    for (int row = 0; row < 9; row += 3) {
      for (int col = 0; col < 9; col += 3) {
        List<Cell> blockCells = new ArrayList<>();
        for (int i = row; i < row + 3; i++) {
          for (int j = col; j < col + 3; j++) {
            blockCells.add(new Cell(i, j));
          }
        }
        ALL_UNITS.add(blockCells);
      }
    }
  }

  private int print(Square[][] mine) {
    System.out.println();
    int possiblesLeft = 0;
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        Square square = mine[i][j];
        if (!square.isAnswer() && square.possible.size() == 1) {
          square.answer = square.possible.iterator().next();
        }
        int num = square.isAnswer() ? 0 : mine[i][j].possible.size();
        System.out.printf("%d ", num);
        possiblesLeft += num;
      }
      System.out.println();
    }
    return possiblesLeft;
  }

  // Shared elimination for a row, column, or block
  private void processUnit(Square[][] mine, List<Cell> cells) {
    Set<Character> found = new HashSet<>();

    // Collect solved numbers in this unit
    for (Cell cell : cells) {
      Square sq = mine[cell.row()][cell.col()];
      if (sq.isAnswer()) {
        found.add(sq.answer);
      }
    }

    // Remove them from unsolved cells in this unit
    for (Cell cell : cells) {
      mine[cell.row()][cell.col()].possible.removeAll(found);
    }
  }

  private void processAll(Square[][] mine) {
    for (List<Cell> unit : ALL_UNITS) {
      processUnit(mine, unit);
    }
  }

  private boolean isValid(Square[][] mine, int row, int col, char val) {
    // row
    for (int j = 0; j < 9; j++) {
      if (j != col && mine[row][j].answer == val) return false;
    }

    // col
    for (int i = 0; i < 9; i++) {
      if (i != row && mine[i][col].answer == val) return false;
    }

    // 3x3 square
    int rStart = (row / 3) * 3;
    int cStart = (col / 3) * 3;
    for (int i = rStart; i < rStart + 3; i++) {
      for (int j = cStart; j < cStart + 3; j++) {
        if ((i != row || j != col) && mine[i][j].answer == val) return false;
      }
    }
    return true;
  }

  private boolean backtrack(Square[][] mine) {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (!mine[i][j].isAnswer()) {
          for (char c : mine[i][j].possible) {
            if (isValid(mine, i, j, c)) {
              mine[i][j].answer = c;
              if (backtrack(mine)) return true;
              mine[i][j].answer = '\0'; // undo
            }
          }
          return false; // no option worked
        }
      }
    }
    return true; // solved
  }

  public void solveSudoku(char[][] board) {
    Square[][] mine = new Square[9][9];
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (board[i][j] == '.') {
          mine[i][j] = new Square();
        } else {
          mine[i][j] = new Square(board[i][j]);
        }
      }
    }

    int prevPossiblesLeft = print(mine);
    boolean done = false;
    while (!done) {
      processAll(mine);

      int possiblesLeft = print(mine);
      if (possiblesLeft == prevPossiblesLeft) {
        // didn't make any progress... need to guess + add backtracking
        backtrack(mine);
      } else {
        prevPossiblesLeft = possiblesLeft;
      }
      done = possiblesLeft == 0;
    }

    if (done) {
      for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
          board[i][j] = mine[i][j].answer;
        }
      }
    } else {
      throw new IllegalStateException("never finished");
    }
  }
}
