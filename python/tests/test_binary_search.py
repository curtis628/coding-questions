import common.binary_search as bs


def test_binary_searches():
    nums = [1, 3, 3, 5, 7]
    assert bs.binary_search(nums, -1) == -1
    assert bs.binary_search_left(nums, -1) == 0
    assert bs.binary_search_right(nums, -1) == 0

    assert bs.binary_search(nums, 1) == 0
    assert bs.binary_search_left(nums, 1) == 0
    assert bs.binary_search_right(nums, 1) == 1

    assert bs.binary_search(nums, 2) == -1
    assert bs.binary_search_left(nums, 2) == 1
    assert bs.binary_search_right(nums, 2) == 1

    assert bs.binary_search(nums, 3) in [1, 2]
    assert bs.binary_search_left(nums, 3) == 1
    assert bs.binary_search_right(nums, 3) == 3

    assert bs.binary_search(nums, 4) == -1
    assert bs.binary_search_left(nums, 4) == 3
    assert bs.binary_search_right(nums, 4) == 3

    assert bs.binary_search(nums, 5) == 3
    assert bs.binary_search_left(nums, 5) == 3
    assert bs.binary_search_right(nums, 5) == 4

    assert bs.binary_search(nums, 6) == -1
    assert bs.binary_search_left(nums, 6) == 4
    assert bs.binary_search_right(nums, 6) == 4

    assert bs.binary_search(nums, 7) == 4
    assert bs.binary_search_left(nums, 7) == 4
    assert bs.binary_search_right(nums, 7) == 5

    assert bs.binary_search(nums, 100) == -1
    assert bs.binary_search_left(nums, 100) == 5
    assert bs.binary_search_right(nums, 100) == 5
