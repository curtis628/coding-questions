# https://leetcode.com/problems/roman-to-integer/
class Solution:

    def romanToInt(self, s: str) -> int:
        values = {
            "I": 1,
            "V": 5,
            "X": 10,
            "L": 50,
            "C": 100,
            "D": 500,
            "M": 1000,
        }
        result = 0
        for ndx in range(len(s)):
            numeral = s[ndx]
            # if next value is larger than this one, subtract instead of add
            if ndx + 1 < len(s) and values[numeral] < values[s[ndx + 1]]:
                result -= values[numeral]
            else:
                result += values[numeral]
        return result
