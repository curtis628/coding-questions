"""212: https://leetcode.com/problems/word-search-ii"""

from typing import List

from common.trie_node import TrieNode


class Solution:
    def findWords(self, board: List[List[str]], words: List[str]) -> List[str]:
        # Construct trie containing all words
        root = TrieNode()
        for word in words:
            node = root
            for ch in word:
                if ch not in node.children:
                    node.children[ch] = TrieNode()
                node = node.children[ch]

            node.word = word

        rows, cols = len(board), len(board[0])
        dirs = [(1, 0), (-1, 0), (0, 1), (0, -1)]  # N,S,E,W
        results = []

        def _dfs(node, row, col):
            if not (0 <= row < rows and 0 <= col < cols):
                return
            cell = board[row][col]
            if cell == "#" or cell not in node.children:
                return

            board[row][col] = "#"  # sentinel value marking it as visited
            child_node = node.children[cell]
            # print(f"Processing ({row}, {col}): {cell}: {child_node}")
            if child_node.word is not None:
                results.append(child_node.word)
                child_node.word = None  # no longer need to count this as a word
                if not child_node.children:  # also prune trie if we can
                    del node.children[cell]

            for row_delta, col_delta in dirs:
                _dfs(child_node, row + row_delta, col + col_delta)

            # backtrack!
            board[row][col] = cell

        for row in range(rows):
            for col in range(cols):
                _dfs(root, row, col)

        return results
