"""224: https://leetcode.com/problems/basic-calculator"""

from common.method import Method


class Solution:
    def __init__(self, method=Method.OPTIMAL):
        self.method = method

    def calculate(self, s: str) -> int:
        if self.method == Method.RECURSIVE:
            result = self._recursive(s)
        else:
            result = self._optimal(s)

        print(f"method={self.method} --> {result=}")
        return result

    def _optimal(self, s):
        stack = []
        current = result = 0
        sign = 1

        for ch in s:
            if ch.isdigit():
                current = current * 10 + int(ch)

            elif ch in "+-":
                # complete the previous number
                result += sign * current
                current = 0
                # update sign for next number
                sign = 1 if ch == "+" else -1

            elif ch == "(":
                # push current context before starting sub-expression
                stack.append(result)
                stack.append(sign)
                result = current = 0
                sign = 1

            elif ch == ")":
                # complete sub-expression inside of paren
                result += sign * current
                current = 0

                # pop the last sign and previous result
                result *= stack.pop()  # multiply by sign before parentheses
                result += stack.pop()  # add previous result

        return result + sign * current

    def _recursive(self, s):
        """This was my initial attempt. Uses recursion stack when processing parens"""
        ops = {"+", "-", "(", ")"}
        stripped = "".join(s.split())  # parse to minimum formatted string
        N = len(stripped)

        def _helper(start_ndx):
            result = op = None
            ndx = start_ndx

            while ndx < N:
                token = stripped[ndx]
                if token not in ops:  # token is start of a number. be greedy!
                    while ndx + 1 < N and stripped[ndx + 1] not in ops:
                        token += stripped[ndx + 1]
                        ndx += 1
                    token = int(token)

                if token == "(":
                    token, ndx = _helper(ndx + 1)
                elif token == ")":
                    return -result if op == "-" else result, ndx

                if result is None and token not in ops:
                    result = -token if op == "-" else token
                    op = None
                elif token in ops:
                    op = token
                else:
                    result = result + token if op == "+" else result - token
                    op = None

                ndx += 1

            return result, ndx

        response, _ = _helper(0)
        return response
