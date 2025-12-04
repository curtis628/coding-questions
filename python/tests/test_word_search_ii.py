import pytest

from leetcode.backtrack.word_search_ii import Solution


@pytest.mark.parametrize(
    "board, words, expected",
    [
        (
            [
                ["o", "a", "a", "n"],
                ["e", "t", "a", "e"],
                ["i", "h", "k", "r"],
                ["i", "f", "l", "v"],
            ],
            ["oath", "pea", "eat", "rain"],
            ["oath", "eat"],
        ),
        ([["a", "b"], ["c", "d"]], ["abcb"], []),
    ],
)
def test_word_search_ii(board, words, expected):
    solver = Solution()
    actual = solver.findWords(board, words)
    assert set(actual) == set(expected)
