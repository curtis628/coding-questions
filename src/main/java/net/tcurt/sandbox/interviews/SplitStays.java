package net.tcurt.sandbox.interviews;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * You are given a collection of hosts, which each have a list of availability days. You are also
 * given a reservation span via a {@code startDay} and an {@code endDay}.
 *
 * <p>Return a collection of all possible pairs of hosts that could satisfy the reservation
 * <b>and</b> only require moving hosts once.
 *
 * <pre>
 * Airbnb A - [1,2,3,6,7,10,11]
 * Airbnb B - [3,4,5,6,8,9,10,13]
 * Airbnb C - [7,8,9,10,11]
 * startDay - 3, endDay 11
 * results: [
 *   [B, C]
 * ]
 * </pre>
 *
 * One could stay at host {@code B} for days {@code 3-6}, and then switch to host {@code C} for
 * 7-11.
 */
@Slf4j
public class SplitStays {

  record Host(String name, List<Integer> availability) {}

  /**
   * Returns the {@code host}'s latest consecutive day from {@code day}, or {@code -1} if {@code
   * host} lacks availability for {@code day}
   */
  private int latestConsecutiveFromDay(Host host, int day) {
    int latestDay = -1;
    List<Integer> availability = host.availability;

    for (int i = 0; i < availability.size(); i++) {
      int d = availability.get(i);
      if (d == day) { // found that 'host' contains 'day'
        latestDay = day;
      } else if (latestDay > 0 && availability.get(i - 1) + 1 == d) {
        latestDay = d; // found consecutive availability
      } else if (latestDay > 0) {
        break; // no more consecutive availability
      }
    }

    return latestDay;
  }

  /**
   * Return all possible pair combinations of {@code hosts} (names only) that satisfy the
   * requirements.
   */
  public List<List<String>> splitStays(List<Host> hosts, int startDay, int endDay) {
    List<List<Host>> results = new ArrayList<>();

    for (int i = 0; i < hosts.size(); i++) {
      Host host1 = hosts.get(i);
      List<Integer> host1Days = host1.availability;
      for (int j = i + 1; j < hosts.size(); j++) {
        Host host2 = hosts.get(j);
        List<Integer> host2Days = host2.availability;

        // determine if we can start reservation from `host1`, and end at `host2`
        if (host1Days.contains(startDay)) {
          int host1LatestFromDay = this.latestConsecutiveFromDay(host1, startDay);
          int nextDay = host1LatestFromDay + 1;
          int host2LatestFromNext = this.latestConsecutiveFromDay(host2, nextDay);

          if (host2LatestFromNext >= endDay) {
            results.add(List.of(host1, host2));
          }
        }

        // determine if we can start reservation from `host2`, and end at `host1`
        if (host2Days.contains(startDay)) {
          int host2LatestFromDay = this.latestConsecutiveFromDay(host2, startDay);
          int nextDay = host2LatestFromDay + 1;
          int host1LatestFromNext = this.latestConsecutiveFromDay(host1, nextDay);

          if (host1LatestFromNext >= endDay) {
            // NOTE: put `host2` in front since we should start the stay there
            results.add(List.of(host2, host1));
          }
        }
      }
    }

    // return list of host names (and not hosts themselves)
    List<List<String>> response = new ArrayList<>();
    for (List<Host> hostPairs : results) {
      response.add(hostPairs.stream().map(Host::name).toList());
    }
    return response;
  }
}
