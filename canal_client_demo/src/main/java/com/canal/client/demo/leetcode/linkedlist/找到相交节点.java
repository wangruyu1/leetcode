package com.canal.client.demo.leetcode.linkedlist;


import java.util.HashSet;
import java.util.Set;

public class 找到相交节点 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode pa = headA;
        ListNode pb = headB;

        while (true) {
            if (pa == pb) {
                return pa;
            }
            if (pa == null) {
                pa = headB;
            } else {
                pa = pa.next;
            }
            if (pb == null) {
                pb = headA;
            } else {
                pb = pb.next;
            }
        }
    }


    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {

        Set<ListNode> exists = new HashSet<>();

        while (headA != null) {
            exists.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (exists.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }

        return null;
    }
}