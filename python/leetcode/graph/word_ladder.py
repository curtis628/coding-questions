"""127: https://leetcode.com/problems/word-ladder"""

from collections import deque
from typing import List


class Solution:
    def ladderLength(self, beginWord: str, endWord: str, wordList: List[str]) -> int:
        if endWord not in wordList:
            return 0
        word_set = set(wordList)
        n = len(beginWord)

        def _filter_single_letter_diff(word):
            result = []
            for i in range(n):
                for ch in range(ord("a"), ord("z") + 1):
                    poss = word[0:i] + chr(ch) + word[i + 1 : n]
                    if poss != word and poss in word_set:
                        result.append(poss)

            return result

        queue = deque([(beginWord, 0)])
        while queue:
            word, transforms = queue.popleft()
            # print(f"Processing: {word=} {transforms=}")
            if word == endWord:
                return transforms + 1

            for poss in _filter_single_letter_diff(word):
                queue.append((poss, transforms + 1))
                word_set.remove(poss)

        return 0
