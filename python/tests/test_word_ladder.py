import json
from pathlib import Path

import pytest

from leetcode.graph.word_ladder import Solution

large_word_list = json.loads(
    Path("tests/fixtures/word_ladder_large_list.json").read_text()
)


@pytest.mark.parametrize(
    "begin_word, end_word, word_list, expected",
    [
        ("hit", "cog", ["hot", "dot", "dog", "lot", "log", "cog"], 5),
        ("hit", "cog", ["hot", "dot", "dog", "lot", "log"], 0),
        ("cet", "ism", large_word_list, 11),
    ],
)
def test_word_ladder(begin_word, end_word, word_list, expected):
    solver = Solution()
    assert solver.ladderLength(begin_word, end_word, word_list) == expected
