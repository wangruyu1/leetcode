package com.canal.client.demo.list;

import com.sun.scenario.effect.Merge;

import java.util.List;

public class MergeSortedList extends BaseList {

    public static void main(String args[]) {
        MergeSortedList list = new MergeSortedList();
        list.print(list.mergeList(list.head, list.head2));
    }

    public Node mergeList(Node list1, Node list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        Node head = new Node(0);
        Node tail = head;
        while (list1 != null && list2 != null) {
            if (list1.value <= list2.value) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }
        if (list1 != null) {
            tail.next = list1;
        }
        if (list2 != null) {
            tail.next = list2;
        }

        return head.next;
    }
}
