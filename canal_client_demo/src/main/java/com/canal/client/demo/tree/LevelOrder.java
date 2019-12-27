package com.canal.client.demo.tree;

import java.util.LinkedList;

public class LevelOrder extends BaseTree {

    public static void main(String[] args) {
        LevelOrder levelOrder = new LevelOrder();
        levelOrder.start(levelOrder.head);
    }


    @Override
    void start(Node node) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);
        while (queue.size() > 0) {
            Node tmp = queue.pop();
            System.out.print(tmp.value + "->");
            if (tmp.left != null) {
                queue.add(tmp.left);
            }
            if (tmp.right != null) {
                queue.add(tmp.right);
            }
        }
    }
}
