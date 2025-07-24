package net.tcurt.sandbox.cache;

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
