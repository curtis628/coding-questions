from leetcode.word_dictionary import WordDictionary


def test_word_dictionary():
    word_dict = WordDictionary()
    word_dict.addWord("bad")
    word_dict.addWord("dad")
    word_dict.addWord("mad")

    assert word_dict.search("pad") is False
    assert word_dict.search("bad") is True
    assert word_dict.search(".ad") is True
    assert word_dict.search("b..") is True
    assert word_dict.search("ba") is False
    assert word_dict.search("b.") is False
    assert word_dict.search(".") is False
