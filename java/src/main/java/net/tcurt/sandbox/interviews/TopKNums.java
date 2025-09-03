package net.tcurt.sandbox.interviews;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.tcurt.sandbox.Method;

/**
 * From mock ChatGPT interview.
 *
 * <p>You’re given an array of integers {@code nums} and an integer {@code k}. Return the <b>k</b>
 * most frequent elements in {@code nums}.
 *
 * <p>The answer can be in any order.
 *
 * <pre>
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 *
 * Input: nums = [4,4,4,4,5,5,6], k = 1
 * Output: [4]
 * </pre>
 */
@RequiredArgsConstructor
@Slf4j
public class TopKNums {

  private final Method method;

  public int[] topKnums(int[] nums, int k) {
    int[] result = null;
    switch (method) {
      case HEAP -> result = minHeap(nums, k);
      case OPTIMIZED -> result = bucketSort(nums, k);
    }
    return result;
  }

  private Map<Integer, Integer> numToCountMap(int[] nums) {
    // gather int counts
    Map<Integer, Integer> numToCountMap = new HashMap<>();
    for (int num : nums) {
      numToCountMap.compute(num, (n, count) -> count == null ? 1 : count + 1);
    }
    return numToCountMap;
  }

  private int[] bucketSort(int[] nums, int k) {
    Map<Integer, Integer> numToCountMap = numToCountMap(nums);

    // buckets: index=count, values=collection of numbers matching count
    @SuppressWarnings("unchecked")
    List<Integer>[] buckets = new ArrayList[nums.length + 1];

    for (Map.Entry<Integer, Integer> entry : numToCountMap.entrySet()) {
      int count = entry.getValue();
      if (buckets[count] == null) {
        buckets[count] = new ArrayList<>();
      }
      buckets[count].add(entry.getKey());
    }

    List<Integer> result = new ArrayList<>();
    for (int i = buckets.length - 1; i > 0 && result.size() < k; i--) {
      List<Integer> bucket = buckets[i];
      if (bucket != null) {
        for (int r = 0; r < bucket.size() && result.size() < k; r++) {
          result.add(bucket.get(r));
        }
      }
    }

    return result.stream().mapToInt(Integer::intValue).toArray();
  }

  private int[] minHeap(int[] nums, int k) {
    // gather int counts
    Map<Integer, Integer> numToCountMap = numToCountMap(nums);

    // insert into min heap
    PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
        new PriorityQueue<>(
            Comparator.<Map.Entry<Integer, Integer>>comparingInt(Map.Entry::getValue));
    for (Map.Entry<Integer, Integer> entry : numToCountMap.entrySet()) {
      if (minHeap.size() < k) {
        minHeap.offer(entry);
      } else if (entry.getValue() > minHeap.peek().getValue()) {
        minHeap.poll(); // remove smallest entry and re-add this larger one
        minHeap.offer(entry);
      }
    }

    // pop and return k elements
    int[] result = new int[k];
    for (int i = 0; i < k; i++) {
      Map.Entry<Integer, Integer> entry = minHeap.poll();
      result[i] = entry.getKey();
    }

    return result;
  }
}
