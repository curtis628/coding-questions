package net.tcurt.sandbox.leetcode;

import static org.assertj.core.api.Assertions.assertThat;

import net.tcurt.sandbox.ListNode;
import org.junit.jupiter.api.Test;

public class SwapNodesInPairsTest {
  private SwapNodesInPairs underTest = new SwapNodesInPairs();

  @Test
  void input_1_2_3_4_is_2_1_4_3() {
    ListNode four = new ListNode(4);
    ListNode three = new ListNode(3, four);
    ListNode two = new ListNode(2, three);
    ListNode one = new ListNode(1, two);

    ListNode actual = underTest.swapPairs(one);
    assertThat(actual).isNotNull();
    assertThat(actual.val).isEqualTo(2);
    assertThat(actual.next).isNotNull();
    assertThat(actual.next.val).isEqualTo(1);
    assertThat(actual.next.next).isNotNull();
    assertThat(actual.next.next.val).isEqualTo(4);
    assertThat(actual.next.next.next).isNotNull();
    assertThat(actual.next.next.next.val).isEqualTo(3);
    assertThat(actual.next.next.next.next).isNull();
  }

  @Test
  void input_1_2_3_4_5_is_2_1_4_3_5() {
    ListNode five = new ListNode(5);
    ListNode four = new ListNode(4, five);
    ListNode three = new ListNode(3, four);
    ListNode two = new ListNode(2, three);
    ListNode one = new ListNode(1, two);

    ListNode actual = underTest.swapPairs(one);
    assertThat(actual).isNotNull();
    assertThat(actual.val).isEqualTo(2);
    assertThat(actual.next).isNotNull();
    assertThat(actual.next.val).isEqualTo(1);
    assertThat(actual.next.next).isNotNull();
    assertThat(actual.next.next.val).isEqualTo(4);
    assertThat(actual.next.next.next).isNotNull();
    assertThat(actual.next.next.next.val).isEqualTo(3);
    assertThat(actual.next.next.next.next).isNotNull();
    assertThat(actual.next.next.next.next.val).isEqualTo(5);
    assertThat(actual.next.next.next.next.next).isNull();
  }

  @Test
  void input_1_is_1() {
    ListNode one = new ListNode(1);
    ListNode actual = underTest.swapPairs(one);
    assertThat(actual.val).isEqualTo(1);
    assertThat(actual.next).isNull();
  }

  @Test
  void input_null() {
    ListNode actual = underTest.swapPairs(null);
    assertThat(actual).isNull();
  }
}
