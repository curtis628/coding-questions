package net.tcurt.sandbox.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import lombok.extern.slf4j.Slf4j;

/** Given a sequence of text, return the top {@code k} most common words. */
@Slf4j
public class TopKWords {
  private Map<String, Integer> freqMap = new HashMap<>();

  private void parseWordsFromLine(String line) {
    String[] words =
        line.toLowerCase() // words are case-insensitive
            .replaceAll("[^a-z0-9\\s]", "") // excluding punctuation
            .split("\\s+"); // return parsed words
    for (String word : words) {
      int existingCount = freqMap.getOrDefault(word, 0);
      freqMap.put(word, existingCount + 1);
    }
  }

  private List<String> processTopWords(int topK) {
    // Step 2: PriorityQueue (Max Heap instead of default min heap via reversed comparator)
    PriorityQueue<Map.Entry<String, Integer>> maxHeap =
        new PriorityQueue<>(
            Comparator.<Map.Entry<String, Integer>>comparingInt(Map.Entry::getValue)
                .reversed()
                .thenComparing(Map.Entry::getKey));
    maxHeap.addAll(freqMap.entrySet()); // we add everything, but will only poll topK

    // Step 3: Extract top K entries directly from the heap
    List<String> result = new ArrayList<>(topK);
    for (int i = 0; i < topK; i++) {
      result.add(maxHeap.poll().getKey());
    }

    freqMap.clear(); // reset map after so this instance can be reused
    return result;
  }

  public List<String> topKWordsFromFile(Path filePath, int topK) throws IOException {
    try (BufferedReader reader = Files.newBufferedReader(filePath)) {
      String line;
      while ((line = reader.readLine()) != null) {
        parseWordsFromLine(line);
      }
    }
    return processTopWords(topK);
  }

  public List<String> topWords(String text, int topK) {
    parseWordsFromLine(text);
    return processTopWords(topK);
  }
}
