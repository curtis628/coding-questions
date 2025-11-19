import pytest

from leetcode.dp.candy import Solution


@pytest.mark.parametrize(
    "ratings, expected_total_candies",
    [
        ([1, 0, 2], 5),
        ([1, 2, 2], 4),
        ([1, 8, 9, 1], 7),
        ([1, 9, 8, 1], 7),
        ([5, 6, 7, 8, 8, 8, 7, 6, 5], 21),
    ],
)
def test_candy(ratings, expected_total_candies):
    solver = Solution()
    assert solver.candy(ratings) == expected_total_candies
