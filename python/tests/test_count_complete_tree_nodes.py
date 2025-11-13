import pytest

from common.tree_node import TreeNode
from leetcode.count_complete_tree_nodes import Solution


@pytest.mark.parametrize(
    "root, expected",
    [
        (TreeNode.from_level_list([1, 2, 3, 4, 5]), 5),
        (TreeNode.from_level_list([1, 2, 3, 4, 5, 6]), 6),
        (TreeNode.from_level_list([1, 2, 3, 4, 5, 6, 7]), 7),
        (TreeNode.from_level_list([]), 0),
        (TreeNode.from_level_list([1]), 1),
    ],
)
def test_count_complete_tree_nodes(root, expected):
    solver = Solution()
    assert solver.countNodes(root) == expected
