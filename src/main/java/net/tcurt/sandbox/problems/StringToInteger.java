package net.tcurt.sandbox.problems;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;

/** From <a href="https://leetcode.com/problems/string-to-integer-atoi/">Leetcode 8</a>. */
@Slf4j
public class StringToInteger {
  /**
   *
   *
   * <ul>
   *   <li>ignore beginning whitespace
   *   <li>capture optional sign into {@code sign} named group
   *   <li>ignore any preceeding zeros
   *   <li>capture remaining numbers into {@code num} named group
   * </ul>
   */
  private static final Pattern PATTERN = Pattern.compile("\\s*(?<sign>[+-]?)0*(?<num>\\d+).*");

  private static final String INT_MAX = Integer.toString(Integer.MAX_VALUE);
  private static final String INT_MIN = Integer.toString(Integer.MIN_VALUE);

  public int myAtoi(String s) {
    Matcher m = PATTERN.matcher(s);
    int result = 0;

    if (m.matches()) {
      String sign = m.group("sign");
      String num = m.group("num");

      // overflow
      if (num.length() > INT_MAX.length()
          || (num.length() == INT_MAX.length() && num.compareTo(INT_MAX) > 0)) {
        num = INT_MAX;

        if ("-".equals(sign)) {
          num = INT_MIN;
        }
      }

      int parsedNum = Integer.parseInt(num);
      result = "-".equals(sign) ? -1 * parsedNum : parsedNum;
    }

    return result;
  }
}
