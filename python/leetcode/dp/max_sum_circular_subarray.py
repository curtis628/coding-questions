"""918: https://leetcode.com/problems/maximum-sum-circular-subarray"""

from typing import List


class Solution:

    def maxSubarraySumCircular(self, nums: List[int]) -> int:
        total_sum = nums[0]
        max_sum = curr_max = nums[0]
        min_sum = curr_min = nums[0]

        for num in nums[1:]:
            total_sum += num
            curr_max = max(curr_max + num, num)
            max_sum = max(max_sum, curr_max)

            curr_min = min(curr_min + num, num)
            min_sum = min(min_sum, curr_min)

        if max_sum < 0:  # all negative numbers in 'nums' -> no need to loop
            return max_sum

        # By subtracting the min_sum subarray (kadane's inverse) from total,
        # we get the max looped sum (and see if it beats normal max_sum)
        return max(max_sum, total_sum - min_sum)
