package net.tcurt.sandbox.problems;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class GroupAnagramsTest {
  private GroupAnagrams groupAnagrams = new GroupAnagrams();

  @Test
  void case1() {
    String[] input = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};
    assertThat(groupAnagrams.groupAnagrams(input))
        .containsExactlyInAnyOrder(
            List.of("eat", "tea", "ate"), // sorted: aet
            List.of("tan", "nat"), // sorted: atn
            List.of("bat")); // sorted: abt
  }

  @Test
  void case2() {
    String[] input = new String[] {"aaabb", "ababa", "bbaa", "aabb", "ab", "ba"};
    assertThat(groupAnagrams.groupAnagrams(input))
        .containsExactlyInAnyOrder(
            List.of("aaabb", "ababa"), // sorted: aaabb
            List.of("bbaa", "aabb"), // sorted: aabb
            List.of("ab", "ba")); // sorted: ab
  }
}
