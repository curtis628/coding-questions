package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class TextJusticationTest {
  private TextJustification underTest = new TextJustification();

  @Test
  void oneline_width10() {
    String[] input = List.of("single").toArray(new String[0]);
    List<String> actual = underTest.fullJustify(input, 10);
    assertThat(actual).containsExactly("single    ");
  }

  @Test
  void smallInput_width16() {
    String[] input =
        List.of("This", "is", "an", "example", "of", "text", "justification.")
            .toArray(new String[0]);
    List<String> actual = underTest.fullJustify(input, 16);
    assertThat(actual).containsExactly("This    is    an", "example  of text", "justification.  ");
  }

  @Test
  void smallInput2_width16() {
    String[] input =
        List.of("What", "must", "be", "acknowledgment", "shall", "be").toArray(new String[0]);
    List<String> actual = underTest.fullJustify(input, 16);
    assertThat(actual).containsExactly("What   must   be", "acknowledgment  ", "shall be        ");
  }

  @Test
  void smallInput1_width20() {
    String[] input =
        List.of(
                "Science",
                "is",
                "what",
                "we",
                "understand",
                "well",
                "enough",
                "to",
                "explain",
                "to",
                "a",
                "computer.",
                "Art",
                "is",
                "everything",
                "else",
                "we",
                "do")
            .toArray(new String[0]);
    List<String> actual = underTest.fullJustify(input, 20);
    assertThat(actual)
        .containsExactly(
            "Science  is  what we",
            "understand      well",
            "enough to explain to",
            "a  computer.  Art is",
            "everything  else  we",
            "do                  ");
  }
}
