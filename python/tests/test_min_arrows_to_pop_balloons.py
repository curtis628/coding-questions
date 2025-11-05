import pytest

from leetcode.min_arrows_to_pop_balloons import Solution


@pytest.mark.parametrize(
    "points, expected",
    [
        ([[10, 16], [2, 8], [1, 6], [7, 12]], 2),
        ([[1, 2], [3, 4], [5, 6], [7, 8]], 4),
        ([[1, 2], [2, 3], [3, 4], [4, 5]], 2),
    ],
)
def test_min_arrows_to_pop_balloons(points, expected):
    solver = Solution()
    assert solver.findMinArrowShots(points) == expected
