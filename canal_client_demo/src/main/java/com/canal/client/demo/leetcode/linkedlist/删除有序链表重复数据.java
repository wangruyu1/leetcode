package com.canal.client.demo.leetcode.linkedlist;


class 删除有序链表重复数据 {


    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }

        ListNode tmp = head.next;
        ListNode pre = head;
        while (tmp != null) {
            if (tmp.val == pre.val) {
                pre.next = tmp.next;
                tmp = tmp.next;
            } else {
                pre = pre.next;
            }
        }
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}