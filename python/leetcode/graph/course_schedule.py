"""207: https://leetcode.com/problems/course-schedule"""

from collections import defaultdict, deque
from typing import List


class Solution:

    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        """Use Kahn's to determine if DAG + topological sort

        1. Build adjacency list: prereq -> list of courses that depend on it
        2. Build in-degree array: in_degree[course] = # of prerequisites
        3. Push all courses with in_degree == 0 into a queue
        4. While queue no empty:
             c = queue.pop()
             For neighbor:
               decrement in_degree[n]
               If in_degree[n] becomes 0:
                 queue.push(n)
        """
        adj_list = defaultdict(set)
        in_degree = [0] * numCourses
        for course, prereq in prerequisites:
            adj_list[prereq].add(course)
            in_degree[course] += 1

        queue = deque(n for n in range(numCourses) if in_degree[n] == 0)
        remaining_courses = numCourses
        print(
            f"Process {remaining_courses} courses:\n{adj_list=}\n{in_degree=}\n{queue=}"
        )
        while queue:
            course = queue.popleft()
            for neighbor in adj_list[course]:
                in_degree[neighbor] -= 1
                if in_degree[neighbor] == 0:
                    queue.append(neighbor)

            remaining_courses -= 1

        return remaining_courses == 0
