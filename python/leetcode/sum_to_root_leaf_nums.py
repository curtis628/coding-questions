"""129: https://leetcode.com/problems/sum-root-to-leaf-numbers"""

from typing import Optional

from common.tree_node import TreeNode


class Solution:
    def sumNumbers(self, root: Optional[TreeNode]) -> int:
        def _dfs(node, total):
            if not node:
                return 0

            current_sum = total * 10 + node.val
            if not node.left and not node.right:  # is a leaf node
                return current_sum

            return _dfs(node.left, current_sum) + _dfs(node.right, current_sum)

        return _dfs(root, 0)
