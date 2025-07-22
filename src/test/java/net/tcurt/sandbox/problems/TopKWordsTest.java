package net.tcurt.sandbox.problems;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TopKWordsTest {
  private TopKWords underTest = new TopKWords();

  @Test
  void simple() {
    String input =
        """
        Hello there! There is a simple test here that spans multiple lines.
        There exists one answer. Is it going to work? I hope that it does.
        Time will tell if there is a solution that works.
        """;
    List<String> actual = underTest.topWords(input, 3);
    assertThat(actual).containsExactly("there", "is", "that");
  }

  @Test
  void simpleFromFile() throws Exception {
    URL resource = getClass().getClassLoader().getResource("TopKWordsTest/simple.txt");
    assertThat(resource).isNotNull();
    Path path = Paths.get(resource.toURI());
    List<String> actual = underTest.topKWordsFromFile(path, 3);
    assertThat(actual).containsExactly("there", "is", "that");
  }
}
