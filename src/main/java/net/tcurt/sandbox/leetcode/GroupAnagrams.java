package net.tcurt.sandbox.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * From an mock interview with ChatGPT. Also from <a
 * href="https://leetcode.com/problems/group-anagrams/">Leetcode 49</a>
 *
 * <p>You are given an array of strings {@code strs}. Group the strings that are anagrams of each
 * other. You can return the result in any order, but each group should be a list of strings that
 * are anagrams.
 *
 * <p>Constraints:
 *
 * <ul>
 *   <li>All input strings are lowercase letters.
 *   <li>You can assume input is non-null.
 *   <li>{@code 1 <= strs.length <= 10⁴}
 *   <li>{@code 0 <= strs[i].length <= 100}
 * </ul>
 *
 * Example input:
 *
 * <pre>["eat","tea","tan","ate","nat","bat"]</pre>
 *
 * Example output:
 *
 * <pre>
 * [
 *   ["eat","tea","ate"],
 *   ["tan","nat"],
 *   ["bat"]
 * ]
 * </pre>
 */
public class GroupAnagrams {

  public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> sortedWordToOriginalWordsMap = new HashMap<>();

    for (String word : strs) {
      char[] charArray = word.toCharArray();
      Arrays.sort(charArray);
      String sortedWord = String.valueOf(charArray);

      sortedWordToOriginalWordsMap
          .computeIfAbsent(sortedWord, k -> new ArrayList<>()) // create new list if not in map yet
          .add(word); // add word to the map
    }

    return sortedWordToOriginalWordsMap.values().stream().toList();
  }
}
