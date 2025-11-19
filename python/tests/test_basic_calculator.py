import pytest

from common.method import Method
from leetcode.stack_queue.basic_calculator import Solution


@pytest.mark.parametrize(
    "input_str, expected",
    [
        ("1 + 1", 2),
        (" 2-1 + 2 ", 3),
        ("(1+(4+5+2)-3)+(6+8)", 23),
        ("-2147483647", -2147483647),
        ("1-(     -2)", 3),
        ("-2+ 1", -1),
    ],
)
@pytest.mark.parametrize(
    "method",
    [Method.RECURSIVE, Method.OPTIMAL],
)
def test_basic_calculator(input_str, method, expected):
    solver = Solution(method)
    assert solver.calculate(input_str) == expected
