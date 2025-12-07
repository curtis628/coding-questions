"""20: https://leetcode.com/problems/valid-parentheses"""


class Solution:

    def isValid(self, s: str) -> bool:
        stack = []
        lookup = {"]": "[", ")": "(", "}": "{"}
        open_chars = set(lookup.values())

        for char in s:
            if char in open_chars:
                stack.append(char)
            else:
                if stack and stack[-1] == lookup[char]:
                    stack.pop()
                else:
                    return False

        return len(stack) == 0
