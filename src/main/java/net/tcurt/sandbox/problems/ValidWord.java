package net.tcurt.sandbox.problems;

import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;

/**
 * From <a
 * href="https://leetcode.com/problems/valid-word/description/?envType=daily-question&envId=2025-07-15">
 * leetcode problem 3136</a>.
 */
@Slf4j
public class ValidWord {
  private static final Pattern VOWEL_PATTERN = Pattern.compile("[aeiou]");
  private static final Pattern CONSANANT_PATTERN = Pattern.compile("[a-z&&[^aeiou]]");
  private static final Pattern INVALID_CHAR_PATTERN = Pattern.compile("[^a-z0-9]");

  public boolean isValid(String word) {
    if (word.length() < 3) {
      return false;
    }

    String lowerWord = word.toLowerCase();
    boolean vowel = VOWEL_PATTERN.matcher(lowerWord).find();
    boolean consanant = CONSANANT_PATTERN.matcher(lowerWord).find();
    boolean invalid = INVALID_CHAR_PATTERN.matcher(lowerWord).find();

    log.info("'{}' vowel={} consanant={} invalid={}", word, vowel, consanant, invalid);
    return vowel && consanant && !invalid;
  }
}
