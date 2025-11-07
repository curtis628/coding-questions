import pytest

from common.list_node import ListNode
from leetcode.remove_duplicates_from_linked_list import Solution


@pytest.mark.parametrize(
    "head, expected",
    [
        (ListNode.from_list([1, 2, 3, 3, 4, 4, 5]), ListNode.from_list([1, 2, 5])),
        (ListNode.from_list([1, 1, 1, 2, 3]), ListNode.from_list([2, 3])),
        (ListNode.from_list([1, 1, 2, 2, 2, 2, 3, 3]), ListNode.from_list([])),
    ],
)
def test_delete_duplicates(head, expected):
    solver = Solution()
    assert solver.deleteDuplicates(head) == expected
