"""105: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal"""  # noqa

from typing import List, Optional

from common.method import Method
from common.tree_node import TreeNode


class Solution:
    def __init__(self, method: Method = None):
        self.method = method if method else Method.DFS

    def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        if self.method == Method.DFS:
            return self._dfs(preorder, inorder)
        else:
            return self._optimal(preorder, inorder)

    def _optimal(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        inorder_lookup = {val: i for i, val in enumerate(inorder)}
        preorder_ndx = 0

        def helper(left, right):
            nonlocal preorder_ndx
            if left > right:
                return None

            # Step 1: pick current root
            root = TreeNode(preorder[preorder_ndx])
            preorder_ndx += 1

            # Step 2: split by inorder index
            mid = inorder_lookup[root.val]

            # Step 3: recurse left and right
            root.left = helper(left, mid - 1)
            root.right = helper(mid + 1, right)

            return root

        return helper(0, len(inorder) - 1)

    def _dfs(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        # preorder: root - left - right
        #  inorder: left - root - right
        if not preorder or not inorder:
            return None

        root = TreeNode(preorder.pop(0))  # pop(0) --> O(n)
        root_ndx = inorder.index(
            root.val
        )  # index  --> O(n) --> O(n^2) + slicing overhead

        root.left = self._dfs(preorder, inorder[0:root_ndx])
        root.right = self._dfs(preorder, inorder[root_ndx + 1 :])

        return root
