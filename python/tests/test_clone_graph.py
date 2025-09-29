import pytest

from common.method import Method
from common.node import Node
from leetcode.clone_graph import Solution


def test_edge():
    solver = Solution()
    assert solver.cloneGraph(None) is None

    node1 = Node(1, [])  # empty adjacency list
    actual = solver.cloneGraph(node1)
    assert actual is not node1
    assert actual.val == 1


@pytest.mark.parametrize(
    "method",
    [Method.BFS, Method.DFS],
)
def test_basic(method):
    """
    Basic test case using graph::

        (1) — (2)
         |     |
        (4) — (3)
    """
    solver = Solution(method)

    node1 = Node(1)
    node2 = Node(2)
    node3 = Node(3)
    node4 = Node(4)

    node1.neighbors = [node2, node4]
    node2.neighbors = [node1, node3]
    node3.neighbors = [node2, node4]
    node4.neighbors = [node1, node3]

    actual = solver.cloneGraph(node1)

    # ✅ Root node is a deep copy
    assert actual is not node1
    assert actual.val == node1.val

    # ✅ Collect visited nodes (to traverse the cloned graph)
    visited = {}

    def dfs(node):
        if node.val in visited:
            return
        visited[node.val] = node
        for nei in node.neighbors:
            dfs(nei)

    dfs(actual)

    # ✅ Check all 4 nodes exist
    assert set(visited.keys()) == {1, 2, 3, 4}

    # ✅ Each clone is a new object
    assert visited[1] is not node1
    assert visited[2] is not node2
    assert visited[3] is not node3
    assert visited[4] is not node4

    # ✅ Neighbor relationships preserved
    assert {n.val for n in visited[1].neighbors} == {2, 4}
    assert {n.val for n in visited[2].neighbors} == {1, 3}
    assert {n.val for n in visited[3].neighbors} == {2, 4}
    assert {n.val for n in visited[4].neighbors} == {1, 3}
