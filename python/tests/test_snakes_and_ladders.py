import pytest

from leetcode.graph.snakes_and_ladders import Solution


@pytest.mark.parametrize(
    "board, expected",
    [
        (
            [
                [-1, -1, -1, -1, -1, -1],
                [-1, -1, -1, -1, -1, -1],
                [-1, -1, -1, -1, -1, -1],
                [-1, 35, -1, -1, 13, -1],
                [-1, -1, -1, -1, -1, -1],
                [-1, 15, -1, -1, -1, -1],
            ],
            4,
        ),
        ([[-1, -1], [-1, 3]], 1),
    ],
)
def test_snakes_and_ladders(board, expected):
    solver = Solution()
    assert solver.snakesAndLadders(board) == expected
