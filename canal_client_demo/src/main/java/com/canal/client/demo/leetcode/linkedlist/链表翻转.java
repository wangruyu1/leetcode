package com.canal.client.demo.leetcode.linkedlist;

class 链表翻转 {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode head1 = null;
        ListNode head2 = head;
        ListNode tmp = null;
        while (head2 != null) {
            tmp = head2.next;
            head2.next = head1;
            head1 = head2;
            head2 = tmp;
        }
        return head1;
    }
}