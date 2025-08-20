package net.tcurt.sandbox.interviews.mock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.tcurt.sandbox.Method;

/**
 * Return number of ways an integer can be represented as a sum of two or more consecutive positive
 * integers.
 *
 * <p>From practice coding challenge prep.
 */
@RequiredArgsConstructor
@Slf4j
public class ConsecutiveSum {
  private static final long HIGHEST = 200000;

  private final Method method;

  public int consecutive(long num) {
    if (num == 1) return 1;
    int result = -1;

    switch (method) {
      case SLIDING_WINDOW -> result = slidingWindow(num, 1, 1);
      case BINARY_SEARCH -> result = binarySearch(num);
    }

    return result;
  }

  private int binarySearch(long num) {
    System.out.printf("binarySearch: " + num);
    long l = 1, r = Math.min(num, HIGHEST), mid = -1;
    long sum = 0;

    // binary search to find initial sum
    while (l < r) {
      mid = l + (r - l) / 2;
      sum = mid * (mid + 1) / 2;

      System.out.printf("l=%d r=%d [mid=%d sum=%d] (num=%d) -> ", l, r, mid, sum, num);
      if (sum < num) {
        l = mid + 1;
        System.out.printf("LESS [diff=%d]\n", Math.abs(num - sum));
      } else if (sum > num) {
        r = mid - 1;
        System.out.printf("MORE [diff=%d]\n", Math.abs(num - sum));
      } else {
        break;
      }
    }

    return slidingWindow(num, 1, mid - 1);
  }

  private int slidingWindow(long num, long l, long r) {
    long sum = (r - 1) * r / 2;
    int numWays = 0;
    System.out.printf("num=%d: sum=%d l=%d r=%d\n", num, sum, l, r);

    // use sliding window approach
    while (l <= (num / 2)) {
//      System.out.printf("sum=%d l=%d r=%d\n", sum, l, r);
      if (sum + r < num) {
        sum += r;
        r++;
//        System.out.printf("  Expand window: [l=%d r=%d]\n", l, r);
      } else if (sum + r == num) {
        System.out.printf("  Found match: l=%d r=%d\n", l, r);
        numWays++;
//        if (r - 1 == l) {
//          break;
//        }
//        sum = l = r - 1;
        sum -= l;
        l++;
//        System.out.printf("  new window started: l=%d r=%d\n", l, r);
      } else {
        sum -= l;
        l++;
//        System.out.printf("  Contract window: [l=%d r=%d]\n", l, r);
      }
    }

    return numWays;
  }
}
