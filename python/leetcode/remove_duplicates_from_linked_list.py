"""82: https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii"""

from typing import Optional

from common.list_node import ListNode


# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def deleteDuplicates(self, head: Optional[ListNode]) -> Optional[ListNode]:
        dummy = result_end = ListNode(-101, head)
        slow, fast = dummy, head

        while slow:
            fast = slow.next
            found_duplicate = False

            while fast and fast.val == slow.val:
                found_duplicate = True
                fast = fast.next

            if not found_duplicate:
                result_end.next = slow
                result_end = result_end.next

            slow = fast

        result_end.next = None  # prevent cycles
        return dummy.next
