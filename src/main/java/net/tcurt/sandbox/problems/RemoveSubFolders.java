package net.tcurt.sandbox.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * From <a
 * href="https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/?envType=daily-question&envId=2025-07-21">leetcode
 * 1233</a>
 */
@Slf4j
public class RemoveSubFolders {

  public List<String> removeSubfolders(String[] folder) {
    Arrays.sort(folder);
    List<String> result = new ArrayList<>();

    String lastFolder = null;
    for (String f : folder) {
      if (lastFolder == null || !f.startsWith(lastFolder)) {
        lastFolder = f + "/";
        result.add(f);
      }
    }
    return result;
  }
}
