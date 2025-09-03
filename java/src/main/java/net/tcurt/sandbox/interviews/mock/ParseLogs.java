package net.tcurt.sandbox.interviews.mock;

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
    List<String> letterLogs = new ArrayList<>();
    List<String> digitLogs = new ArrayList<>();

    for (String log : logs) {
      int idSeparatorNdx = log.indexOf(" ");
      String content = log.substring(idSeparatorNdx + 1);

      if (Character.isDigit(content.charAt(0))) {
        digitLogs.add(log);
      } else {
        letterLogs.add(log);
      }
    }

    letterLogs.sort(
        (a, b) -> {
          int aSeparatorNdx = a.indexOf(" ");
          String aId = a.substring(0, aSeparatorNdx);
          String aContent = a.substring(aSeparatorNdx + 1);

          int bSeparatorNdx = b.indexOf(" ");
          String bId = b.substring(0, bSeparatorNdx);
          String bContent = b.substring(bSeparatorNdx + 1);

          int cmp = aContent.compareTo(bContent);
          if (cmp == 0) {
            cmp = aId.compareTo(bId);
          }
          return cmp;
        });

    List<String> result = new ArrayList<>(letterLogs);
    result.addAll(digitLogs);
    return result.toArray(new String[0]);
  }
}
