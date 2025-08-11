package net.tcurt.sandbox.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** From <a href="https://leetcode.com/problems/fruit-into-baskets">Leetcode 904</a>. */
@RequiredArgsConstructor
@Slf4j
public class FruitIntoBaskets {
  enum Method {
    BRUTE_FORCE,
    SLIDING_WINDOW
  }

  private final Method method;

  public int totalFruit(int[] fruits) {
    int totalFruit = (method == Method.BRUTE_FORCE) ? bruteForce(fruits) : slidingWindow(fruits);
    return totalFruit;
  }

  private int slidingWindow(int[] fruits) {
    Map<Integer, Integer> fruitCountMap = new HashMap<>();
    int left = 0, right = 0;

    // Add fruit from right side (right) of the window.
    for (right = 0; right < fruits.length; right++) {
      int fruit = fruits[right];
      addFruit(fruitCountMap, fruit);

      // If the current window has >2 fruits, remove one from the left side.
      // (only one because we know the window must be at least size right - left
      if (fruitCountMap.size() > 2) {
        int leftFruit = fruits[left];
        removeFruit(fruitCountMap, leftFruit);
        left++;
      }
    }
    // right and left store longest valid subarray we encountered
    return right - left;
  }

  private static void addFruit(Map<Integer, Integer> fruitCountMap, int fruit) {
    int existingCount = fruitCountMap.getOrDefault(fruit, 0);
    fruitCountMap.put(fruit, existingCount + 1);
  }

  private static void removeFruit(Map<Integer, Integer> fruitCountMap, int fruit) {
    int existingCount = fruitCountMap.get(fruit);
    if (existingCount == 1) {
      fruitCountMap.remove(fruit);
    } else {
      fruitCountMap.put(fruit, existingCount - 1);
    }
  }

  private int bruteForce(int[] fruits) {
    int maxPicked = Integer.MIN_VALUE;
    Set<Integer> baskets = new HashSet<>(2);

    for (int left = 0; left < fruits.length; left++) {
      baskets.clear();

      int right = left;
      while (right < fruits.length) {
        int fruit = fruits[right];

        if (baskets.size() == 2 && !baskets.contains(fruit)) {
          break;
        }
        baskets.add(fruit);
        right++;
      }
      maxPicked = Math.max(maxPicked, right - left);
    }

    return maxPicked;
  }
}
