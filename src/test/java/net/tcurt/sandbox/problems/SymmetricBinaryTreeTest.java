package net.tcurt.sandbox.problems;

import static net.tcurt.sandbox.BinaryTreeNode.builder;
import static org.assertj.core.api.Assertions.assertThat;

import net.tcurt.sandbox.BinaryTreeNode;
import org.junit.jupiter.api.Test;

public class SymmetricBinaryTreeTest {
  SymmetricBinaryTree tester = new SymmetricBinaryTree();

  @Test
  void oneLevel() {
    BinaryTreeNode root = BinaryTreeNode.builder().val(0).build();
    assertThat(tester.isSymmetric(root)).isTrue();
  }

  /**
   *
   *
   * <pre>
   * 0               0
   *              /     \
   * 1          1         1
   *          /   \     /   \
   * 2       2    1    1     2
   *        / \  / \  / \   / \
   * 3     1  2 3  1 1  3  2  1
   * </pre>
   */
  @Test
  void symmetric_full4layerTree() {
    BinaryTreeNode tree =
        builder()
            .val(0) // root
            .left(
                builder()
                    .val(1)
                    .left(
                        builder()
                            .val(2)
                            .left(builder().val(1).build())
                            .right(builder().val(2).build())
                            .build())
                    .right(
                        builder()
                            .val(1)
                            .left(builder().val(3).build())
                            .right(builder().val(1).build())
                            .build())
                    .build())
            .right(
                builder()
                    .val(1)
                    .left(
                        builder()
                            .val(1)
                            .left(builder().val(1).build())
                            .right(builder().val(3).build())
                            .build())
                    .right(
                        builder()
                            .val(2)
                            .left(builder().val(2).build())
                            .right(builder().val(1).build())
                            .build())
                    .build())
            .build();

    assertThat(tester.isSymmetric(tree)).isTrue();
  }

  /**
   *
   *
   * <pre>
   * 0               0
   *              /     \
   * 1          1         2
   * </pre>
   */
  @Test
  void unsymmetric_simple() {
    BinaryTreeNode tree =
        builder()
            .val(0) // root
            .left(builder().val(1).build())
            .right(builder().val(2).build())
            .build();
    assertThat(tester.isSymmetric(tree)).isFalse();
  }

  /**
   *
   *
   * <pre>
   * 0               0
   *              /     \
   * 1          1         1
   *              \     /
   * 2            2    2
   * </pre>
   */
  @Test
  void symmetric_nonFull() {
    BinaryTreeNode tree =
        builder()
            .val(0) // root
            .left(builder().val(1).right(builder().val(2).build()).build())
            .right(builder().val(1).left(builder().val(2).build()).build())
            .build();
    assertThat(tester.isSymmetric(tree)).isTrue();
  }

  /**
   * This isn't symmetric as it's not a mirror image!
   *
   * <pre>
   * 0               0
   *              /     \
   * 1          1         1
   *              \         \
   * 2            2          2
   * </pre>
   */
  @Test
  void unsymmetricTricky_nonFull() {
    BinaryTreeNode tree =
        builder()
            .val(0) // root
            .left(builder().val(1).right(builder().val(2).build()).build())
            .right(builder().val(1).right(builder().val(2).build()).build())
            .build();
    assertThat(tester.isSymmetric(tree)).isFalse();
  }
}
