package com.canal.client.demo.list;

import java.util.Random;

/**
 * 删除倒数第n个节点
 */
public class List1 extends BaseList {

    public static void main(String[] args) {
        List1 list1 = new List1();
        list1.print(list1.head);
        list1.remove(list1.head, new Random().nextInt(list1.len) + 1);
        list1.print(list1.head);
    }


    public Node remove(Node node, int n) {
        System.out.println("删除倒数第" + n + "个");
        Node tmp = new Node(0);
        tmp.next = node;

        Node first = tmp;
        Node second = tmp;
        for (int i = 1; i <= n; i++) {
            first = first.next;
        }
        while (first.next != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return tmp.next;
    }
}
