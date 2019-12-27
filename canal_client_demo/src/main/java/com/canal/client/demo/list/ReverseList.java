package com.canal.client.demo.list;

public class ReverseList extends BaseList {

    public static void main(String[] args) {
        ReverseList reverseList = new ReverseList();
        reverseList.print(reverseList.head);
        reverseList.reverse(reverseList.head);
        reverseList.print(reverseList.head);
    }

    private Node reverse(Node node) {
        if (node == null) {
            return null;
        }
        Node tail = null;
        Node head = node;
        while (true) {
            Node tmp = head.next;
            head.next = tail;
            tail = head;
            if (tmp == null) {
                break;
            }
            head = tmp;
        }
        this.head = head;
        return head;
    }
}
