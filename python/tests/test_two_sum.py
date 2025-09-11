import pytest
from leetcode.two_sum import Solution


@pytest.mark.parametrize(
    "nums,target,expected",
    [
        ([2, 7, 11, 15], 9, [0, 1]),  # basic example
        ([3, 2, 4], 6, [1, 2]),  # pair later in list
        ([3, 3], 6, [0, 1]),  # duplicate values
        ([-1, -2, -3, -4, -5], -8, [2, 4]),  # negative numbers
    ],
)
def test_two_sum(nums, target, expected):
    solver = Solution()
    result = solver.twoSum(nums, target)
    assert result == expected

    result = solver.two_sum_sort_and_binary_search(nums, target)
    assert result == expected
