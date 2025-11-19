import pytest

from common.method import Method
from common.tree_node import TreeNode
from leetcode.binarytree.binarytree_right_side_view import Solution


@pytest.mark.parametrize(
    "root, expected",
    [
        (TreeNode.from_level_list([1, 2, 3, None, 5, None, 4]), [1, 3, 4]),
        (TreeNode.from_level_list([1, 2, 3, 4, None, None, None, 5]), [1, 3, 4, 5]),
        (TreeNode.from_level_list([1, None, 3]), [1, 3]),
        (TreeNode.from_level_list([]), []),
    ],
)
@pytest.mark.parametrize(
    "method",
    [Method.BFS, Method.DFS],
)
def test_binarytree_right_side_view(root, method, expected):
    solver = Solution(method)
    assert solver.rightSideView(root) == expected
