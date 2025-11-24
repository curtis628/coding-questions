import pytest

from leetcode.graph.surrounded_regions import Solution


@pytest.mark.parametrize(
    "board, final_board",
    [
        (
            [
                ["X", "X", "X", "X"],
                ["X", "O", "O", "X"],
                ["X", "X", "O", "X"],
                ["X", "O", "X", "X"],
            ],
            [
                ["X", "X", "X", "X"],
                ["X", "X", "X", "X"],
                ["X", "X", "X", "X"],
                ["X", "O", "X", "X"],
            ],
        ),
        (
            [
                ["X", "X", "X", "X"],
                ["X", "O", "O", "X"],
                ["X", "X", "X", "X"],
                ["X", "O", "O", "X"],
                ["O", "X", "X", "O"],
            ],
            [
                ["X", "X", "X", "X"],
                ["X", "X", "X", "X"],
                ["X", "X", "X", "X"],
                ["X", "X", "X", "X"],
                ["O", "X", "X", "O"],
            ],
        ),
        ([["X"]], [["X"]]),
    ],
)
def test_surrounded_regions(board, final_board):
    solver = Solution()
    solver.solve(board)
    assert board == final_board
