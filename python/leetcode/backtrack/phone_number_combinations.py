"""17: https://leetcode.com/problems/letter-combinations-of-a-phone-number"""

from typing import List


class Solution:

    def letterCombinations(self, digits: str) -> List[str]:
        lookup = {
            "2": "abc",
            "3": "def",
            "4": "ghi",
            "5": "jkl",
            "6": "mno",
            "7": "pqrs",
            "8": "tuv",
            "9": "wxyz",
        }
        results = []

        def _dfs(ndx, comb):
            if ndx == len(digits):
                results.append("".join(comb))
                return

            digit = digits[ndx]
            for letter in lookup[digit]:
                comb.append(letter)
                _dfs(ndx + 1, comb)
                comb.pop()

        _dfs(0, [])
        return results
