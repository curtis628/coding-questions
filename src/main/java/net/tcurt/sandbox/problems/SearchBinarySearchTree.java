package net.tcurt.sandbox.problems;

import net.tcurt.sandbox.TreeNode;

/**
 * From <a href="https://leetcode.com/problems/search-in-a-binary-search-tree/">Leetcode 700</a>.
 */
public class SearchBinarySearchTree {

  public TreeNode searchBST(TreeNode root, int val) {
    if (root == null) return null;

    // DFS, in-order traversal
    if (root.val == val) {
      return root;
    }

    if (val < root.val) {
      return searchBST(root.left, val);
    } else {
      return searchBST(root.right, val);
    }
  }
}
