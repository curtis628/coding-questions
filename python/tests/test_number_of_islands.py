import pytest

from common.method import Method
from leetcode.graph.number_of_islands import Solution


@pytest.mark.parametrize(
    "grid, expected",
    [
        (
            [
                ["1", "1", "1", "1", "0"],
                ["1", "1", "0", "1", "0"],
                ["1", "1", "0", "0", "0"],
                ["0", "0", "0", "0", "0"],
            ],
            1,
        ),
        (
            [
                ["1", "1", "0", "0", "0"],
                ["1", "1", "0", "0", "0"],
                ["0", "0", "1", "0", "0"],
                ["0", "0", "0", "1", "1"],
            ],
            3,
        ),
    ],
)
@pytest.mark.parametrize(
    "method",
    [Method.BFS, Method.DFS],
)
def test_number_of_islands(method, grid, expected):
    solver = Solution(method)
    assert solver.numIslands(grid) == expected
