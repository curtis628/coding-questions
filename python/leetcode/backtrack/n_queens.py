"""51: https://leetcode.com/problems/n-queens"""

from typing import List


class Solution:

    def solveNQueens(self, n: int) -> List[List[str]]:
        results = []
        queen_col_placement = []
        col_used = set()  # store cols used
        diag1_used = set()  # store (row - col) main diagonal: "\"
        diag2_used = set()  # store (row + col) anti-diagonal: "/"

        def _dfs(row):
            if row == n:
                print("Found solution!")
                board = []
                for r in range(n):
                    line = ["."] * n
                    queen_ndx = queen_col_placement[r]
                    line[queen_ndx] = "Q"
                    board.append("".join(line))

                results.append(board)
                return

            for col in range(n):
                if (
                    col not in col_used
                    and row - col not in diag1_used
                    and row + col not in diag2_used
                ):
                    # Choose: mark constraints for placing a Q at row,col
                    print(f"Try placing Q at {row}, {col}")
                    col_used.add(col)
                    diag1_used.add(row - col)
                    diag2_used.add(row + col)
                    queen_col_placement.append(col)

                    # Explore
                    _dfs(row + 1)

                    # Unchoose / backtrack
                    col_used.remove(col)
                    diag1_used.remove(row - col)
                    diag2_used.remove(row + col)
                    queen_col_placement.pop()

        _dfs(0)
        return results
