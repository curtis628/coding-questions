import pytest

from leetcode.stack_queue_heap.kth_largest_in_array import Solution


@pytest.mark.parametrize(
    "nums, k, expected",
    [
        ([3, 2, 1, 5, 6, 4], 2, 5),
        ([3, 2, 3, 1, 2, 4, 5, 5, 6], 4, 4),
    ],
)
def test_kth_largest_in_array(nums, k, expected):
    solver = Solution()
    assert solver.findKthLargest(nums, k) == expected
