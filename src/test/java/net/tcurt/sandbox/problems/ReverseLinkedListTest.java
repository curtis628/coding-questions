package net.tcurt.sandbox.problems;

import static org.assertj.core.api.Assertions.assertThat;

import net.tcurt.sandbox.ListNode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ReverseLinkedListTest {

  @ParameterizedTest
  @ValueSource(booleans = {true, false})
  void input_1_2_3_4_5(boolean isRecursive) {
    ListNode five = new ListNode(5);
    ListNode four = new ListNode(4, five);
    ListNode three = new ListNode(3, four);
    ListNode two = new ListNode(2, three);
    ListNode one = new ListNode(1, two);
    ReverseLinkedList underTest = new ReverseLinkedList(isRecursive);

    ListNode actual = underTest.reverseList(one);
    String msg = isRecursive ? "recursive test" : "iterative test";
    assertThat(actual).as(msg).isNotNull();
    assertThat(actual.val).as(msg).isEqualTo(5);
    assertThat(actual.next).as(msg).isNotNull();
    assertThat(actual.next.val).as(msg).isEqualTo(4);
    assertThat(actual.next.next).as(msg).isNotNull();
    assertThat(actual.next.next.val).as(msg).isEqualTo(3);
    assertThat(actual.next.next.next).as(msg).isNotNull();
    assertThat(actual.next.next.next.val).as(msg).isEqualTo(2);
    assertThat(actual.next.next.next.next).as(msg).isNotNull();
    assertThat(actual.next.next.next.next.val).as(msg).isEqualTo(1);
    assertThat(actual.next.next.next.next.next).as(msg).isNull();
  }

  @ParameterizedTest
  @ValueSource(booleans = {true, false})
  void input_1_2(boolean isRecursive) {
    ListNode two = new ListNode(2);
    ListNode one = new ListNode(1, two);
    ReverseLinkedList underTest = new ReverseLinkedList(isRecursive);

    ListNode actual = underTest.reverseList(one);
    assertThat(actual).isNotNull();
    assertThat(actual.val).isEqualTo(2);
    assertThat(actual.next).isNotNull();
    assertThat(actual.next.val).isEqualTo(1);
    assertThat(actual.next.next).isNull();
  }

  @ParameterizedTest
  @ValueSource(booleans = {true, false})
  void input_empty(boolean isRecursive) {
    ReverseLinkedList underTest = new ReverseLinkedList(isRecursive);
    ListNode actual = underTest.reverseList(null);
    assertThat(actual).isNull();
  }
}
