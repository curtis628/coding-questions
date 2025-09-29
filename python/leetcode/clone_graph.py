"""133: https://leetcode.com/problems/clone-graph"""

from collections import deque
from typing import Optional

from common.node import Node


class Solution:
    def cloneGraph(self, node: Optional["Node"]) -> Optional["Node"]:
        # return self._dfs(node, {}) if node else None
        return self._bfs(node) if node else None

    def _dfs(self, curr, copy_dict):
        if curr in copy_dict:
            return copy_dict[curr]

        clone_node = Node(curr.val)
        copy_dict[curr] = clone_node

        for neighbor in curr.neighbors:
            nei_clone = self._dfs(neighbor, copy_dict)
            clone_node.neighbors.append(nei_clone)

        return clone_node

    def _bfs(self, node):
        queue = deque([node])
        copy_dict = {node: Node(node.val)}

        while queue:
            curr = queue.popleft()
            clone = copy_dict[curr]

            for neighbor in curr.neighbors:
                if neighbor not in copy_dict:
                    copy_dict[neighbor] = Node(neighbor.val)
                    queue.append(neighbor)

                nei_clone = copy_dict[neighbor]
                clone.neighbors.append(nei_clone)

        return copy_dict[node]
