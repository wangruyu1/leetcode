package com.canal.client.demo.leetcode.linkedlist;

class 删除指定链表元素 {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        ListNode first = new ListNode(0);
        ListNode pre = first;
        first.next = head;
        while (head != null) {
            if (head.val == val) {
                pre.next = head.next;
            } else {
                pre = head;
            }
            head = head.next;
        }

        return first.next;
    }
}