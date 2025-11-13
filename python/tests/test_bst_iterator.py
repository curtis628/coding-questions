import pytest

from common.method import Method
from common.tree_node import TreeNode
from leetcode.bst_iterator import BSTIterator


@pytest.mark.parametrize(
    "method",
    [Method.DFS, Method.ITERATIVE],
)
def test_bst_iterator(method):
    """
    Test that BST in-order iterator works as expected for tree::

            7
           / \\
          3   15
             /  \\
            9    20
    """

    tree = TreeNode.from_level_list([7, 3, 15, None, None, 9, 20])
    iterator = BSTIterator(tree, method)

    # "next","next","hasNext","next","hasNext","next","hasNext","next","hasNext"]
    # 3,7,true,9,true,15,true,20,false]
    assert iterator.next() == 3
    assert iterator.next() == 7
    assert iterator.has_next()
    assert iterator.next() == 9
    assert iterator.has_next()
    assert iterator.next() == 15
    assert iterator.has_next()
    assert iterator.next() == 20
    assert iterator.has_next() is False
