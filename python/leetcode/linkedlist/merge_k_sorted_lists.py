"""23: https://leetcode.com/problems/merge-k-sorted-lists"""

import heapq
from itertools import count
from typing import List, Optional

from common.list_node import ListNode


class Solution:

    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        if not lists:
            return None

        h = []
        tie_breaker = count()
        for node in lists:
            if node:
                heapq.heappush(h, (node.val, next(tie_breaker), node))

        merged = dummy = ListNode(-1)
        while h:
            _, _, node = heapq.heappop(h)
            merged.next = node
            merged = merged.next
            if node.next:
                heapq.heappush(h, (node.next.val, next(tie_breaker), node.next))

        return dummy.next
