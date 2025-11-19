"""135: https://leetcode.com/problems/candy"""


class Solution:

    def candy(self, ratings: list[int]) -> int:
        n = len(ratings)

        l_to_r = [1] * n
        for i in range(1, n):
            if ratings[i] > ratings[i - 1]:
                l_to_r[i] = l_to_r[i - 1] + 1

        r_to_l = [1] * n
        for i in range(n - 2, -1, -1):
            if ratings[i] > ratings[i + 1]:
                r_to_l[i] = r_to_l[i + 1] + 1

        candies = [max(l_to_r[i], r_to_l[i]) for i in range(n)]
        return sum(candies)
