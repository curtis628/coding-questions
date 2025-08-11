package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class UniqueBinarySearchTreesTest {
  private UniqueBinarySearchTrees underTest = new UniqueBinarySearchTrees();

  // find numTrees for 3
  //   root 1: 2
  //     left=[] right=[2,3]
  //     left=[] right=[3,2]
  //   root 2: 1
  //     left=[1] right=[3]
  //   root 3: 2
  //     left=[2,1] right=[]
  //     left=[1,2] right=[]
  // 2 + 1 + 2 = 5
  @Test
  void n3is5() {
    assertThat(underTest.numTrees(3)).isEqualTo(5);
  }

  @Test
  void n1is1() {
    assertThat(underTest.numTrees(1)).isEqualTo(1);
  }

  // find numTrees for 4
  //   root 1: 5
  //     left=[] right=[2,3,4]
  //     left=[] right=[2,4,3]
  //     left=[] right=[3,2,4]
  //     left=[] right=[4,2,3]
  //     left=[] right=[4,3,2]
  //   root 2: 2
  //     left=[1] right=[3,4]
  //     left=[1] right=[4,3]
  //   root 3: 2
  //     left=[2,1] right=[4]
  //     left=[1,2] right=[4]
  //   root 4: 5
  //     left=[1,2,3] right=[]
  //     left=[1,3,2] right=[]
  //     left=[2,1,3] right=[]
  //     left=[3,2,1] right=[]
  //     left=[3,1,2] right=[]
  // 5 + 2 + 2 + 5 = 14
  @Test
  void n4is14() {
    assertThat(underTest.numTrees(4)).isEqualTo(14);
  }
}
