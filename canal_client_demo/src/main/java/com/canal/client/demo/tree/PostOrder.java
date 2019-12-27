package com.canal.client.demo.tree;

import java.util.Stack;

public class PostOrder extends BaseTree {

    public static void main(String[] args) {
        PostOrder postOrder = new PostOrder();
        postOrder.start(postOrder.head);

    }


    @Override
    void start(Node node) {
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.add(node);
        while (!stack1.isEmpty()) {
            Node cur = stack1.pop();
            if (cur.left != null) {
                stack1.add(cur.left);
            }
            if (cur.right != null) {
                stack1.add(cur.right);
            }
            stack2.add(cur);
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().value + "->");
        }
    }
}
