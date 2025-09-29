import pytest

from template.default_problem import Solution


@pytest.mark.parametrize(
    "input_str, expected",
    [
        ("dummy", 5),
        ("hi", 2),
        ("", 0),
    ],
)
def test_dummy(input_str, expected):
    solver = Solution()
    assert solver.dummy(input_str) == expected
