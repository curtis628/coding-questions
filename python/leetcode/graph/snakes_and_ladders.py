"""909: https://leetcode.com/problems/snakes-and-ladders"""

from collections import deque
from typing import List


class Solution:

    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)

        # convert Boustrophedon style to zero-indexed, flattened list for easier use
        flat_board = list()
        left_to_right = True
        for row in range(n - 1, -1, -1):
            ordered_row = board[row] if left_to_right else board[row][::-1]
            final_row = [cell - 1 if cell != -1 else -1 for cell in ordered_row]
            flat_board.extend(final_row)
            left_to_right = not left_to_right

        queue = deque([(0, 0)])
        visited = {0}
        while queue:
            ndx, moves = queue.popleft()
            if ndx == n**2 - 1:
                return moves

            # Simulate all possible moves via BFS to find "shortest path" along edges
            # If you land (move_ndx) on a ladder | snake, transport immediately
            for move in range(1, 7):
                move_ndx = ndx + move
                if move_ndx >= len(flat_board):  # don't go off the end of the board
                    continue

                dest = flat_board[move_ndx]
                if dest != -1:
                    move_ndx = dest

                if move_ndx not in visited:
                    queue.append((move_ndx, moves + 1))
                    visited.add(move_ndx)

        return -1
