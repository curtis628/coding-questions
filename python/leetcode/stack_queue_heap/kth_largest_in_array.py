"""215: https://leetcode.com/problems/kth-largest-element-in-an-array"""

import heapq
from typing import List


class Solution:

    def findKthLargest(self, nums: List[int], k: int) -> int:
        h = []
        for num in nums:
            heapq.heappush(h, num)
            if len(h) > k:
                heapq.heappop(h)
        return h[0]
