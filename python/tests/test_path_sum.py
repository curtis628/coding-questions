import pytest

from common.method import Method
from common.tree_node import TreeNode
from leetcode.binarytree.path_sum import Solution


@pytest.mark.parametrize(
    "root, target_sum, expected",
    [
        (
            TreeNode.from_level_list(
                [5, 4, 8, 11, None, 13, 4, 7, 2, None, None, None, 1]
            ),
            22,
            True,
        ),
        (TreeNode.from_level_list([1, 2, 3]), 5, False),
        (TreeNode.from_level_list([]), 0, False),
        (TreeNode.from_level_list([1, 2]), 1, False),
        (TreeNode.from_level_list([1]), 1, True),
    ],
)
@pytest.mark.parametrize(
    "method",
    [Method.DFS, Method.ITERATIVE],
)
def test_path_sum(root, target_sum, method, expected):
    solver = Solution(method)
    assert solver.hasPathSum(root, target_sum) == expected
