package net.tcurt.sandbox.problems;

/**
 * From <a href="https://leetcode.com/problems/delete-characters-to-make-fancy-string">Leetcode
 * 1957</a>
 */
public class FancyString {
  private static final int MAX_CONSECUTIVE_CHARS = 2;

  public String makeFancyString(String s) {
    StringBuilder sb = new StringBuilder();

    char recentChar = s.charAt(0);
    int recentCharCount = 1;
    sb.append(recentChar);

    for (int i = 1; i < s.length(); i++) {
      char c = s.charAt(i);

      if (c == recentChar && recentCharCount < MAX_CONSECUTIVE_CHARS) {
        recentCharCount++;
        sb.append(c);
      } else if (c == recentChar && recentCharCount == MAX_CONSECUTIVE_CHARS) {
        System.out.printf(
            "Removing '%s' as recentChar=%s recentCharCount=%d\n", c, recentChar, recentCharCount);
        // don't append to sb
      } else {
        recentChar = c;
        recentCharCount = 1;
        sb.append(c);
      }
    }

    return sb.toString();
  }
}
