"""53: https://leetcode.com/problems/maximum-subarray (kandane)"""

from typing import List


class Solution:

    def maxSubArray(self, nums: List[int]) -> int:
        """Find the subarray in `nums` with the largest sum, and return its sum."""
        max_sum = curr_max = nums[0]
        for num in nums[1:]:
            curr_max = max(curr_max + num, num)
            max_sum = max(max_sum, curr_max)

        return max_sum
