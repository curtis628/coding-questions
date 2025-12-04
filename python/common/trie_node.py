class TrieNode:
    """LeetCode definition for a Trie node"""

    def __init__(self):
        self.children = {}
        self.word = None  # only store terminal word in leaf nodes

    def __repr__(self):
        """Readable string representation for debugging."""
        return f"TrieNode(children={self.children.keys()}, word={self.word})"
