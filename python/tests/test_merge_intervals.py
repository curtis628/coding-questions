import pytest

from leetcode.intervals.merge_intervals import Solution


@pytest.mark.parametrize(
    "intervals, expected",
    [
        ([[1, 3], [2, 6], [8, 10], [15, 18]], [[1, 6], [8, 10], [15, 18]]),
        ([[1, 4], [4, 5]], [[1, 5]]),
        ([[4, 7], [1, 4]], [[1, 7]]),
    ],
)
def test_dummy(intervals, expected):
    solver = Solution()
    assert solver.merge(intervals) == expected
