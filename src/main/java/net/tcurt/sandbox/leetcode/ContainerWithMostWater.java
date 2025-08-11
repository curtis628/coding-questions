package net.tcurt.sandbox.leetcode;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** From <a href="https://leetcode.com/problems/container-with-most-water">Leetcode 11</a>. */
@RequiredArgsConstructor
@Slf4j
public class ContainerWithMostWater {
  enum Method {
    BRUTE_FORCE,
    TWO_POINTERS
  }

  private final Method method;

  public int maxArea(int[] height) {
    int result = (method == Method.BRUTE_FORCE) ? bruteForce(height) : twoPointers(height);
    return result;
  }

  private int twoPointers(int[] height) {
    int left = 0, right = height.length - 1;
    int maxArea = 0;

    while (left < right) {
      int lHeight = height[left];
      int rHeight = height[right];
      int minHeight = Math.min(lHeight, rHeight);

      maxArea = Math.max(maxArea, minHeight * (right - left));
      if (lHeight == minHeight) {
        left++;
      } else {
        right--;
      }
    }

    return maxArea;
  }

  private int bruteForce(int[] height) {
    int[] results = new int[height.length];

    for (int i = 0; i < height.length; i++) {
      int currentMaxContainer = 0;

      for (int j = i + 1; j < height.length; j++) {
        int tmpHeight = Math.min(height[i], height[j]);
        int tmpContainer = (j - i) * tmpHeight;
        currentMaxContainer = Math.max(currentMaxContainer, tmpContainer);
      }
      results[i] = currentMaxContainer;
    }

    return Arrays.stream(results).max().getAsInt();
  }
}
