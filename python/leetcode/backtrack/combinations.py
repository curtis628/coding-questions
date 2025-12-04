"""77: https://leetcode.com/problems/combinations"""

from typing import List


class Solution:

    def combine(self, n: int, k: int) -> List[List[int]]:
        results = []

        def _dfs(start, comb):
            if len(comb) == k:
                results.append(comb[:])
                return

            for num in range(start, n + 1):
                comb.append(num)
                _dfs(num + 1, comb)
                comb.pop()

        _dfs(1, [])
        return results
