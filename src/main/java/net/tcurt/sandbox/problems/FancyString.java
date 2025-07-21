package net.tcurt.sandbox.problems;

/**
 * From <a
 * href="https://leetcode.com/problems/delete-characters-to-make-fancy-string/?envType=daily-question&envId=2025-07-21">leetcode
 * 1957</a>
 */
public class FancyString {

  public String makeFancyString(String s) {
    if (s == null || s.length() <= 1) {
      return s;
    }

    StringBuilder sb = new StringBuilder();
    Character lastChar = null;
    int count = 0;

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (lastChar == null || lastChar != c) {
        lastChar = c;
        count = 1;
      } else {
        if (count < 2) {
          count++;
        } else {
          continue;
        }
      }
      sb.append(c);
    }
    return sb.toString();
  }
}
