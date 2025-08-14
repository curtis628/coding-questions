package net.tcurt.sandbox.leetcode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * From <a href="https://leetcode.com/problems/largest-3-same-digit-number-in-string">Leetcode
 * 2264</a>.
 */
@RequiredArgsConstructor
@Slf4j
public class Largest3SameDigitInString {

  private final int goodCount;

  public String largestGoodInteger(String num) {
    int counter = 0;
    char lastDigit = '\0';
    String goodNumber = "";

    for (char c : num.toCharArray()) {
      if (c != lastDigit) {
        lastDigit = c;
        counter = 1;
      } else {
        if (++counter == goodCount) {
          String option = String.valueOf(lastDigit).repeat(goodCount);
          goodNumber =
              (goodNumber.isEmpty() || option.compareTo(goodNumber) > 0) ? option : goodNumber;

          counter = 0;
          lastDigit = '\0';
        }
      }
    }

    return goodNumber;
  }
}
