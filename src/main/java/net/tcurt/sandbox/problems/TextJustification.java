package net.tcurt.sandbox.problems;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * From <a href="https://leetcode.com/problems/text-justification/">Leetcode 68</a>
 *
 * <p>Given an array of strings words and a width maxWidth, format the text such that each line has
 * exactly maxWidth characters and is fully (left and right) justified.
 *
 * <p>You should pack your words in a greedy approach; that is, pack as many words as you can in
 * each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 *
 * <p>Extra spaces between words should be distributed as evenly as possible. If the number of
 * spaces on a line does not divide evenly between words, the empty slots on the left will be
 * assigned more spaces than the slots on the right.
 *
 * <p>For the last line of text, it should be left-justified, and no extra space is inserted between
 * words.
 *
 * <p>Note:
 *
 * <ul>
 *   <li>A word is defined as a character sequence consisting of non-space characters only.
 *   <li>Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 *   <li>The input array words contains at least one word.
 * </ul>
 */
@Slf4j
public class TextJustification {

  List<String> getWords(int ndx, String[] words, int maxWidth) {
    List<String> lineWords = new ArrayList<>();
    int remainingWidth = maxWidth;

    while (ndx < words.length && remainingWidth > 0) {
      String word = words[ndx];
      int spaceWidth = lineWords.isEmpty() ? 0 : 1;
      if (word.length() + spaceWidth <= remainingWidth) {
        lineWords.add(word);
        remainingWidth -= (word.length() + spaceWidth);
      } else {
        remainingWidth = 0;
      }
      ndx++;
    }

    return lineWords;
  }

  String formatLine(List<String> lineWords, int ndx, String[] words, int maxWidth) {
    StringBuilder sb = new StringBuilder();
    int wordsLength = lineWords.stream().mapToInt(String::length).sum();
    int spacesRemaining = maxWidth - wordsLength;
    int minSpaces = lineWords.size() - 1;
    int avgSpaces = minSpaces > 0 ? spacesRemaining / minSpaces : spacesRemaining;

    // left-justify
    if (lineWords.size() == 1 || ndx == words.length) {
      return String.join(" ", lineWords) + " ".repeat(spacesRemaining - minSpaces);
    }

    // full justify
    for (String word : lineWords) {
      sb.append(word);

      if (spacesRemaining > 0) {
        int spaces = avgSpaces * minSpaces == spacesRemaining ? avgSpaces : avgSpaces + 1; // greedy
        sb.append(" ".repeat(spaces));
        spacesRemaining -= spaces;
        minSpaces--;
      }
    }
    return sb.toString();
  }

  public List<String> fullJustify(String[] words, int maxWidth) {
    List<String> result = new ArrayList<>();

    int ndx = 0;
    while (ndx < words.length) {
      List<String> lineWords = getWords(ndx, words, maxWidth);
      ndx += lineWords.size();
      result.add(formatLine(lineWords, ndx, words, maxWidth));
    }

    return result;
  }
}
