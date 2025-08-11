package net.tcurt.sandbox.interviews.cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Implements a least-recently-used (LRU) cache.
 *
 * <p>Recently used items will always be placed at the beginning of the linked list. Once the list
 * has reached its max capacity, it deletes oldest entries by dropping nodes from the end of the
 * list.
 *
 * <p>Quick lookup is implemented via a separate hash map. On a cache hit, the node will be removed
 * from its current place in the linked list and re-added at the beginning to signal it was recently
 * used.
 *
 * <h3>Problem Statement</h3>
 *
 * You are building a backend service that processes a high volume of user events. Each event
 * contains a user_id, and your system needs to enrich these events with the user's IP address.
 *
 * <p>To obtain the IP address, your service must query a third-party API: - The third-party API
 * accepts a user_id a nd returns the corresponding ip_address. - The API has a rate limit: maximum
 * X requests per hour.
 *
 * <p>Your system processes ~10X requests, and many events may contain the same user_id. Currently
 * the system queries the API to get the corresponding ip_address for every single user_id.
 *
 * <p>How can the sytem be improved to respect the API rate limit and ensure the IP address
 * information is available for each event.
 */
@Slf4j
public class LRUCache {
  // some basic metrics for the cache...
  AtomicInteger cacheHits = new AtomicInteger();
  AtomicInteger cacheMisses = new AtomicInteger();
  AtomicInteger evictionCounts = new AtomicInteger();

  @Data
  public static class Node {
    final String userId;
    final String ip;
  }

  int maxElements;
  Map<String, Node> userIdToNodeLookup;
  LinkedList<Node> linkedList;
  IpLookupService ipLookupService;

  public LRUCache(int max, IpLookupService lookupService) {
    this.maxElements = max;
    this.userIdToNodeLookup = new HashMap<>(maxElements);
    this.linkedList = new LinkedList<>();
    this.ipLookupService = lookupService;
  }

  public String getIpForUserId(String userId) {
    Node node;

    // cache hit. Move back to front of linked list
    if (userIdToNodeLookup.containsKey(userId)) {
      cacheHits.incrementAndGet();
      node = userIdToNodeLookup.get(userId);
      this.linkedList.remove(node);
      this.linkedList.addFirst(node);
    } else {
      // cache miss
      cacheMisses.incrementAndGet();
      String ip = ipLookupService.lookupIp(userId); // TODO add latency metrics
      node = new Node(userId, ip);

      if (linkedList.size() == maxElements) {
        log.debug("Cache is full... removing last element.");
        Node removed = this.linkedList.removeLast();
        this.userIdToNodeLookup.remove(removed.userId);
        this.evictionCounts.incrementAndGet();
      }
      this.linkedList.addFirst(node);
      this.userIdToNodeLookup.put(userId, node);
    }

    return node.ip;
  }
}
