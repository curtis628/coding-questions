package net.tcurt.sandbox.problems;

import lombok.extern.slf4j.Slf4j;
import net.tcurt.sandbox.ListNode;

/** From <a href="https://leetcode.com/problems/swap-nodes-in-pairs">Leetcode 24</a> */
@Slf4j
public class SwapNodesInPairs {

  public ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null) return head;

    // navigate to end of linked list in pairs recursively until the end
    ListNode currentRightNext = swapPairs(head.next.next);

    // when backtracking, swap pairs
    ListNode left = head;
    ListNode right = head.next;

    right.next = left;
    left.next = currentRightNext;
    head = right;
    return head;
  }
}
