"""202: https://leetcode.com/problems/happy-number"""


class Solution:
    def isHappy(self, n: int) -> bool:
        def sum_of_squares(num):
            num_str = str(num)
            total = 0
            for digit in num_str:
                total += int(digit) ** 2
            return total

        slow, fast = n, sum_of_squares(n)
        while fast != 1 and sum_of_squares(fast) != 1:
            slow = sum_of_squares(slow)
            fast = sum_of_squares(sum_of_squares(fast))

            if slow == fast:
                return False  # cycle detected. Tortoise + Hare

        return True
