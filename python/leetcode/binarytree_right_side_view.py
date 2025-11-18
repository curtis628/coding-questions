"""199: https://leetcode.com/problems/binary-tree-right-side-view"""

from collections import deque
from typing import List, Optional

from common.method import Method
from common.tree_node import TreeNode


class Solution:

    def __init__(self, method=Method.BFS):
        self.method = Method.BFS

    def rightSideView(self, root: Optional[TreeNode]) -> List[int]:
        if not root:
            return []

        if self.method == Method.BFS:
            return self._bfs(root)
        else:
            return self._dfs(root)

    def _bfs(self, root):
        queue = deque([root])
        result = []

        while queue:
            level_count = len(queue)
            for i in range(level_count):
                node = queue.popleft()
                if node.left:
                    queue.append(node.left)
                if node.right:
                    queue.append(node.right)

                # last one on the level goes in the result
                if i == level_count - 1:
                    result.append(node.val)

        return result

    def _dfs(self, root):
        result = []

        def _helper(node, level):
            if not node:
                return

            if len(result) == level:
                result.append(node.val)

            _helper(node.left, level + 1)
            _helper(node.right, level + 1)

        _helper(root, 0)
        return result
