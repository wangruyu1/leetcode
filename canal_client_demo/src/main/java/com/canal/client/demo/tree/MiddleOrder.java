package com.canal.client.demo.tree;

import java.util.Stack;

public class MiddleOrder extends BaseTree {

    public static void main(String[] args) {
        MiddleOrder middleOrder = new MiddleOrder();
        middleOrder.start(middleOrder.head);
    }

    @Override
    void start(Node node) {
        Stack<Node> stack = new Stack<>();
        Node right = head;
        while (!stack.isEmpty() || right != null) {
            Node left = right;
            //做孩子入栈
            while (left != null) {
                stack.add(left);
                left = left.left;
            }
            //打印节点信息
            Node cur = stack.pop();
            System.out.print(cur.value + "->");
            //对右孩子做同样的操作
            right = cur.right;
        }
    }
}
