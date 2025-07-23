package net.tcurt.sandbox.problems;

import java.util.Stack;
import lombok.extern.slf4j.Slf4j;

/**
 * From <a href="https://leetcode.com/problems/maximum-score-from-removing-substrings">Leetcode
 * #1717</a>
 */
@Slf4j
public class MaxScoreRemovingSubstrings {

  public int maximumGain(String s, int x, int y) {
    String highPriorityPair = x > y ? "ab" : "ba";
    int highPriorityScore = "ab".equals(highPriorityPair) ? x : y;

    String lowPriorityPair = "ab".equals(highPriorityPair) ? "ba" : "ab";
    int lowPriorityScore = "ab".equals(highPriorityPair) ? y : x;
    log.info(
        "'{}': highPriority [{}, score={}] lowPriority [{} , score={}]",
        s,
        highPriorityPair,
        highPriorityScore,
        lowPriorityPair,
        lowPriorityScore);

    // First, remove high priority/scoring pair... (and calculate score)
    String s1 = removeSubstrings(s, highPriorityPair);
    int sizeDiff = s.length() - s1.length();
    int totalScore = sizeDiff / highPriorityPair.length() * highPriorityScore;

    // Then remove low priority one...
    String s2 = removeSubstrings(s1, lowPriorityPair);
    sizeDiff = s1.length() - s2.length();
    totalScore += sizeDiff / lowPriorityPair.length() * lowPriorityScore;

    return totalScore;
  }

  private String removeSubstrings(String s, String pair) {
    Stack<Character> stack = new Stack<>();
    Character startChar = pair.charAt(0);
    Character endChar = pair.charAt(1);
    log.debug("Remove substrings: pair={}", pair);

    for (char c : s.toCharArray()) {
      log.debug("  Processing: {}", c);

      // found pair!
      if (!stack.isEmpty() && startChar.equals(stack.peek()) && endChar.equals(c)) {
        log.debug("    found pair!");
        stack.pop();
      } else {
        stack.push(c);
      }
    }

    StringBuilder sb = new StringBuilder(stack.size());
    while (!stack.isEmpty()) {
      sb.insert(0, stack.pop());
    }

    return sb.toString();
  }
}
