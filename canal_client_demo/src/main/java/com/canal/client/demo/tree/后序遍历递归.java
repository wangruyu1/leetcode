package com.canal.client.demo.tree;

public class 后序遍历递归 extends BaseTree {

    public static void main(String[] args) {
        BaseTree tree = new 后序遍历递归();
        tree.start(tree.head);
    }


    @Override
    void start(Node node) {
        if (node == null) {
            return;
        }
        start(node.left);
        start(node.right);
        System.out.print(node.value + "->");
    }
}
