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
    List<String> result = new ArrayList<>();
    addLetters(result, new StringBuilder(), digits, 0);
    return result;
  }

  private void addLetters(List<String> result, StringBuilder sb, String digits, int ndx) {
    if (ndx == digits.length()) return;

    char digit = digits.charAt(ndx);
    String possibilities = NUMBER_LOOKUP_MAP.get(digit);
    System.out.printf("Adding poss=%s for digit=%s\n", possibilities, digit);
    for (char ch : possibilities.toCharArray()) {
      sb.append(ch);
      addLetters(result, sb, digits, ndx + 1);

      // backtrack
      System.out.println("Finished adding letters for DFS: " + sb);
      if (sb.length() == digits.length()) {
        result.add(sb.toString());
      }
      sb.deleteCharAt(sb.length() - 1);
    }
  }
}
