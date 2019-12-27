package com.canal.client.demo.leetcode.linkedlist;

class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        System.out.println(solution.isPalindrome(node1));
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode fast = head;
        ListNode slow = head;
        ListNode head1 = null;
        while (fast != null && fast.next != null) {
            if (fast == head) {
                fast = fast.next;
            } else {
                fast = fast.next.next;
            }
            ListNode tmp = slow.next;
            slow.next = head1;
            head1 = slow;
            slow = tmp;
        }
        if (fast == null) {
            head1 = head1.next;
        }
        while (head1 != null) {
//            System.out.print(head1.val);
            if (head1.val != slow.val) {
                return false;
            }
            head1 = head1.next;
            slow = slow.next;
        }
        return true;
    }


}