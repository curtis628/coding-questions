"""30: https://leetcode.com/problems/substring-with-concatenation-of-all-words"""

import logging
from collections import Counter, defaultdict
from itertools import permutations
from typing import List

from common.method import Method

logger = logging.getLogger(__name__)


class Solution:
    def __init__(self, method: Method = None):
        self.method = method if method else Method.BRUTE_FORCE

    def find_substring(self, s: str, words: List[str]) -> List[int]:
        logger.debug(f"Testing via {self.method}")
        if self.method == Method.BRUTE_FORCE:
            return self._brute_force(s, words)
        else:
            return self._optimal(s, words)

    def _optimal(self, s: str, words: List[str]) -> List[int]:
        if not s or not words:
            return []

        words_count = Counter(words)
        word_len = len(words[0])
        total_words = len(words)
        result = []

        # only need to check starting offsets that align with word boundaries
        for offset in range(word_len):
            left = right = offset
            window_count = defaultdict(int)
            words_matched = 0

            while right + word_len <= len(s):
                next_word = s[right : right + word_len]

                # Case 1: valid word → include it
                if next_word in words_count:
                    window_count[next_word] += 1
                    words_matched += 1
                    right += word_len

                    # shrink if we have too many of a word
                    while window_count[next_word] > words_count[next_word]:
                        left_word = s[left : left + word_len]
                        window_count[left_word] -= 1
                        words_matched -= 1
                        left += word_len

                    # full match
                    if words_matched == total_words:
                        result.append(left)

                # Case 2: invalid word → reset
                else:
                    window_count.clear()
                    words_matched = 0
                    right += word_len
                    left = right

        return result

    def _brute_force(self, s: str, words: List[str]) -> List[int]:
        num_words = len(words)
        perms = []

        def backtrack(comb: List[str], used):
            """Generate all permutations via brute force. O(N!) though!"""
            if len(comb) == num_words:
                perms.append("".join(comb))

            for i, num in enumerate(words):
                # O(1) check instead of O(n) vs "is not in"
                if not used[i]:
                    comb.append(num)
                    used[i] = True
                    # print(f"  appending {i=}: {num} to comb")
                    backtrack(comb, used)

                    del comb[-1]
                    used[i] = False

        backtrack(list(), [False] * num_words)

        # same as above, but lazily yields them via itertools
        all_perms = ["".join(p) for p in permutations(words)]
        assert all_perms == perms

        print(f"{perms=}")

        indexes = set()
        for poss in perms:
            start, end = 0, len(s)
            i = s.find(poss, start, end)
            # print(f"  {i=}: {poss=}: {start=} {end=}")
            while i != -1:
                indexes.add(i)
                start = i + 1
                i = s.find(poss, start, end)

        return list(indexes)
