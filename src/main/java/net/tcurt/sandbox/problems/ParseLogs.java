package net.tcurt.sandbox.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * From a ChatGPT mock interview.
 *
 * <p>You're given a list of logs, where each log is a string in the format "id content". Logs are
 * either letter-logs (content is letters) or digit-logs (content is numbers).
 *
 * <p>Rules to reorder:
 *
 * <ul>
 *   <li>Letter-logs come before digit-logs.
 *   <li>Letter-logs are sorted lexicographically by content; if tied, by ID.
 *   <li>Digit-logs remain in original order.
 * </ul>
 *
 * Example input:
 *
 * <pre>
 * [
 *   "dig1 8 1 5 1",
 *   "let1 art can",
 *   "dig2 3 6",
 *   "let2 own kit dig",
 *   "let3 art zero"
 * ]
 * </pre>
 *
 * Example output:
 *
 * <pre>
 * [
 *   "let1 art can",
 *   "let3 art zero",
 *   "let2 own kit dig",
 *   "dig1 8 1 5 1",
 *   "dig2 3 6"
 * ]
 * </pre>
 */
public class ParseLogs {

  public String[] reorderLogFiles(String[] logs) {
    // as we find digit logs, add them where we can get in original order
    List<String> letterLogs = new ArrayList<>();
    List<String> digitLogs = new ArrayList<>();

    for (String log : logs) {
      int splitNdx = log.indexOf(" ");
      String content = log.substring(splitNdx + 1);

      if (Character.isDigit(content.charAt(0))) {
        digitLogs.add(log);
      } else {
        letterLogs.add(log);
      }
    }

    letterLogs.sort(
        (log1, log2) -> {
          int i1 = log1.indexOf(" ");
          int i2 = log2.indexOf(" ");

          String id1 = log1.substring(0, i1);
          String content1 = log1.substring(i1 + 1);

          String id2 = log2.substring(0, i2);
          String content2 = log2.substring(i2 + 1);

          int cmp = content1.compareTo(content2);
          if (cmp == 0) {
            cmp = id1.compareTo(id2);
          }
          return cmp;
        });

    List<String> result = new ArrayList<>(letterLogs);
    result.addAll(digitLogs);
    return result.toArray(new String[0]);
  }
}
