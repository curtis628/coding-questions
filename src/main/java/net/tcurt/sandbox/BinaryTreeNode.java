package net.tcurt.sandbox;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderClassName = "BinaryTreeNodeBuilder")
public class BinaryTreeNode {
  String val;
  BinaryTreeNode left;
  BinaryTreeNode right;

  public static class BinaryTreeNodeBuilder {
    public BinaryTreeNodeBuilder val(int val) {
      this.val = String.valueOf(val);
      return this;
    }

    // Manual override to keep String values working too
    public BinaryTreeNodeBuilder val(String val) {
      this.val = val;
      return this;
    }
  }
}
