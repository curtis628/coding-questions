"""200: https://leetcode.com/problems/number-of-islands"""

from collections import deque
from typing import List

from common.method import Method


class Solution:

    def __init__(self, method=Method.BFS):
        self.method = method

    def numIslands(self, grid: List[List[str]]) -> int:
        self.rows, self.cols = len(grid), len(grid[0])
        self.visited = [[False] * self.cols for _ in range(self.rows)]
        self.dirs = [(-1, 0), (1, 0), (0, 1), (0, -1)]  # N, S, E, W
        self.islands = 0
        self.grid = grid

        if self.method == Method.BFS:
            self._count_islands_bfs()
        else:
            self._count_islands_dfs()

        return self.islands

    def _is_valid(self, row, col):
        return (
            0 <= row < self.rows
            and 0 <= col < self.cols
            and not self.visited[row][col]
            and self.grid[row][col] == "1"
        )

    def _count_islands_dfs(self):
        def _dfs(row, col):
            self.visited[row][col] = True

            for dir_row, dir_col in self.dirs:
                nei_row, nei_col = row + dir_row, col + dir_col
                if self._is_valid(nei_row, nei_col):
                    _dfs(nei_row, nei_col)

        for r in range(self.rows):
            for c in range(self.cols):
                if self._is_valid(r, c):
                    self.islands += 1
                    print(f"Process island={self.islands} using DFS: in {r=} {c=}")
                    _dfs(r, c)

    def _count_islands_bfs(self):

        def _bfs(row, col):
            queue = deque([(row, col)])
            self.visited[row][col] = True

            while queue:
                row, col = queue.popleft()

                for dir_row, dir_col in self.dirs:
                    nei_row, nei_col = row + dir_row, col + dir_col
                    if self._is_valid(nei_row, nei_col):
                        queue.append((nei_row, nei_col))
                        self.visited[nei_row][nei_col] = True

        for r in range(self.rows):
            for c in range(self.cols):
                if self._is_valid(r, c):
                    self.islands += 1
                    print(f"Process island={self.islands} using BFS: in {r=} {c=}")
                    _bfs(r, c)
