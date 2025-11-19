"""222: https://leetcode.com/problems/count-complete-tree-nodes"""

from typing import Optional

from common.tree_node import TreeNode


class Solution:

    def countNodes(self, root: Optional[TreeNode]) -> int:
        def left_height(node):
            height = 0
            while node:
                height += 1
                node = node.left
            return height

        def right_height(node):
            height = 0
            while node:
                height += 1
                node = node.right
            return height

        l_height, r_height = left_height(root), right_height(root)
        if l_height == r_height:
            return (1 << l_height) - 1  # same as 2 ** l_height - 1, but faster
        else:
            # height is the number of nodes in left tree + nodes in right tree + root
            # once height of subtree is complete, we can calc the result right away
            # O(log n) ^ 2
            return 1 + self.countNodes(root.left) + self.countNodes(root.right)
