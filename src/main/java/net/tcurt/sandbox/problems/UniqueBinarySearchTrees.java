package net.tcurt.sandbox.problems;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/** From <a href="https://leetcode.com/problems/unique-binary-search-trees">Leetcode 96</a> */
@Slf4j
public class UniqueBinarySearchTrees {

  private record StartEndPair(int start, int end) {}

  private Map<StartEndPair, Integer> cache = new HashMap();

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
  // 5 + 2 + 2 + 5
  public int numTrees(int n) {
    return countBST(1, n);
  }

  private int countBST(int start, int end) {
    if (start >= end) return 1;

    StartEndPair node = new StartEndPair(start, end);
    if (cache.containsKey(node)) {
      log.debug("Avoiding call for node={}", node);
      return cache.get(node);
    }

    int sum = 0;
    for (int i = start; i <= end; i++) {
      int left = countBST(start, i - 1);
      int right = countBST(i + 1, end);
      sum += left * right;
    }

    cache.put(node, sum);
    return sum;
  }

  // NOTE: I don't really understand how this is working...
  // 1 2 3 4 5 6 7 ... n
  //     ^ (pick root)
  //     i
  // [i-1] [n-1]
  private int numTreesWithMemo(int n) {
    int[] memo = new int[n + 1];
    memo[0] = 1;
    memo[1] = 1; // only one one to assemble BST of zero or 1 nodes...

    for (int i = 2; i <= n; i++) {
      for (int j = 1; j <= i; j++) {
        int leftPossibilities = memo[j - 1];
        int rightPossibilities = memo[i - j];
        memo[i] += leftPossibilities * rightPossibilities;
      }
      System.out.printf("# of possible BSTs with n=%d ? %d\n", i, memo[i]);
    }
    return memo[n];
  }
}
