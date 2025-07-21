package net.tcurt.sandbox.problems;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
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
    Map<Integer, StringBuilder> levelToStringBuilderMap = new HashMap<>();

    Queue<BinaryTreeNode> queue = new LinkedList<>();
    queue.add(rootNode);
    int level = 0;

    while (!queue.isEmpty()) {
      log.debug("Processing level={}", level);
      // current size of queue includes all nodes at level
      int levelItems = queue.size();
      StringBuilder sb = new StringBuilder();
      levelToStringBuilderMap.put(level, sb);

      for (int i = 0; i < levelItems; i++) {
        BinaryTreeNode node = queue.remove();
        sb.append(node.getVal());
        log.debug("  Adding [{}]", node.getVal());

        if (node.getLeft() == null && node.getRight() == null) {
          continue;
        }

        // Add dummy char for missing child to ensure exact mirror image
        if (node.getLeft() != null) {
          queue.add(node.getLeft());
        } else {
          queue.add(BinaryTreeNode.builder().val("$").build());
        }

        if (node.getRight() != null) {
          queue.add(node.getRight());
        } else {
          queue.add(BinaryTreeNode.builder().val("$").build());
        }
      }
      level++;
    }

    // if each level of the map is a palindrome --> symmetric!
    for (int i = 0; i < levelToStringBuilderMap.size(); i++) {
      String levelValues = levelToStringBuilderMap.get(i).toString();
      log.debug("level={} values: {}", i, levelValues);

      for (int startNdx = 0, endNdx = levelValues.length() - 1;
          startNdx <= endNdx;
          startNdx++, endNdx--) {
        char start = levelValues.charAt(startNdx);
        char end = levelValues.charAt(endNdx);
        if (start != end) {
          {
            log.debug("  level={} isn't symmetric: startChar={} endChar={}", i, start, end);
            return false;
          }
        }
      }
    }

    return true;
  }
}
