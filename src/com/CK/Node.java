package com.CK;

public class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {
    }

    Node(int _val, Node _next, Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }

    public static void printNode(Node node) {
        while (node.next != null) {
            System.out.print(node.val + ", r:");
            if (node.random == null) {
                System.out.print("null" + "->");
            } else {
                System.out.print(node.random.val + "->");
            }
            node = node.next;
        }
        if (node.random == null) {
            System.out.print(node.val + ", r:" + "null\n");

        } else {
            System.out.print(node.val + ", r:" + node.random.val + "\n");
        }
    }

    void setRandom(Node node) {
        this.random = node;
    }

}
