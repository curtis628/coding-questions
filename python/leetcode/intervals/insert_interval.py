"""57: https://leetcode.com/problems/insert-interval/"""

from typing import List

"""
[[1,3],[6,9]],                      [2,5],  [[1,5],[6,9]]),
[[1,2],[3,5],[6,7],[8,10],[12,16]], [4,8], [[1,2],[3,10],[12,16]]),

"""


class Solution:
    def insert(
        self, intervals: List[List[int]], newInterval: List[int]
    ) -> List[List[int]]:
        result = []
        new_i_start, new_i_end = newInterval[0], newInterval[1]
        i, n = 0, len(intervals)

        # Step 1: insert `intervals` until we need to merge
        while i < n and new_i_start > intervals[i][1]:
            result.append([intervals[i][0], intervals[i][1]])
            i += 1

        # Step 2: merge
        merged_start = new_i_start if i == n else min(new_i_start, intervals[i][0])
        merged_end = new_i_end
        while i < n and new_i_end >= intervals[i][0]:
            merged_end = max(merged_end, intervals[i][1])
            i += 1
        result.append([merged_start, merged_end])

        # Step 3: add remaining
        while i < n:
            result.append([intervals[i][0], intervals[i][1]])
            i += 1

        return result
