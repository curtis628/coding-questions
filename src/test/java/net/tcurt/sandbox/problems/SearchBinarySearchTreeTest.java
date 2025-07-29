package net.tcurt.sandbox.problems;

import static org.assertj.core.api.Assertions.assertThat;

import net.tcurt.sandbox.TreeNode;
import org.junit.jupiter.api.Test;

public class SearchBinarySearchTreeTest {
  private SearchBinarySearchTree underTest = new SearchBinarySearchTree();

  /**
   * Example for small tree
   *
   * <pre>
   *          4
   *        /   \
   *       2     7
   *     /   \
   *    1     3
   * </pre>
   */
  private TreeNode createSmallTree() {
    TreeNode root = new TreeNode(4);
    TreeNode n2 = new TreeNode(2);
    TreeNode n7 = new TreeNode(7);
    TreeNode n1 = new TreeNode(1);
    TreeNode n3 = new TreeNode(3);

    root.left = n2;
    root.right = n7;
    n2.left = n1;
    n2.right = n3;

    return root;
  }

  @Test
  void smallTree_found() {
    TreeNode root = createSmallTree();
    TreeNode foundRoot = underTest.searchBST(root, 2);
    assertThat(foundRoot).isNotNull();
    assertThat(foundRoot.val).isEqualTo(2);
    assertThat(foundRoot.left.val).isEqualTo(1);
    assertThat(foundRoot.right.val).isEqualTo(3);
  }

  @Test
  void smallTree_notFonud() {
    TreeNode root = createSmallTree();
    TreeNode foundRoot = underTest.searchBST(root, 5);
    assertThat(foundRoot).isNull();
  }
}
