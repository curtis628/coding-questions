import pytest

from common.tree_node import TreeNode
from leetcode.sum_to_root_leaf_nums import Solution


@pytest.mark.parametrize(
    "root, expected",
    [
        (TreeNode.from_level_list([1, 2, 3]), 25),
        (TreeNode.from_level_list([4, 9, 0, 5, 1]), 1026),
    ],
)
def test_sum_to_root_leaf_nums(root, expected):
    solver = Solution()
    assert solver.sumNumbers(root) == expected
