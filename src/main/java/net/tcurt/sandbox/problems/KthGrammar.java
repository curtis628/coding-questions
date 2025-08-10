package net.tcurt.sandbox.problems;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.tcurt.sandbox.Method;
import net.tcurt.sandbox.TreeNode;

/**
 * From <a href="https://leetcode.com/problems/k-th-symbol-in-grammar">Leetcode 779</a>
 *
 * <pre>
 *  1: 0
 *  2: 01
 *  3: 0110
 *  4: 01101001
 *  5: 0110100110010110
 *  6: 01101001100101101001011001101001
 *  7: 0110100110010110100101100110100110010110011010010110100110010110
 * </pre>
 */
@RequiredArgsConstructor
@Slf4j
public class KthGrammar {
  private final Method method;

  public int kthGrammar(int n, int k) {
    int result = -1;
    switch (method) {
      case RECURSIVE -> {
        String line = recursive(n - 1);
        char digit = line.charAt(k - 1);
        result = Character.getNumericValue(digit);
      }
      case BINARY_TREE -> {
        TreeNode root = new TreeNode(0);
        TreeNode child = depthFirstSearch(root, n, k);
        result = child.val;
      }
    }
    return result;
  }

  private TreeNode depthFirstSearch(TreeNode root, int n, int k) {
    if (n == 1) {
      return root;
    }

    int nodesOnLevel = (int) Math.pow(2.0, n - 1);
    int halfway = nodesOnLevel / 2;
    boolean isLeft = k <= halfway;
    int childVal, nextK;
    if (isLeft) {
      childVal = root.val == 0 ? 0 : 1;
      nextK = k;
    } else {
      childVal = root.val == 0 ? 1 : 0;
      nextK = k - halfway;
    }

    TreeNode child = new TreeNode(childVal);
    return depthFirstSearch(child, n - 1, nextK);
  }

  private String recursive(int n) {
    if (n == 0) {
      return "0";
    }
    String prev = recursive(n - 1);

    StringBuilder sb = new StringBuilder();
    for (char c : prev.toCharArray()) {
      if (c == '0') {
        sb.append("01");
      } else if (c == '1') {
        sb.append("10");
      }
    }
    return sb.toString();
  }
}
