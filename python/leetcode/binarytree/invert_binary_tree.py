"""226: https://leetcode.com/problems/invert-binary-tree"""

from collections import deque
from typing import Optional

from common.method import Method
from common.tree_node import TreeNode


class Solution:
    def __init__(self, method: Method = None):
        self.method = method if method else Method.DFS

    def invertTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        if self.method == Method.BFS:
            return self._bfs(root)
        else:
            return self._dfs(root)

    def _dfs(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        if not root:
            return None

        # Swap left and right
        root.left, root.right = root.right, root.left

        # Recurse into children
        self._dfs(root.left)
        self._dfs(root.right)

        return root

    def _bfs(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        if not root:
            return root

        queue = deque([root])
        while queue:
            node = queue.popleft()
            node.left, node.right = node.right, node.left

            if node:
                if node.left:
                    queue.append(node.left)
                if node.right:
                    queue.append(node.right)

        return root
