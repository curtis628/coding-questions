"""211: https://leetcode.com/problems/design-add-and-search-words-data-structure"""

from common.trie_node import TrieNode


class WordDictionary:

    def __init__(self):
        self.root = TrieNode()

    def addWord(self, word: str) -> None:
        node = self.root
        for ch in word:
            if ch not in node.children:
                node.children[ch] = TrieNode()
            node = node.children[ch]

        node.word = word

    def search(self, word: str) -> bool:

        def _dfs(node, ndx):
            if ndx == len(word):
                return node.word is not None

            letter = word[ndx]
            if letter == ".":
                return any(_dfs(child, ndx + 1) for child in node.children.values())
            else:
                if letter not in node.children:
                    return False
                else:
                    return _dfs(node.children[letter], ndx + 1)

        return _dfs(self.root, 0)


# Your WordDictionary object will be instantiated and called as such:
# obj = WordDictionary()
# obj.addWord(word)
# param_2 = obj.search(word)
