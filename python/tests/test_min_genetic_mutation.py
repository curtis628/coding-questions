import pytest

from common.method import Method
from leetcode.graph.min_genetic_mutation import Solution


@pytest.mark.parametrize(
    "start_gene, end_gene, bank, expected",
    [
        ("AACCGGTT", "AACCGGTA", ["AACCGGTA"], 1),
        ("AACCGGTT", "AAACGGTA", ["AACCGGTA", "AACCGCTA", "AAACGGTA"], 2),
        ("AAAAAAAA", "ACAAAAAA", ["CAAAAAAA", "CCAAAAAA", "ACAAAAAA"], 1),
    ],
)
@pytest.mark.parametrize(
    "method",
    [Method.BFS, Method.DFS],
)
def test_min_genetic_mutation(start_gene, end_gene, bank, expected, method):
    solver = Solution(method)
    assert solver.minMutation(start_gene, end_gene, bank) == expected
