package net.tcurt.sandbox.interviews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.tcurt.sandbox.Method;

/**
 * You are given a collection of hosts, which each have a list of availability days. You are also
 * given a reservation span via a {@code startDay} and an {@code endDay}.
 *
 * <p>Return a collection of all possible pairs of hosts that could satisfy the reservation
 * <b>and</b> only require moving hosts once.
 *
 * <pre>
 * Host A - [1,2,3,6,7,10,11]
 * Host B - [3,4,5,6,8,9,10,13]
 * Host C - [7,8,9,10,11]
 * startDay - 3, endDay 11
 * results: [
 *   [B, C]
 * ]
 * </pre>
 *
 * One could stay at host {@code B} for days {@code 3-6}, and then switch to host {@code C} for
 * 7-11.
 */
@RequiredArgsConstructor
@Slf4j
public class SplitStays {
  private final Method method;

  record Host(String name, List<Integer> availability) {}

  record Range(int start, int end) {}

  /**
   * Return all possible pair combinations of {@code hosts} (names only) that satisfy the
   * requirements.
   */
  public List<List<String>> splitStays(List<Host> hosts, int startDay, int endDay) {
    List<List<String>> results = new ArrayList<>();

    for (int i = 0; i < hosts.size(); i++) {
      for (int j = 0; j < hosts.size(); j++) {
        if (i == j) continue;
        Host startHost = hosts.get(i);
        Host endHost = hosts.get(j);

        // supports multiple methods of finding reservations (based on `method`)
        processHosts(hosts, startHost, endHost, startDay, endDay, results);
      }
    }
    return results;
  }

  private void processHosts(
      List<Host> hosts,
      Host startHost,
      Host endHost,
      int startDay,
      int endDay,
      List<List<String>> results) {
    log.debug(
        "Finding availability for days {}-{} for start={} end={} using {}",
        startDay,
        endDay,
        startHost,
        endHost,
        this.method);
    switch (method) {
      case BRUTE_FORCE -> {
        int latestFromStart = latestConsecutiveFromDay(startHost, startDay);
        if (latestFromStart > 0) {
          int nextDay = latestFromStart + 1;
          int latestFromEnd = latestConsecutiveFromDay(endHost, nextDay);

          if (latestFromEnd >= endDay) {
            log.info("  {}-{} can be done via {} and {}", startDay, endDay, startHost, endHost);
            results.add(List.of(startHost.name(), endHost.name()));
          }
        }
      }
      case BINARY_SEARCH -> {
        // Build precomputed ranges for each host
        Map<Host, List<Range>> hostRangeMap = generateHostToRangeMap(hosts);

        List<Range> startRanges = hostRangeMap.get(startHost);
        int latestFromStart = latestConsecutiveFromDay(startRanges, startDay);
        if (latestFromStart > 0) {
          int nextDay = latestFromStart + 1;
          List<Range> endRanges = hostRangeMap.get(endHost);
          int latestFromEnd = latestConsecutiveFromDay(endRanges, nextDay);

          if (latestFromEnd >= endDay) {
            log.info("  {}-{} can be done via {} and {}", startDay, endDay, startHost, endHost);
            results.add(List.of(startHost.name(), endHost.name()));
          }
        }
      }
    }
  }

  /**
   * Returns the {@code host}'s latest consecutive day from {@code day}, or {@code -1} if {@code
   * host} lacks availability for {@code day}
   */
  private int latestConsecutiveFromDay(Host host, int day) {
    List<Integer> days = host.availability;
    int ndx = days.indexOf(day);
    if (ndx == -1) return -1;

    int latestDay = day;
    while (ndx + 1 < days.size() && days.get(ndx + 1) == latestDay + 1) {
      ndx++;
      latestDay++;
    }
    return latestDay;
  }

  /** Binary search for range containing {@code day}. */
  private int latestConsecutiveFromDay(List<Range> ranges, int day) {
    int lo = 0, hi = ranges.size() - 1;

    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      Range r = ranges.get(mid);

      if (day < r.start) {
        hi = mid - 1; // choose left side
      } else if (day > r.end) {
        lo = mid + 1; // choose right side
      } else {
        return r.end; // found the range
      }
    }

    return -1;
  }

  private Map<Host, List<Range>> generateHostToRangeMap(List<Host> hosts) {
    Map<Host, List<Range>> hostRangeMap = new HashMap<>();
    for (Host host : hosts) {
      List<Integer> days = host.availability();
      List<Range> ranges = new ArrayList<>();
      int s = days.get(0), e = days.get(0);
      for (int i = 1; i < days.size(); i++) {
        if (days.get(i) == e + 1) {
          e++;
        } else {
          ranges.add(new Range(s, e));
          s = e = days.get(i);
        }
      }
      ranges.add(new Range(s, e));
      hostRangeMap.put(host, ranges);
    }
    return hostRangeMap;
  }
}
