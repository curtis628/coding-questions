"""1: https://leetcode.com/problems/two-sum"""

from common.binary_search import binary_search_left


class Solution:
    def twoSum(self, nums: list[int], target: int) -> list[int]:
        lookup = {}
        for i, num in enumerate(nums):
            diff = target - num
            if diff in lookup:
                return [lookup[diff], i]
            lookup[num] = i

    def two_sum_sort_and_binary_search(self, nums, target) -> list[int]:
        # Step 1: keep original indices
        pairs = [(num, i) for i, num in enumerate(nums)]

        # Step 2: sort by value
        pairs.sort(key=lambda x: x[0])
        sorted_nums = [num for (num, _) in pairs]  # just values for searching

        # Step 3: for each element, binary search for its partner
        for idx, (num, original_i) in enumerate(pairs):
            diff = target - num

            # search in the portion after current index
            # j = bisect.bisect_left(pairs, (diff, -1), lo=idx + 1)
            # if j < len(pairs) and pairs[j][0] == diff:
            #    return sorted([original_i, pairs[j][1]])

            j = binary_search_left(sorted_nums, diff, lo=idx + 1)
            if j < len(sorted_nums) and sorted_nums[j] == diff:
                return sorted([original_i, pairs[j][1]])

        return []  # should not happen if input guarantees one solution
