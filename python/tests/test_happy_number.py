import pytest

from leetcode.happy_number import Solution


@pytest.mark.parametrize(
    "num, expected",
    [
        (19, True),
        (2, False),
    ],
)
def test_dummy(num, expected):
    solver = Solution()
    assert solver.isHappy(num) == expected
