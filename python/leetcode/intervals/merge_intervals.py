"""56: https://leetcode.com/problems/merge-intervals/"""

from typing import List


class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        intervals.sort()
        result = []

        for start, end in intervals:
            # print(f"Processing {start=} {end=}. result: {result}")
            if not result or result[-1][1] < start:
                result.append([start, end])
            else:
                updated_end = max(result[-1][1], end)
                result[-1][1] = updated_end

        return result
