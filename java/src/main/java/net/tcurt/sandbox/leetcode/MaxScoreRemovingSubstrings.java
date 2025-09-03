package net.tcurt.sandbox.leetcode;

import java.util.Stack;
import lombok.extern.slf4j.Slf4j;

/**
 * From <a href="https://leetcode.com/problems/maximum-score-from-removing-substrings">Leetcode
 * #1717</a>
 */
@Slf4j
public class MaxScoreRemovingSubstrings {

  public int maximumGain(String s, int x, int y) {
    String highPriorityPair = (x > y) ? "ab" : "ba";
    int highPriorityScore = (x > y) ? x : y;
    String lowPriorityPair = (x > y) ? "ba" : "ab";
    int lowPriorityScore = (x > y) ? y : x;

    // First, remove high priority/scoring pair... (and calculate score)
    String remaining = removeSubstrings(s, highPriorityPair);
    int score = (s.length() - remaining.length()) / 2 * highPriorityScore;

    // Then remove low priority one...
    String end = removeSubstrings(s, lowPriorityPair);
    score += (end.length() - remaining.length()) / 2 * lowPriorityScore;

    return score;
  }

  private String removeSubstrings(String s, String pair) {
    Stack<Character> stack = new Stack<>();
    for (char c : s.toCharArray()) {
      if (stack.isEmpty()) {
        stack.push(c);
      } else {
        StringBuilder sb = new StringBuilder();
        char prev = stack.peek();
        sb.append(prev).append(c);

        if (pair.contentEquals(sb)) {
          stack.pop(); // found match! Remove it from stack.
        } else {
          stack.push(c); // no match. Add it to stack.
        }
      }
    }

    StringBuilder remaining = new StringBuilder();
    int remainingSize = stack.size();
    for (int i = 0; i < remainingSize; i++) {
      remaining.insert(0, stack.pop());
    }

    return remaining.toString();
  }
}
