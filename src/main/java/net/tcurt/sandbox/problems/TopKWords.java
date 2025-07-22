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
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

/** Given a sequence of text, return the top {@code k} most common words. */
@Slf4j
public class TopKWords {

  public List<String> topKWordsFromFile(Path filePath, int topK) throws IOException {
    Map<String, Integer> freqMap = new HashMap<>();

    try (BufferedReader reader = Files.newBufferedReader(filePath)) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] words = line.toLowerCase().replaceAll("[^a-zA-Z\\s]", "").split("\\s+");

        for (String word : words) {
          if (!word.isEmpty()) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
          }
        }
      }
    }

    // Step 2: PriorityQueue (Max Heap instead of default min heap via reversed comparator)
    PriorityQueue<Map.Entry<String, Integer>> heap =
        new PriorityQueue<>(
            Comparator.<Map.Entry<String, Integer>>comparingInt(Map.Entry::getValue)
                .reversed()
                .thenComparing(Map.Entry::getKey));

    heap.addAll(freqMap.entrySet()); // we add everything, but will only poll topK

    // Step 3: Extract top K entries directly from the heap
    List<String> result = new ArrayList<>();
    for (int i = 0; i < topK && !heap.isEmpty(); i++) {
      result.add(heap.poll().getKey());
    }
    return result;
  }

  public List<String> topWords(String text, int topK) {
    if (text == null || text.isEmpty() || topK <= 0) {
      return List.of();
    }

    // Normalize and split words
    String[] words =
        text.toLowerCase()
            .replaceAll("[^a-zA-Z\\s]", "") // Remove punctuation
            .split("\\s+"); // Split on whitespace

    // Count word frequencies
    Map<String, Integer> freqMap = new HashMap<>();
    for (String word : words) {
      if (!word.isEmpty()) {
        freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
      }
    }

    // Get top K words sorted by frequency (descending), then alphabetically
    return freqMap.entrySet().stream()
        .sorted(
            (a, b) -> {
              int cmp = b.getValue().compareTo(a.getValue());
              return cmp != 0 ? cmp : a.getKey().compareTo(b.getKey());
            })
        .limit(topK)
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
  }

  /*
    // Could use Scanner also
    try (Scanner scanner = new Scanner(new File("file.txt"))) {
      scanner.useDelimiter("[^a-zA-Z]+"); // split on non-letters (ignore punctuation/whitespace)
      Map<String, Integer> freq = new HashMap<>();

      while (scanner.hasNext()) {
          String word = scanner.next().toLowerCase();
          if (!word.isEmpty()) {
              freq.put(word, freq.getOrDefault(word, 0) + 1);
          }
      }

      // Get top K as usual...
  }
     */
}
