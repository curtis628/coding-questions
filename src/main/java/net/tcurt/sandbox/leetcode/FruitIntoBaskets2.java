package net.tcurt.sandbox.leetcode;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

/** From <a href="https://leetcode.com/problems/fruit-into-baskets">Leetcode 904</a>. */
@Slf4j
public class FruitIntoBaskets2 {

  public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
    for (int f = 0; f < fruits.length; f++) {
      for (int b = 0; b < fruits.length; b++) {
        if (fruits[f] <= baskets[b]) {
          baskets[b] = 0;
          fruits[f] = 0;
          break;
        }
      }
    }
    return (int) Arrays.stream(fruits).filter(f -> f > 0).count();
  }
}
