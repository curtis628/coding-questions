"""39: https://leetcode.com/problems/combination-sum"""

from typing import List


class Solution:

    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        results = []
        candidates.sort()  # sorted + forward-only iteration avoids duplicates

        def _dfs(start, path, remaining):
            if remaining == 0:
                results.append(path[:])
                return

            for ndx in range(start, len(candidates)):
                num = candidates[ndx]
                if num > remaining:
                    break  # later numbers are even larger (due to sorting)

                path.append(num)
                _dfs(ndx, path, remaining - num)
                path.pop()

        _dfs(0, [], target)
        return results
