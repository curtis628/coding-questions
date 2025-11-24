"""130: https://leetcode.com/problems/surrounded-regions"""

from typing import List


class Solution:

    def solve(self, board: List[List[str]]) -> None:
        rows, cols = len(board), len(board[0])
        dirs = [(-1, 0), (1, 0), (0, 1), (0, -1)]  # N, S, E, W

        # Change `O` --> `*`. We will replace all safe `*` with `O` in _dfs
        for r in range(rows):
            for c in range(cols):
                board[r][c] = "*" if board[r][c] == "O" else board[r][c]

        def _dfs(row, col):
            """Any `O` connected to the border cannot be encompassed and --> `O`."""
            if 0 <= row < rows and 0 <= col < cols and board[row][col] == "*":
                board[row][col] = "O"

                for row_delta, col_delta in dirs:
                    _dfs(row + row_delta, col + col_delta)

        # first and last rows
        for r in {0, rows - 1}:
            for c in range(cols):
                _dfs(r, c)

        # left and right cols
        for c in {0, cols - 1}:
            for r in range(rows):
                _dfs(r, c)

        # any remaining "*" is enclosed, and should switch to X
        for r in range(rows):
            for c in range(cols):
                board[r][c] = "X" if board[r][c] == "*" else board[r][c]
