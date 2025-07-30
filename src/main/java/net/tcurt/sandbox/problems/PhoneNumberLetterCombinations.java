package net.tcurt.sandbox.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * From <a href="https://leetcode.com/problems/letter-combinations-of-a-phone-number">Leetcode
 * 17</a>
 */
@Slf4j
public class PhoneNumberLetterCombinations {
  public static final Map<Character, String> NUMBER_LOOKUP_MAP =
      Map.of(
          '2', "abc",
          '3', "def",
          '4', "ghi",
          '5', "jkl",
          '6', "mno",
          '7', "pqrs",
          '8', "tuv",
          '9', "wxyz");

  public List<String> letterCombinations(String digits) {
    List<String> results = new ArrayList<>();
    backtrack(0, digits, new StringBuilder(), results);
    return results;
  }

  private void backtrack(int digitNdx, String digits, StringBuilder sb, List<String> combinations) {
    // no more options left
    if (digitNdx == digits.length()) {
      if (!sb.isEmpty()) {
        combinations.add(sb.toString());
      }
      return;
    }

    char digit = digits.charAt(digitNdx);
    for (char charPossibility : NUMBER_LOOKUP_MAP.get(digit).toCharArray()) {
      sb.append(charPossibility);
      backtrack(digitNdx + 1, digits, sb, combinations);
      sb.deleteCharAt(sb.length() - 1);
    }
  }
}
