import pytest
from leetcode.roman_to_integer import Solution


@pytest.mark.parametrize(
    "roman, expected",
    [
        ("III", 3),
        ("IV", 4),
        ("IX", 9),
        ("LVIII", 58),
        ("MCMXCIV", 1994),
    ],
)
def test_roman_to_int(roman, expected):
    solver = Solution()
    assert solver.romanToInt(roman) == expected
