package net.tcurt.sandbox.problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

/**
 * From <a
 * href="https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/?envType=daily-question&envId=2025-07-21">leetcode
 * 1233</a>
 */
@Slf4j
public class RemoveSubFolders {

  public static class Folder {
    public String name;

    public Set<Folder> subfolders;

    public Folder(String name) {
      this.name = name;
      this.subfolders = new HashSet<>();
    }

    public Folder getSubfolder(String subfolderName) {
      return this.subfolders.stream()
          .filter(s -> subfolderName.equals(s.name))
          .findFirst()
          .orElse(null);
    }

    public Folder getOrAddSubfolder(String subfolderName) {
      Folder sub = getSubfolder(subfolderName);
      if (sub == null) {
        sub = new Folder(subfolderName);
        this.subfolders.add(sub);
      }
      return sub;
    }
  }

  public List<String> removeSubfolders(String[] folder) {
    List<String> folders = Arrays.stream(folder).toList();
    Folder root = new Folder("$");

    // setup data structure
    for (String f : folders) {
      StringBuilder parentPath = new StringBuilder();

      Folder parent = root;
      log.debug("Processing folder: {}", f);

      String[] path = f.substring(1).split("/");
      for (String pathPart : path) {
        if (parent == null) {
          break;
        }

        if (!parent.name.equals("$")) {
          parentPath.append("/").append(parent.name);
        }

        if (folders.contains(parentPath.toString())) {
          log.debug(
              "Don't add {} because it's root folder {} is already listed", pathPart, parentPath);
          break;
        } else {
          Folder sub = parent.getOrAddSubfolder(pathPart);
          log.debug("  parent={} adds subfolder={}", parent, pathPart);
          parent = sub;
        }
      }
    }

    return Arrays.stream(folder)
        .filter(
            f -> {
              Folder parent = root;
              String[] path = f.substring(1).split("/");
              for (String pathPart : path) {
                if (parent == null) {
                  break;
                }
                parent = parent.getSubfolder(pathPart);
              }
              return parent != null;
            })
        .collect(Collectors.toList());
  }
}
