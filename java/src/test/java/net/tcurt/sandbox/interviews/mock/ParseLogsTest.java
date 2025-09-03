package net.tcurt.sandbox.interviews.mock;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ParseLogsTest {
  private ParseLogs underTest = new ParseLogs();

  @Test
  void case1() {
    String[] input =
        List.of(
                "dig1 8 1 5 1",
                "let2 art can",
                "let1 art can",
                "dig2 3 6",
                "let2 own kit dig",
                "let3 art zero")
            .toArray(new String[0]);
    String[] expected =
        List.of(
                "let1 art can",
                "let2 art can",
                "let3 art zero",
                "let2 own kit dig",
                "dig1 8 1 5 1",
                "dig2 3 6")
            .toArray(new String[0]);
    assertThat(underTest.reorderLogFiles(input)).isEqualTo(expected);
  }
}
