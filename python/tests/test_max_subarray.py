import pytest

from leetcode.dp.max_subarray import Solution


@pytest.mark.parametrize(
    "nums, expected",
    [
        ([-2, 1, -3, 4, -1, 2, 1, -5, 4], 6),
        ([1], 1),
        ([5, 4, -1, 7, 8], 23),
    ],
)
def test_max_subarray(nums, expected):
    solver = Solution()
    assert solver.maxSubArray(nums) == expected
