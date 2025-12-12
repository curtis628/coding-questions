import pytest

from leetcode.dp.max_sum_circular_subarray import Solution


@pytest.mark.parametrize(
    "nums, expected",
    [
        ([1, -2, 3, -2], 3),
        ([5, -3, 5], 10),
        ([-3, -2, -3], -2),
    ],
)
def test_max_sum_circular_subarray(nums, expected):
    solver = Solution()
    assert solver.maxSubarraySumCircular(nums) == expected
