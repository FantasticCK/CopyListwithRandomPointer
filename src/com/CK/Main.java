package com.CK;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Node node4 = new Node(4, null, null);
        Node node3 = new Node(3, node4, null);
        Node node2 = new Node(2, node3, node3);
        Node node1 = new Node(1, node2, node3);
        Node.printNode(node1);
        Solution2 solution = new Solution2();
        Node.printNode(solution.copyRandomList(node1));
    }
}

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node clonedHead = new Node(head.val, null, null);
        Node headCurrent = head, clonedCurrent = clonedHead;
        HashMap<Node, Node> hashMapHead = new HashMap<Node, Node>();
        hashMapHead.put(headCurrent, headCurrent.random);

        while (headCurrent.next != null) {
            clonedCurrent.next = new Node(headCurrent.next.val, null, null);
            hashMapHead.put(headCurrent.next, headCurrent.next.random);
            headCurrent = headCurrent.next;
            clonedCurrent = clonedCurrent.next;
        }

        headCurrent = head;
        clonedCurrent = clonedHead;
        while (headCurrent != null) {
            if (headCurrent.random == null) {
                clonedCurrent.random = null;
                if (headCurrent.next == null) {
                    return clonedHead;
                } else {
                    headCurrent = headCurrent.next;
                    clonedCurrent = clonedCurrent.next;
                }
            }
            Node headTest = head, clonedTest = clonedHead;
            while (hashMapHead.get(headCurrent) != headTest) {
                headTest = headTest.next;
                clonedTest = clonedTest.next;
            }
            clonedCurrent.random = clonedTest;
            if (headCurrent.next == null) {
                return clonedHead;
            } else {
                headCurrent = headCurrent.next;
                clonedCurrent = clonedCurrent.next;
            }
        }
        return clonedHead;
    }
}

class Solution2 {
    // HashMap which holds old nodes as keys and new nodes as its values.
    HashMap<Node, Node> visitedHash = new HashMap<Node, Node>();

    public Node copyRandomList(Node head) {

        if (head == null) {
            return null;
        }

        // If we have already processed the current node, then we simply return the cloned version of
        // it.
        if (this.visitedHash.containsKey(head)) {
            return this.visitedHash.get(head);
        }

        // Create a new node with the value same as old node. (i.e. copy the node)
        Node node = new Node(head.val, null, null);

        // Save this value in the hash map. This is needed since there might be
        // loops during traversal due to randomness of random pointers and this would help us avoid
        // them.
        this.visitedHash.put(head, node);

        // Recursively copy the remaining linked list starting once from the next pointer and then from
        // the random pointer.
        // Thus we have two independent recursive calls.
        // Finally we update the next and random pointers for the new node created.
        node.next = this.copyRandomList(head.next);
        node.random = this.copyRandomList(head.random);

        return node;
    }
}
