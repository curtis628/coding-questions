from collections import deque


class TreeNode:
    """LeetCode definition for binary tree node (+ testing and debugging helpers)"""

    @classmethod
    def from_level_list(cls, values):
        """Builds a BST from a level-order list, handling None children properly."""
        if not values:
            return None

        iter_vals = iter(values)
        root = cls(next(iter_vals))
        queue = deque([root])

        while queue:
            node = queue.popleft()
            try:
                left_val = next(iter_vals)
                if left_val is not None:
                    node.left = cls(left_val)
                    queue.append(node.left)
                # else leave as None

                right_val = next(iter_vals)
                if right_val is not None:
                    node.right = cls(right_val)
                    queue.append(node.right)
                # else leave as None
            except StopIteration:
                break

        return root

    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

    def __eq__(self, other):
        """Deep equality: True if both trees have the same structure and values."""
        if not isinstance(other, TreeNode):
            return False
        # Recursively compare value and both subtrees
        return (
            self.val == other.val
            and self.left == other.left
            and self.right == other.right
        )

    def __repr__(self):
        """Readable string representation for debugging."""
        return f"TreeNode({self.val}, left={repr(self.left)}, right={repr(self.right)})"
