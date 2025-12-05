import pytest

from leetcode.n_queens import Solution


@pytest.mark.parametrize(
    "n, expected",
    [
        (4, [[".Q..", "...Q", "Q...", "..Q."], ["..Q.", "Q...", "...Q", ".Q.."]]),
        (1, [["Q"]]),
    ],
)
def test_n_queens(n, expected):
    solver = Solution()
    assert solver.solveNQueens(n) == expected
