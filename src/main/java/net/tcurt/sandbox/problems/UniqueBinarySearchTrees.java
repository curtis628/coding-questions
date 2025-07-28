package net.tcurt.sandbox.problems;

import lombok.extern.slf4j.Slf4j;

/** From <a href="https://leetcode.com/problems/unique-binary-search-trees">Leetcode 96</a> */
@Slf4j
public class UniqueBinarySearchTrees {

  // dp[0]: 1
  // dp[1]: 1
  // dp[2]: 2
  // dp[3]: 5
  // find numTrees for n=4
  //   root=1: left=[]      (or dp[0]=1)   right=[2,3,4] (or dp[3]=5). 1 * 5 = 5
  //   root=2: left=[1]     (or dp[1]=1)   right=[3,4]   (or dp[2]=2). 1 * 2 = 2
  //   root=3: left=[1,2]   (or dp[2]=2)   right=[4]     (or dp[1]=2). 2 * 1 = 2
  //   root=4: left=[1,2,3] (or dp[3]=5)   right=[]      (or dp[0]=1). 5 * 1 = 5
  // sum together: 5 + 2 + 2 + 5 = 14
  //
  public int numTrees(int n) {
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
