package net.tcurt.sandbox.problems;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;
import net.tcurt.sandbox.BinaryTreeNode;

/**
 * Given root node of binary tree, check if it is symmetric (mirror from left to right). Examples of
 * symmetric binary trees are below. This was an interview question I received from S.C. (AI sec.) -
 * which I struggled to code in the moment.
 *
 * <p>The idea is to:
 *
 * <ul>
 *   <li>level-traverse the binary tree (which I now know requires a Queue (FIFO) )
 *   <li>for each level, track items in-order (if node is missing a child, insert a dummy val {@code
 *       $} to ensure mirror / palindrome
 *   <li>a tree is symmetric iff each level contains a palindrome
 * </ul>
 *
 * <pre>
 * 0               0
 *              /     \
 * 1          1         1
 *          /   \     /   \
 * 2       2    1    1     2
 *        / \  / \  / \   / \
 * 3     1  2 3  1 1  3  2   1
 * </pre>
 *
 * <pre>
 * 0               0
 *              /     \
 * 1          1         1
 *              \     /
 * 2            1    1
 * </pre>
 */
@Slf4j
public class SymmetricBinaryTree {

  public boolean isSymmetric(BinaryTreeNode rootNode) {
    Queue<BinaryTreeNode> queue = new ArrayDeque<>();
    List<String> levels = new ArrayList<>();
    queue.offer(rootNode);
    while (!queue.isEmpty()) {
      int nodesOnLevel = queue.size();
      StringBuilder sb = new StringBuilder();

      for (int i = 0; i < nodesOnLevel; i++) {
        BinaryTreeNode node = queue.poll();
        sb.append(node.getVal());

        if (node.getLeft() != null || node.getRight() != null) {
          BinaryTreeNode placeHolder = BinaryTreeNode.builder().val("$").build();
          BinaryTreeNode left = node.getLeft() != null ? node.getLeft() : placeHolder;
          queue.offer(left);

          BinaryTreeNode right = node.getRight() != null ? node.getRight() : placeHolder;
          queue.offer(right);
        }
      }
      levels.add(sb.toString());
    }

    for (String level : levels) {
      for (int startNdx = 0, endNdx = level.length() - 1; startNdx < endNdx; startNdx++, endNdx--) {
        char startChar = level.charAt(startNdx);
        char endChar = level.charAt(endNdx);
        if (startChar != endChar) { // not a palindrome --> not a symmetric tree
          return false;
        }
      }
    }
    return true;
  }
}
