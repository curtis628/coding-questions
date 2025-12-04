import pytest

from leetcode.backtrack.combinations import Solution


@pytest.mark.parametrize(
    "n, k, expected",
    [
        (4, 2, [[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]),
        (4, 3, [[1, 2, 3], [1, 2, 4], [1, 3, 4], [2, 3, 4]]),
        (1, 1, [[1]]),
    ],
)
def test_combinations(n, k, expected):
    solver = Solution()
    assert solver.combine(n, k) == expected
