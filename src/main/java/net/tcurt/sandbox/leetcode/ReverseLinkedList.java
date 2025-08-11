package net.tcurt.sandbox.leetcode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.tcurt.sandbox.ListNode;

// TODO struggled!
/** From <a href="https://leetcode.com/problems/reverse-linked-list/">Leetcode 206</a> */
@RequiredArgsConstructor
@Slf4j
public class ReverseLinkedList {
  final boolean isUsingRecursion;

  public ListNode reverseList(ListNode head) {
    System.out.printf("Reversing %s\n", isUsingRecursion ? "recursively" : "iteratively");
    return isUsingRecursion ? reverseRecursively(head) : reverseIteratively(head);
  }

  private ListNode reverseRecursively(ListNode head) {
    if (head == null || head.next == null) return head; // reached end of the list

    ListNode origEnd = reverseRecursively(head.next);
    // originally,  1 -> 2 -> 3 -> 4 -> null ; origEnd always stays 4
    //   when head=3: 3(head) -> 4(head.next) -> null
    //        head(3).next(4).next should now be 3(head): 4 -> 3
    head.next.next = head;
    head.next = null; // clear out head(3).next as it's the new end now
    //        now we have 4 -> 3 -> null

    //  when head=2: 2(head) -> 3 -> null
    //                      4 --^
    //       head(2).next(3).next -> head(2) + clear head(2).next as it's the end now
    //        now we have 4 -> 3 -> 2 -> null
    // ... etc.

    return origEnd;
  }

  private ListNode reverseIteratively(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;

    // P    C
    // null 1 -> 2 -> 3 -> null

    // 3 -> 2 -> 1 -> null
    while (curr != null) {
      ListNode nextTemp = curr.next;
      curr.next = prev;
      prev = curr;
      curr = nextTemp;
    }

    return prev;
  }
}
