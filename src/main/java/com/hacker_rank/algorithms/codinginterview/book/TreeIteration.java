package com.hacker_rank.algorithms.codinginterview.book;

import com.hacker_rank.algorithms.codinginterview.book.util.TreeNode;

import java.util.HashMap;

public class TreeIteration {
    /**
     * In order traversal of binary tree.
     * Visits the left child first, followed
     * by the root, and finally the right child.
     *
     * Results in ordered print output when the
     * binary tree is a binary search tree
     */
    public static void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.println(root.val);
        inOrder(root.right);
    }

    /**
     * Pre order traversal of binary tree.
     * Visits the root TreeNode first, followed
     * by the left child and finally the right.
     */
    public static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * Post order traversal of binary tree.
     * Vists the left child first, followed
     * by the right, and finally the root.
     */
    public static void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.val);
    }
}
