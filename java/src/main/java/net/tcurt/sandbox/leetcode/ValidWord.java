package net.tcurt.sandbox.leetcode;

import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;

/** From <a href="https://leetcode.com/problems/valid-word">Leetcode 3136</a>. */
@Slf4j
public class ValidWord {
  private static final Pattern VOWEL_PATTERN = Pattern.compile("[aeiou]");
  private static final Pattern CONSANANT_PATTERN = Pattern.compile("[a-z&&[^aeiou]]");
  private static final Pattern VALID_PATTERN = Pattern.compile("[a-z0-9]+");

  public boolean isValid(String word) {
    if (word.length() < 3) {
      return false;
    }

    String lowerWord = word.toLowerCase();
    boolean hasVowel = VOWEL_PATTERN.matcher(lowerWord).find();
    boolean hasConsanant = CONSANANT_PATTERN.matcher(lowerWord).find();
    boolean hasOnlyValidChars = VALID_PATTERN.matcher(lowerWord).matches();
    // NOTE: find() scans for next subsequence match, while matches() expects entire input to match

    return hasVowel && hasConsanant && hasOnlyValidChars;
  }
}
