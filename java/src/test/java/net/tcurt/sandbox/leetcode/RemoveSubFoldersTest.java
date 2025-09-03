package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.Test;

public class RemoveSubFoldersTest {

  private RemoveSubFolders underTest = new RemoveSubFolders();

  @Test
  void case1() {
    String[] input = new String[] {"/a", "/a/b", "/c/d", "/c/d/e", "/c/f"};
    List<String> actual = underTest.removeSubfolders(input);
    assertThat(actual).containsExactlyInAnyOrder("/a", "/c/d", "/c/f");
  }

  @Test
  void case2() {
    String[] input = new String[] {"/a", "/a/b/c", "/a/b/d"};
    List<String> actual = underTest.removeSubfolders(input);
    assertThat(actual).containsExactlyInAnyOrder("/a");
  }

  @Test
  void case3() {
    String[] input = new String[] {"/a/b/c", "/a/b/ca", "/a/b/d"};
    List<String> actual = underTest.removeSubfolders(input);
    assertThat(actual).containsExactlyInAnyOrder("/a/b/c", "/a/b/ca", "/a/b/d");
  }

  @Test
  void giant1() throws Exception {
    URL resource = getClass().getClassLoader().getResource("RemoveSubFoldersTest/giant1.txt");
    assertThat(resource).isNotNull();
    Path path = Paths.get(resource.toURI());
    List<String> inputList = Files.readAllLines(path);
    String[] input = inputList.toArray(new String[0]);
    List<String> actual = underTest.removeSubfolders(input);
    assertThat(actual).containsExactlyInAnyOrder("/a");
  }
}
