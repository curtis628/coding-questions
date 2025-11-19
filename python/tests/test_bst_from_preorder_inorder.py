import copy

import pytest

from common.method import Method
from common.tree_node import TreeNode
from leetcode.binarytree.bst_from_preorder_inorder import Solution


@pytest.mark.parametrize(
    "preorder, inorder, expected",
    [
        (
            [3, 9, 20, 15, 7],
            [9, 3, 15, 20, 7],
            TreeNode.from_level_list([3, 9, 20, None, None, 15, 7]),
        ),
        ([-1], [-1], TreeNode.from_level_list([-1])),
    ],
)
@pytest.mark.parametrize(
    "method",
    [Method.DFS, Method.OPTIMAL],
)
def test_bst_from_preorder_inorder(preorder, inorder, method, expected):
    solver = Solution(method)
    actual = solver.buildTree(copy.deepcopy(preorder), inorder)
    assert actual == expected
