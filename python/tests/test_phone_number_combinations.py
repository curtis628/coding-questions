import pytest

from leetcode.backtrack.phone_number_combinations import Solution


@pytest.mark.parametrize(
    "digits, expected",
    [
        ("23", ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]),
        ("2", ["a", "b", "c"]),
    ],
)
def test_phone_number_combinations(digits, expected):
    solver = Solution()
    assert solver.letterCombinations(digits) == expected
