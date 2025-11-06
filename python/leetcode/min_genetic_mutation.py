"""433: https://leetcode.com/problems/minimum-genetic-mutation"""

import logging
from collections import deque
from typing import List

from common.method import Method

logger = logging.getLogger(__name__)


class Solution:
    def __init__(self, method: Method = None):
        self.method = method if method else Method.DFS

    def minMutation(self, startGene: str, endGene: str, bank: List[str]) -> int:
        logger.debug(f"Testing via {self.method}")
        if self.method == Method.DFS:
            return self._dfs(startGene, endGene, bank)
        else:
            return self._bfs(startGene, endGene, bank)

    def _bfs(self, startGene, endGene, bank):
        queue = deque([(startGene, 0)])
        visited = {startGene}
        genes = ["A", "C", "G", "T"]

        while queue:
            gene, steps = queue.popleft()
            if gene == endGene:
                return steps

            for i in range(8):  # iterate over each position in the gene
                for ch in genes:  # try each option
                    if gene[i] == ch:  # skip option if there's no change
                        continue

                    new_gene = gene[:i] + ch + gene[i + 1 :]
                    if new_gene in bank and new_gene not in visited:
                        visited.add(new_gene)
                        queue.append((new_gene, steps + 1))

        return -1

    def _dfs(self, startGene, endGene, bank):
        def backtrack(gene, visited):
            result = float("inf")

            if gene == endGene:
                return 0

            valid = []
            for option in bank:
                if option not in visited:
                    diffs = [0 if gene[i] == option[i] else 1 for i in range(8)]
                    if sum(diffs) == 1:
                        valid.append(option)

            for option in valid:
                visited.add(option)
                r = 1 + backtrack(option, visited)
                if r is not None:
                    result = min(result, r)

                visited.remove(option)

            return result

        r = backtrack(startGene, set([startGene]))
        return -1 if r == float("inf") else r
