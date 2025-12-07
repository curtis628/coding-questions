"""148: https://leetcode.com/problems/sort-list"""

from typing import Optional

from common.list_node import ListNode


class Solution:

    def sortList(self, head: Optional[ListNode]) -> Optional[ListNode]:

        def merge_sort(curr):
            if curr is None or curr.next is None:  # single element
                return curr

            slow, fast = curr, curr.next
            while fast and fast.next:
                slow = slow.next
                fast = fast.next.next

            slow_next = slow.next
            slow.next = None  # cut the list
            left = merge_sort(curr)
            right = merge_sort(slow_next)
            merged = dummy = ListNode(-20000)  # start as dummy

            while left and right:
                if left.val <= right.val:
                    merged.next = left
                    left = left.next
                else:
                    merged.next = right
                    right = right.next

                merged = merged.next

            if left:
                merged.next = left
            if right:
                merged.next = right

            return dummy.next

        return merge_sort(head)

    def sort_array(self, array):

        def merge_sort(lo, hi):
            if lo == hi:
                return [array[lo]]

            mid = lo + (hi - lo) // 2
            left = merge_sort(lo, mid)
            right = merge_sort(mid + 1, hi)

            i = j = 0
            merged = []
            while i < len(left) and j < len(right):
                if left[i] <= right[j]:
                    merged.append(left[i])
                    i += 1
                else:
                    merged.append(right[j])
                    j += 1

            # append leftovers
            merged.extend(left[i:])
            merged.extend(right[j:])
            return merged

        return merge_sort(0, len(array) - 1)
