import pytest

from leetcode.backtrack.generate_parenthesis import Solution


@pytest.mark.parametrize(
    "n, expected",
    [
        (3, ["((()))", "(()())", "(())()", "()(())", "()()()"]),
        (1, ["()"]),
    ],
)
def test_generate_parenthesis(n, expected):
    solver = Solution()
    assert solver.generateParenthesis(n) == expected
