"""46: https://leetcode.com/problems/permutations"""

from typing import List


class Solution:

    def permute(self, nums: List[int]) -> List[List[int]]:
        results = []
        visited = [False] * len(nums)

        def _dfs(comb):
            if len(comb) == len(nums):
                results.append(comb[:])
                return

            # at each step, try every unvisited number. Then backtrack.
            for ndx, num in enumerate(nums):
                if not visited[ndx]:
                    visited[ndx] = True
                    comb.append(num)
                    _dfs(comb)
                    # backtrack!
                    visited[ndx] = False
                    comb.pop()

        _dfs([])
        return results
