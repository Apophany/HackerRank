package com.hacker_rank.datastructures;

public class VerifyBST {
    boolean checkBST(Node root) {
        if (root == null) {
            return true;
        }
        return verify(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean verify(Node node, int min, int max) {
        if (node == null) {
            return true;
        }
        if (node.data <= min  || node.data >= max) {
            return false;
        }
        return verify(node.left, min, node.data) && verify(node.right, node.data, max);
    }

    private static class Node {
        int data;
        Node left;
        Node right;
    }
}
