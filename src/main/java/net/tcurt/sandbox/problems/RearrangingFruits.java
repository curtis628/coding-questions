package net.tcurt.sandbox.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/** * From <a href="https://leetcode.com/problems/rearranging-fruits">Leetcode 2561</a>. */
@Slf4j
public class RearrangingFruits {

  public long minCost(int[] basket1, int[] basket2) {
    Map<Integer, Integer> totalCount = new HashMap<>();
    Map<Integer, Integer> countB1 = new HashMap<>();
    int minFruit = Integer.MAX_VALUE;

    for (int i = 0; i < basket1.length; i++) {
      int f1 = basket1[i];
      int f2 = basket2[i];

      totalCount.put(f1, totalCount.getOrDefault(f1, 0) + 1);
      totalCount.put(f2, totalCount.getOrDefault(f2, 0) + 1);
      countB1.put(f1, countB1.getOrDefault(f1, 0) + 1);

      minFruit = Math.min(minFruit, Math.min(f1, f2));
    }

    // Check if any fruit has odd total count (impossible)
    for (int count : totalCount.values()) {
      if (count % 2 != 0) return -1;
    }

    List<Integer> excess = new ArrayList<>();
    for (int fruit : totalCount.keySet()) {
      int total = totalCount.get(fruit);
      int desired = total / 2;
      int b1Has = countB1.getOrDefault(fruit, 0);

      if (b1Has > desired) {
        for (int i = 0; i < b1Has - desired; i++) {
          excess.add(fruit);
        }
      }

      int b2Has = total - b1Has;
      if (b2Has > desired) {
        for (int i = 0; i < b2Has - desired; i++) {
          excess.add(fruit);
        }
      }
    }

    // Only half of excess will be from one side
    Collections.sort(excess);
    long cost = 0;
    int swaps = excess.size() / 2;
    for (int i = 0; i < swaps; i++) {
      cost += Math.min(excess.get(i), 2 * minFruit);
    }

    return cost;
  }
}
