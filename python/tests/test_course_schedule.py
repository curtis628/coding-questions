import pytest

from leetcode.graph.course_schedule import Solution


@pytest.mark.parametrize(
    "numCourses, prerequisites, expected",
    [
        (2, [[1, 0]], True),
        (2, [[1, 0], [0, 1]], False),
        (5, [[4, 3], [4, 2], [4, 1], [3, 2]], True),
        (5, [[4, 3], [4, 2], [4, 1], [3, 2], [2, 1], [1, 3]], False),
    ],
)
def test_course_schedule(numCourses, prerequisites, expected):
    solver = Solution()
    assert solver.canFinish(numCourses, prerequisites) == expected
