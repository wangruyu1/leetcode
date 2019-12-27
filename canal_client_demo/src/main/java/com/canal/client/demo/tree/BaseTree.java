package com.canal.client.demo.tree;

public abstract class BaseTree {

    abstract void start(Node node);

    public BaseTree() {
        initTree();
    }

    protected Node head;

    public void initTree() {

        head = new Node(1);
        Node left1 = new Node(2);
        Node right1 = new Node(3);
        head.left = left1;
        head.right = right1;

        Node left2 = new Node(4);
        Node right2 = new Node(5);
        head.left.left = left2;
        head.left.right = right2;

        Node left3 = new Node(6);
        Node right3 = new Node(7);
        head.right.left = left3;
        head.right.right = right3;
    }

}


class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }
}
