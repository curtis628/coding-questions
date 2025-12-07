import pytest

from common.list_node import ListNode
from leetcode.linkedlist.sort_linked_list import Solution


@pytest.mark.parametrize(
    "head, expected",
    [
        (ListNode.from_list([4, 2, 1, 3]), ListNode.from_list([1, 2, 3, 4])),
        (ListNode.from_list([-1, 5, 3, 4, 0]), ListNode.from_list([-1, 0, 3, 4, 5])),
        (ListNode.from_list([]), ListNode.from_list([])),
        (
            ListNode.from_list([1, 4, 2, 3, 7, 6, 5, 10, 8, 9]),
            ListNode.from_list([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]),
        ),
    ],
)
def test_sort_list(head, expected):
    solver = Solution()
    assert solver.sortList(head) == expected


@pytest.mark.parametrize(
    "head, expected",
    [
        ([1, 4, 2, 3, 7, 6, 5, 10, 8, 9], [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]),
    ],
)
def test_sort_array(head, expected):
    solver = Solution()
    assert solver.sort_array(head) == expected
