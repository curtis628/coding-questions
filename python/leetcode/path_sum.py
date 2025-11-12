"""112: https://leetcode.com/problems/path-sum"""

from typing import Optional

from common.method import Method
from common.tree_node import TreeNode


class Solution:
    def __init__(self, method=Method.DFS):
        self.method = method

    def hasPathSum(self, root: Optional[TreeNode], targetSum: int) -> bool:
        if self.method == Method.DFS:
            return self._dfs(root, targetSum)
        else:
            return self._iterative(root, targetSum)

    def _iterative(self, root: Optional[TreeNode], targetSum: int) -> bool:
        """Example showing how to do a DFS iteratively (no recursion)"""
        if not root:
            return False

        stack = [(root, root.val)]

        while stack:
            node, total = stack.pop()
            if not node.left and not node.right and total == targetSum:
                return True
            if node.left:
                stack.append((node.left, total + node.left.val))
            if node.right:
                stack.append((node.right, total + node.right.val))

        return False

    def _dfs(self, root: Optional[TreeNode], targetSum: int) -> bool:
        if not root:
            return False

        targetSum -= root.val
        if not root.left and not root.right:
            return targetSum == 0

        return self._dfs(root.left, targetSum) or self._dfs(root.right, targetSum)
