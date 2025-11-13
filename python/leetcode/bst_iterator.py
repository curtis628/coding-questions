"""173: https://leetcode.com/problems/binary-search-tree-iterator"""

from typing import Optional

from common.method import Method
from common.tree_node import TreeNode


class BSTIterator:

    @staticmethod
    def inorder(root: TreeNode):
        if not root:
            return
        yield from BSTIterator.inorder(root.left)
        yield root.val
        yield from BSTIterator.inorder(root.right)

    def __init__(self, root: Optional[TreeNode], method: Method = Method.DFS):
        self.method = method

        if method == Method.DFS:
            self.buffer = None
            self.iterator = BSTIterator.inorder(root)
            self._advance()
        elif method == Method.ITERATIVE:
            self.stack = []
            self._push_left(root)
        else:
            raise NotImplementedError(f"{method} not supported")

    def next(self) -> int:
        if self.method == Method.DFS:
            result = self.buffer
            self._advance()
            return result
        else:
            node = self.stack.pop()
            self._push_left(node.right)
            return node.val

    def has_next(self) -> bool:
        if self.method == Method.DFS:
            return self.buffer is not None
        else:
            return bool(self.stack)

    def _advance(self):
        """In order to support `hasNext()`, we must implement a lookahead buffering"""
        try:
            self.buffer = next(self.iterator)
        except StopIteration:
            self.buffer = None

    def _push_left(self, node):
        while node:
            self.stack.append(node)
            node = node.left
