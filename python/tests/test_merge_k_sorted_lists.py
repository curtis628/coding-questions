import pytest

from common.list_node import ListNode
from leetcode.linkedlist.merge_k_sorted_lists import Solution


@pytest.mark.parametrize(
    "lists, expected",
    [
        (
            [
                ListNode.from_list([1, 4, 5]),
                ListNode.from_list([1, 3, 4]),
                ListNode.from_list([2, 6]),
            ],
            ListNode.from_list([1, 1, 2, 3, 4, 4, 5, 6]),
        ),
        ([], None),
        ([[]], None),
    ],
)
def test_merge_k_sorted_lists(lists, expected):
    solver = Solution()
    assert solver.mergeKLists(lists) == expected
