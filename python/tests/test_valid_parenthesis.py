import pytest

from leetcode.stack_queue.valid_parenthesis import Solution


@pytest.mark.parametrize(
    "input_str, expected",
    [
        ("()", True),
        ("", True),
        ("({[]})(({{}}))", True),
        ("({[})]", False),
        ("]", False),
        ("(", False),
    ],
)
def test_dummy(input_str, expected):
    solver = Solution()
    assert solver.isValid(input_str) == expected
