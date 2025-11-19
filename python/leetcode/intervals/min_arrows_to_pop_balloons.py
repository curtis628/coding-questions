"""452: https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/"""

from typing import List


class Solution:
    def findMinArrowShots(self, points: List[List[int]]) -> int:
        # sort by end coordinate: fire at end coordinate --> greedy + simplifies logic
        points.sort(key=lambda p: p[1])
        num_arrows = 1
        arrow_end = points[0][1]  # always start by firing at end of the range

        for start, end in points[1:]:
            if start > arrow_end:
                num_arrows += 1
                arrow_end = end

        return num_arrows
