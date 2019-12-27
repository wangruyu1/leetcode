package com.canal.client.demo.leetcode.linkedlist;


public class 判断链表有环 {
    public boolean hasCycle(ListNode head) {

        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null) {
            if (fast == slow) {
                return true;
            }
            slow = slow.next;
            fast = fast.next;
            if (fast == null) {
                return false;
            } else {
                fast = fast.next;
            }
        }

        return false;
    }


}