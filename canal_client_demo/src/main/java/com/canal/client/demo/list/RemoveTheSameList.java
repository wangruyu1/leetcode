package com.canal.client.demo.list;

public class RemoveTheSameList extends BaseList {

    public static void main(String args[]) {
        RemoveTheSameList list = new RemoveTheSameList();
        list.print(list.head3);
        list.print(list.removeTheSameNode(list.head3));
    }

    public Node removeTheSameNode(Node node) {
        if (node == null) {
            return null;
        }
        if (node.next == null) {
            return node;
        }
        Node head = node;
        Node pre = node;
        node = node.next;
        while (node != null) {
            if (node.value != pre.value) {
                pre.next = node;
                pre = node;
            }
            node = node.next;
        }

        return head;
    }
}
