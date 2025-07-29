package net.tcurt.sandbox.problems;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.tcurt.sandbox.ListNode;

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
    // say head was 4. head.next(5).next should now be 4: 5 -> 4
    // then clear out 4's (head.next) orig next pointer as its now the end of the list
    head.next.next = head;
    head.next = null;
    return origEnd;
  }

  private ListNode reverseIteratively(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
      ListNode nextTemp = curr.next;
      curr.next = prev;
      prev = curr;
      curr = nextTemp;
    }

    return prev;
  }
}
