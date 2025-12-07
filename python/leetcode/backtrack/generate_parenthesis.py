"""22: https://leetcode.com/problems/generate-parentheses"""

from typing import List


class Solution:

    def generateParenthesis(self, n: int) -> List[str]:
        results = []

        def _dfs(path, open_count, close_count):
            if len(path) == 2 * n:
                results.append("".join(path))
                return

            # if we haven't placed n open parens, we explore placing more.
            if open_count < n:
                path.append("(")
                _dfs(path, open_count + 1, close_count)
                path.pop()  # backtrack

            # we can only explore adding ')' if there are more '('
            if open_count > close_count:
                path.append(")")
                _dfs(path, open_count, close_count + 1)
                path.pop()

        _dfs([], 0, 0)
        return results
