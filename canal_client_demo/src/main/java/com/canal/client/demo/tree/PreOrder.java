package com.canal.client.demo.tree;

import java.util.Stack;

public class PreOrder extends BaseTree {
    public static void main(String[] args) {
        PreOrder preOrder = new PreOrder();
        preOrder.start2(preOrder.head);
//        preOrder.start(preOrder.head);
    }

    @Override
    void start(Node node) {
        Stack<Node> stack = new Stack<>();
        stack.add(head);
        while (!stack.isEmpty()) {
            Node tmpNode = stack.pop();
            System.out.print(tmpNode.value + "->");
            if (tmpNode.right != null) {
                stack.add(tmpNode.right);
            }
            if (tmpNode.left != null) {
                stack.add(tmpNode.left);
            }
        }
    }

    void start2(Node head) {
        Stack<Node> stack = new Stack<>();
        Node right = head;
        while (!stack.isEmpty() || right != null) {
            Node left = right;
            while (left != null) {
                System.out.print(left.value + "->");
                stack.add(left);
                left = left.left;
            }
            right = stack.pop().right;
        }
    }
}
