package com.canal.client.demo.list;

public class BaseList {

    protected Node head;
    protected Node head2;
    protected Node head3;
    protected int len = 10;

    public BaseList() {
        Node cur = null;
        for (int i = 1; i <= len; i++) {
            if (i == 1) {
                cur = head = new Node(i);
            } else {
                cur.next = new Node(i);
                cur = cur.next;
            }
        }
        for (int i = 1; i <= len; i++) {
            if (i == 1) {
                cur = head2 = new Node(i);
            } else {
                cur.next = new Node(i);
                cur = cur.next;
            }
        }
        for (int i = 1; i <= len; i++) {
            if (i == 1) {
                cur = head3 = new Node(Math.random() > 0.5 ? i : i - 1);
            } else {
                cur.next = new Node(Math.random() > 0.5 ? i : i - 1);
                cur = cur.next;
            }
        }
    }

    protected void print(Node node) {
        while (node != null) {
            System.out.print(node.value + "->");
            node = node.next;
        }
        System.out.println();
    }
}

class Node {
    public int value;
    public Node next;

    public Node(int value) {
        this.value = value;
    }
}
