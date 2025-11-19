import copy

import pytest

from common.method import Method
from common.tree_node import TreeNode
from leetcode.binarytree.invert_binary_tree import Solution


@pytest.mark.parametrize(
    "root, expected",
    [
        (
            TreeNode.from_level_list([4, 2, 7, 1, 3, 6, 9]),
            TreeNode.from_level_list([4, 7, 2, 9, 6, 3, 1]),
        ),
        (TreeNode.from_level_list([2, 1, 3]), TreeNode.from_level_list([2, 3, 1])),
        (TreeNode.from_level_list([]), TreeNode.from_level_list([])),
        (TreeNode.from_level_list([1, 2]), TreeNode.from_level_list([1, None, 2])),
    ],
)
@pytest.mark.parametrize(
    "method",
    [Method.BFS, Method.DFS],
)
def test_invert_tree(root, method, expected):
    solver = Solution(method)
    actual = solver.invertTree(copy.deepcopy(root))
    assert actual == expected
