package net.tcurt.sandbox.leetcode;

import net.tcurt.sandbox.TreeNode;

/**
 * From <a href="https://leetcode.com/problems/search-in-a-binary-search-tree/">Leetcode 700</a>.
 */
public class SearchBinarySearchTree {

  public TreeNode searchBST(TreeNode root, int val) {
    if (root == null) return null;
    // DFS, in-order traversal
    if (val == root.val) return root;

    TreeNode next = (val < root.val) ? searchBST(root.left, val) : searchBST(root.right, val);
    return next;
  }
}
