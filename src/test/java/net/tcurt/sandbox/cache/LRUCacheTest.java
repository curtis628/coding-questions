package net.tcurt.sandbox.cache;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LRUCacheTest {
  private static String USER_PREFIX = "user-";
  private IpLookupService mockLookupService;

  /**
   * Setup includes mocking {@link IpLookupService} to return IP addresses corresponding to their
   * user ID. For example:
   *
   * <ul>
   *   <li>{@code user-1} --> {@code 192.168.1.1}
   *   <li>{@code user-2} --> {@code 192.168.1.2}
   *   <li>{@code user-100} --> {@code 192.168.1.100}
   * </ul>
   */
  @BeforeEach
  void setup() {
    mockLookupService = mock(IpLookupService.class);
    when(mockLookupService.lookupIp(anyString()))
        .thenAnswer(
            invocation -> {
              String userId = invocation.getArgument(0);
              String intPart = userId.substring(USER_PREFIX.length());
              int lastOctet = Integer.parseInt(intPart);
              return "192.168.1." + lastOctet;
            });
  }

  /** Helper test method to return IP address in order they exist in the cache's linked list. */
  private List<String> getLinkedListIps(LRUCache cache) {
    return cache.linkedList.stream().map(LRUCache.Node::getIp).collect(Collectors.toList());
  }

  private void assertHitsMissesEvictions(
      LRUCache cache, int expectedHits, int expectedMisses, int expectedEvictions) {
    assertThat(cache.cacheHits.get()).isEqualTo(expectedHits);
    assertThat(cache.cacheMisses.get()).isEqualTo(expectedMisses);
    assertThat(cache.evictionCounts.get()).isEqualTo(expectedEvictions);
  }

  @Test
  void maxSize5() {
    // fill cache
    LRUCache cache = new LRUCache(5, mockLookupService);
    for (int i = 1; i <= 5; i++) { // fill cache
      String user = USER_PREFIX + i;
      cache.getIpForUserId(user);
    }
    assertThat(getLinkedListIps(cache))
        .containsExactly("192.168.1.5", "192.168.1.4", "192.168.1.3", "192.168.1.2", "192.168.1.1");
    assertThat(cache.userIdToNodeLookup).hasSize(5);
    assertHitsMissesEvictions(cache, 0, 5, 0);

    // add one more
    assertThat(cache.getIpForUserId("user-6")).isEqualTo("192.168.1.6");
    assertThat(getLinkedListIps(cache))
        .containsExactly("192.168.1.6", "192.168.1.5", "192.168.1.4", "192.168.1.3", "192.168.1.2");
    assertThat(cache.userIdToNodeLookup).hasSize(5);
    assertHitsMissesEvictions(cache, 0, 6, 1);

    // user-4 queries a couple of times
    assertThat(cache.getIpForUserId("user-4")).isEqualTo("192.168.1.4");
    assertThat(cache.getIpForUserId("user-4")).isEqualTo("192.168.1.4");
    assertThat(getLinkedListIps(cache))
        .containsExactly("192.168.1.4", "192.168.1.6", "192.168.1.5", "192.168.1.3", "192.168.1.2");
    assertThat(cache.userIdToNodeLookup).hasSize(5);
    assertHitsMissesEvictions(cache, 2, 6, 1);

    // user-2 queries again
    assertThat(cache.getIpForUserId("user-2")).isEqualTo("192.168.1.2");
    assertThat(getLinkedListIps(cache))
        .containsExactly("192.168.1.2", "192.168.1.4", "192.168.1.6", "192.168.1.5", "192.168.1.3");
    assertThat(cache.userIdToNodeLookup).hasSize(5);
    assertHitsMissesEvictions(cache, 3, 6, 1);

    // user-1 queries... but ip lookup req'd since it was evicted from the cache already
    assertThat(cache.getIpForUserId("user-1")).isEqualTo("192.168.1.1");
    assertThat(getLinkedListIps(cache))
        .containsExactly("192.168.1.1", "192.168.1.2", "192.168.1.4", "192.168.1.6", "192.168.1.5");
    assertThat(cache.userIdToNodeLookup).hasSize(5);
    assertHitsMissesEvictions(cache, 3, 7, 2);
  }

  @Test
  void maxSize500() {
    LRUCache cache = new LRUCache(500, mockLookupService);
    for (int i = 1; i <= 1000; i++) {
      cache.getIpForUserId(String.format("user-%d", i));
    }
    assertHitsMissesEvictions(cache, 0, 1000, 500);
    assertThat(cache.userIdToNodeLookup).hasSize(500);

    // yeah - I know the IP addresses aren't valid...
    LRUCache.Node expectedFirst = new LRUCache.Node("user-1000", "192.168.1.1000");
    assertThat(cache.linkedList.getFirst()).isEqualTo(expectedFirst);

    LRUCache.Node expectedLast = new LRUCache.Node("user-501", "192.168.1.501");
    assertThat(cache.linkedList.getLast()).isEqualTo(expectedLast);
  }
}
